import javax.swing.*;
import java.util.*;
import java.awt.*;

class MonsterWorld
{
	public static void main(String[] args)
	{
		JFrame frame = new WorldFrame(20,20);
        frame.setTitle("MonsterWorld");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setResizable(false);
        frame.setVisible(true);
	}
}