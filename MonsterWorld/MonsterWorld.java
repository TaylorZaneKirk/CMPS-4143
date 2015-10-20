import javax.swing.*;

class MonsterWorld
{

	private final JFrame frame = new JFrame();

	public static void main(String[] args)
	{

		WorldFrame frame = new WorldFrame(20,20);
		frame.setTitle("MonsterWorld");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true); 

	}
}