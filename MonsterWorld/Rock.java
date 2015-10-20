import java.awt.*;

import javax.swing.*;

public class Rock extends Actor
{

	//Creates Rock object with the position
	//	of the Rock being instantiated with
	//	(x,y) coordinates passed to it
	public Rock(int x, int y)
	{
		super(x, y, IMG_LOC);
	}
	
	//Creates a Rock at the default location (0,0)
	public Rock()
	{
		super(0, 0, IMG_LOC);
	}

	//String containing path to Rock.gif
	private static final String IMG_LOC = "src/rock.gif";
}