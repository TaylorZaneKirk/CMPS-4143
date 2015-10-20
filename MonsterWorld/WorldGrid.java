import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

class WorldGrid extends JComponent
{
	   
	public WorldGrid()
	{
		width = 10;
		height = 10;
		
		actors = new ActorGrid();
		
		actors.add(new Rock(0,0));
	}
	
	public WorldGrid(int width,int height)
	{
		this.width = width;
		this.height = height;
		
		actors = new ActorGrid();
		
		actors.add(new Rock(0,0));
	}
	
	public void paintComponent(Graphics g)
	{
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
	      for (Actor a : actors)
	      {
	    	  g2.drawImage(a.getImage(), a.getX()*48, a.getY()*48, null);
	      }
	      repaint();
	}
	
	public Dimension getPreferredSize() { return new Dimension(width * 48, height * 48); }
	
	int width;
	int height;
	ActorGrid actors;
}