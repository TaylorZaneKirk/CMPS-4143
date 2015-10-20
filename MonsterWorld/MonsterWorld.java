import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

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