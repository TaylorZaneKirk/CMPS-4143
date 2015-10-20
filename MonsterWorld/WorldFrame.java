import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

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






