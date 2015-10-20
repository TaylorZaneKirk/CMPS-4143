import java.awt.*;
import javax.swing.*;

class WorldFrame extends JFrame
{
	public WorldFrame()
	{
		setResizable(false);
		height = 10;
		width = 10;
		
		add(new WorldGrid(width, height));
		pack();
	}
	
	public WorldFrame(int sizeX, int sizeY)
	{
		setResizable(false);
		height = sizeY;
		width = sizeX;
		
		add(new WorldGrid(width, height));
		pack();
	}

	private int height;
	private int width;
}