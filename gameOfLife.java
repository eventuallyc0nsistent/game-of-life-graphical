import java.awt.*;
import javax.swing.*;

class gameOfLife
{	
	private static int rows ;
	private static int cols ;

	public static void main(String args[])
	{
		if(args.length == 2) 
		{
			rows = Integer.parseInt(args[0]) ;
			cols = Integer.parseInt(args[1]) ;
		} 
		else 
		{
			System.out.println("Need to pass more arguments");
			System.exit(0);
		}

		JFrame frame =new gameFrame();
		frame.setVisible(true);

	}
}

class gameFrame extends JFrame
{
	public gameFrame()
	{
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setTitle("Game of Life - Graphical");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel menu_panel = new JPanel();
		JButton next_btn = new JButton("Next");
		JButton clear_btn = new JButton("Clear");

		menu_panel.add(next_btn);
		menu_panel.add(clear_btn);

		add(menu_panel, BorderLayout.NORTH);
	}

	public static final int DEFAULT_HEIGHT = 200;
	public static final int DEFAULT_WIDTH = 300;
}