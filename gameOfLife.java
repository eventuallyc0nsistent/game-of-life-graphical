import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class gameOfLife
{	
	private static int rows=0 ;
	private static int cols=0 ;

	public static void main(String args[])
	{
		if(args.length == 2) 
		{
			rows = Integer.parseInt(args[0]) ;
			cols = Integer.parseInt(args[1]) ;

			//create frame
			JFrame frame = new GameFrame();
			
			// for grid panel set layout to a Grid Layout
			JPanel grid_panel = new JPanel();
			grid_panel.setLayout(new GridLayout(rows,cols));

			// make a grid of buttons  and add buttons to the emptyGrid
			MakeGrid gameGrid = new MakeGrid();
			JPanel emptyGrid = gameGrid.createEmptyGridOfButtons(rows,cols,grid_panel);

			frame.add(emptyGrid);
			frame.setVisible(true);
		} 
		else 
		{
			System.out.println("Need to pass more arguments");
			System.exit(0);
		}

	}

}

class GameFrame extends JFrame
{
	public GameFrame()
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

/*
make a grid
 */
class MakeGrid
{

	/*
	Create grid of JButtons
	 */
	public JPanel createGridOfButtons(int rows, int cols, JPanel grid_panel)
	{
		JButton[][] grid = new JButton[rows][cols];

		for (int i=0; i < rows; i++)
		{
			for(int j=0 ; j < cols ; j++)
			{
				grid[i][j] = new JButton();
				grid[i][j].setBackground(Color.WHITE);
				grid_panel.add(grid[i][j]);
			}
		}

		return grid_panel;
	}

	public JPanel createEmptyGridOfButtons(int rows, int cols, JPanel grid_panel)
	{
		JButton[][] grid = new JButton[rows][cols];
		ActionListener buttonListener = new GridButtonListener();

		for (int i=0; i < rows; i++)
		{
			for(int j=0 ; j < cols ; j++)
			{
				grid[i][j] = new JButton();
				grid[i][j].setBackground(Color.WHITE);
				grid[i][j].addActionListener(buttonListener);
				grid_panel.add(grid[i][j]);
			}
		}

		return grid_panel;
	}
}

class GridButtonListener implements ActionListener
{
	public void actionPerformed(ActionEvent e)
	{
		if(((JButton)e.getSource()).getBackground() == Color.WHITE)
			((JButton)e.getSource()).setBackground(Color.BLACK);
		else
			((JButton)e.getSource()).setBackground(Color.WHITE);
	}
}