
public class Zombie extends Actor
{
	//Creates zombie object with the position
	//	of the zombie being instantiated with
	//	(x,y) coordinates passed to it
	public Zombie(int x, int y)
	{
		super(x, y, IMG_LOC);
	}

	//Creates a zombie at the default location (0,0)
	public Zombie()
	{
		super(0, 0, IMG_LOC);
	}

	//String containing path to zombie-front.gif
	private static final String IMG_LOC = "src/images/zombie-front.gif";

}
