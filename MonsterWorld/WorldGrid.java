import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.Iterator;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class WorldGrid extends JPanel
{	
	public WorldGrid()
	{
		width = 10;
		height = 10;
		actors = new ActorGrid();
		initComponents();
	}

	public WorldGrid(int width,int height)
	{
		this.width = width;
		this.height = height;
		actors = new ActorGrid();
		initComponents();
		actors.add(new Rock(0,0));
	}

	public Dimension getPreferredSize() { return new Dimension(width * 48, height * 48); }

	//Add an actor to the list
	public void addActor(Actor newActor){actors.add(newActor);}

	public boolean isRunning(){return running;}

	public void setInterpolation(float interp){interpolation = interp;}

	// Start or stop gameLoop
	public void pressedStart()
	{
		toggleRunning();
		if (isRunning())
			runGameLoop();
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		for (int i = 0; i <= width; i++)
		{
			g2.draw(new Line2D.Double(i*48-1, 0, i*48-1, height*48));
			g2.draw(new Line2D.Double(i*48, 0, i*48, height*48));
		}

		for (int i = 0; i <= height; i++)
		{
			g2.draw(new Line2D.Double(0, i*48-1, width*48, i*48-1));
			g2.draw(new Line2D.Double(0, i*48, width*48, i*48));
		}

		//Draw all actors in list
		for (Actor a : actors)
		{ g2.drawImage(a.getImage(), a.getX()*48, a.getY()*48, null); }
	}

	//right-click drop down menu
	private void initComponents()
	{
		popupMenu.add(drawRockJMenu);
		popupMenu.add(drawHumanJMenu);
		popupMenu.add(drawVampireJMenu);
		popupMenu.add(drawZombieJMenu);
		add(popupMenu);

		addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e)
			{
				//If left click, try to remove actor in location
				if(e.getButton() == MouseEvent.BUTTON1)
				{
					//Convert mouse location to grid location
					xMouse = e.getX() / 48;
					yMouse = e.getY() / 48;

					removeActor(xMouse, yMouse);
				}

				checkForTriggerEvent(e);
			}

			//Open drop down window when RightClick
			private void checkForTriggerEvent(MouseEvent e)
			{
				if (e.getButton() == MouseEvent.BUTTON3)
				{
					xMouse = e.getX();
					yMouse = e.getY();
					popupMenu.show(e.getComponent(), xMouse, yMouse);
				}
			}

			public void mouseEntered(MouseEvent e) {}

			public void mouseExited(MouseEvent e) {}

			public void mousePressed(MouseEvent e) {}

			public void mouseReleased(MouseEvent e) {}
		});

		//Draw Rock at mouse location
		drawRockJMenu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(isEmpty((xMouse / 48), (yMouse / 48)))
					addActor(new Rock((xMouse / 48), (yMouse / 48)));
			}
		});

		//Draw Human at mouse location
		drawHumanJMenu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(isEmpty((xMouse / 48), (yMouse / 48)))
					addActor(new Human((xMouse / 48), (yMouse / 48)));
			}
		});

		//Draw Vampire at mouse location
		drawVampireJMenu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(isEmpty((xMouse / 48), (yMouse / 48)))
					addActor(new Vampire((xMouse / 48), (yMouse / 48)));
			}
		});

		//Draw Zombie at mouse location
		drawZombieJMenu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(isEmpty((xMouse / 48), (yMouse / 48)))
					addActor(new Zombie((xMouse / 48), (yMouse / 48)));
			}
		});
	}

	//Remove an actor at location (x,y)
	private void removeActor(int x, int y)
	{
		Iterator<Actor> iter = actors.iterator();

		//Checks each actor for their location
		//	if their location matches the location
		//	being searched for, remove that actor
		while(iter.hasNext())
		{
			Actor a = iter.next();

			if(a.getX() == x && a.getY() == y)
				iter.remove();
		}
	}

	//Check to see if (x,y) contains an actor
	private boolean isEmpty(int x, int y)
	{
		Iterator<Actor> iter = actors.iterator();

		//Check each actors location in the Grid to
		// see if their location matches the one being looked at
		while(iter.hasNext())
		{
			Actor a = iter.next();

			if(a.getX() == x && a.getY() == y)
				return false;
		}

		return true;
	}

	//Starts a new thread and runs the game loop in it.
	private void runGameLoop()
	{
		Thread loop = new Thread()
		{ public void run(){ gameLoop(); } };

		loop.start();
	}

	//Repaints with each render
	private void drawGame(float interpolation)
	{
		this.setInterpolation(interpolation);
		this.repaint();
	}

	private void gameLoop()
	{
		//Time stuff
		final double GAME_HERTZ = 30.0;
		final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
		final int MAX_UPDATES_BEFORE_RENDER = 3;
		double lastUpdateTime = System.nanoTime();
		double lastRenderTime = System.nanoTime();

		//If we are able to get as high as this FPS, don't render again.
		final double TARGET_FPS = 60;
		final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;

		//main game loop
		while (running)
		{
			double now = System.nanoTime();	//current cpu time
			int updateCount = 0;

			//update game to current time
			while( now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER )
			{
				//This is where we will check all actors for action queue
				lastUpdateTime += TIME_BETWEEN_UPDATES;
				updateCount++;
			}

			//Account for CPU lag
			if ( now - lastUpdateTime > TIME_BETWEEN_UPDATES)
				lastUpdateTime = now - TIME_BETWEEN_UPDATES;

			//Render
			float interpolation = Math.min(1.0f, (float) ((now - lastUpdateTime) / TIME_BETWEEN_UPDATES) );
			drawGame(interpolation);
			lastRenderTime = now;

			//Yield until it has been at least the target time between renders.
			while ( now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS && now - lastUpdateTime < TIME_BETWEEN_UPDATES)
			{
				Thread.yield();

				now = System.nanoTime();
			}
		}
	}

	private void toggleRunning(){ running = !running; }

	//menu objects
	private final JPopupMenu popupMenu = new JPopupMenu();
	private final JMenuItem drawRockJMenu = new JMenuItem("Draw Rock here");
	private final JMenuItem drawHumanJMenu = new JMenuItem("Draw Human here");
	private final JMenuItem drawVampireJMenu = new JMenuItem("Draw Vampire here");
	private final JMenuItem drawZombieJMenu = new JMenuItem("Draw Zombie here");

	public float interpolation;	//time stuff
	private boolean running = false; //is the game currently active?

	private int xMouse = 0, yMouse = 0; //(x,y) location of mouse on screen

	private int width;
	private int height;

	private ActorGrid actors;	//ArrayList of actors to be rendered

	//Eclipse yelled at me for not having this
	private static final long serialVersionUID = 1L;
}