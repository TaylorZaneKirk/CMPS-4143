
public class Human extends Actor
{

	//Creates human object with the position
	//	of the human being instantiated with
	//	(x,y) coordinates passed to it
	public Human(int x, int y)
	{
		super(x, y, IMG_LOC);
	}

	//Creates a human at the default location (0,0)
	public Human()
	{
		super(0, 0, IMG_LOC);
	}

	//String containing path to human-front.gif
	private static final String IMG_LOC = "src/images/human-front.gif";

}
