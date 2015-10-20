import java.awt.*;

import javax.swing.*;

class Actor implements iActor
{
	public Actor()
	{
		x = y = 0;
		displayImage = new ImageIcon("default.gif").getImage();
	}
	
	public Actor(int x, int y, String filename)
	{
		this.x = x;
		this.y = y;
		displayImage = new ImageIcon(filename).getImage();
	}
	
	public void setPos(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void setImage(String filename)
	{
		displayImage = new ImageIcon(filename).getImage();
	}
	
	public Image getImage()
	{
		return displayImage;
	}
	
	public int getX() {return x;}
	
	public int getY() {return y;}
	
	int x;
	int y;
	Image displayImage;
}