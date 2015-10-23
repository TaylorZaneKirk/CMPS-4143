import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WorldFrame extends JFrame
{
	public WorldFrame()
	{
		super("Monster Simulation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1,2));
		cp.add(worldGrid, BorderLayout.CENTER);
		cp.add(p, BorderLayout.SOUTH);
		p.add(startButton);

		//10 x 10 and 500 x 600 works well for my laptop
		//	feel free to change if you need to.
		setSize(500, 600);

		//When start button is pressed, call gameLoop()
		startButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				worldGrid.pressedStart();
				if(worldGrid.isRunning())
				{
					startButton.setText("Stop");
				}
				else
				{
					startButton.setText("Start");
				}
			}
		});
	}
	
	private WorldGrid worldGrid = new WorldGrid();

	private JButton startButton = new JButton("Start");
	
	//Eclipse yelled at me for not having this
	private static final long serialVersionUID = 1L;
}