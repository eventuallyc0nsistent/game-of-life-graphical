import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class gameOfLife
{	
	public static int rows=0 ;
	public static int cols=0 ;

	public static void main(String args[])
	{
		if(args.length == 2) 
		{
			rows = Integer.parseInt(args[0]) ;
			cols = Integer.parseInt(args[1]) ;

			EventQueue.invokeLater(new Runnable()
			{
				public void run()
				{
					//create frame
					JFrame frame = new GameFrame(rows,cols);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);

				}
			});
			
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
	public GameFrame(int rows,int cols)
	{
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setTitle("Game of Life - Graphical");

		// add to menu panel the Next and Clear buttons
		JPanel menu_panel = new JPanel();
		JButton next_btn = new JButton("Next");
		JButton clear_btn = new JButton("Clear");

		// for grid panel set layout to a Grid Layout
		JPanel grid_panel = new JPanel();
		grid_panel.setLayout(new GridLayout(rows,cols));

		// make a grid of buttons  and add buttons to the emptyGrid
		MakeGrid gameGrid = new MakeGrid(rows,cols);
		MakeGrid nextGrid = new MakeGrid(rows,cols);

		this.grid = gameGrid.grid;
		this.nextGenGrid = nextGrid.grid;
		JPanel emptyGridPanel = gameGrid.createEmptyGridOfButtons(rows,cols,grid_panel,grid);

		// action listeners
		clear_btn.addActionListener(new ClearButtonListener(rows,cols,grid));
		next_btn.addActionListener(new NextButtonListener(rows,cols,grid,nextGenGrid));

		menu_panel.add(next_btn);
		menu_panel.add(clear_btn);
		add(menu_panel, BorderLayout.NORTH);
		add(emptyGridPanel);

	}


	public void setGridButtonArray(JButton[][] new_grid)
	{
		this.grid = new_grid;
	}


	/*
	Set height and width of frame
	 */
	public static final int DEFAULT_HEIGHT = 200;
	public static final int DEFAULT_WIDTH = 300;
	private JButton[][] grid ;
	private JButton[][] nextGenGrid ;
}

/*
make a grid
 */
class MakeGrid
{
	static JButton[][] grid;

	public MakeGrid(int rows, int cols)
	{
		grid = new JButton[rows][cols];
	}

	/*
	Create grid of JButtons
	 */
	
	public JPanel createEmptyGridOfButtons(int rows, int cols, JPanel grid_panel, JButton[][] grid)
	{
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

	public JButton[][] getGridButtonArray()
	{
		return grid;
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

/*
Listener for Clear button on the frame
 */
class ClearButtonListener implements ActionListener
{
	private int rows;
	private int cols;
	private JButton[][] grid;

	public ClearButtonListener(int rows, int cols, JButton[][] grid)
	{
		this.rows = rows;
		this.cols = cols;
		this.grid = grid;
	}

	public void actionPerformed(ActionEvent e)
	{
		for(int i=0; i < rows; i++)
		{
			for(int j=0; j< cols; j++)
			{
				grid[i][j].setBackground(Color.WHITE);
			}
		}
	}
}

class NextButtonListener implements ActionListener
{
	private int rows;
	private int cols;
	private JButton[][] grid;
	private JButton[][] nextGenGrid;
	ActionListener buttonListener = new GridButtonListener();

	public NextButtonListener(int rows, int cols, JButton[][] old_grid, JButton[][] nextGenGrid)
	{
		this.rows = rows;
		this.cols = cols;
		this.grid = old_grid;
		this.nextGenGrid = nextGenGrid;
	}

	public void actionPerformed(ActionEvent e)
	{
		for(int i=0; i < this.rows; i++)
		{
			for(int j=0; j< this.cols; j++)
			{
				/*
				Check for Alive cells
				 */
				if(this.grid[i][j].getBackground() == Color.BLACK)
				{
					String alive = "ALIVE";
					boolean isAlive = this.isAlive(alive,i,j,grid,rows,cols);
					if(isAlive)
					{
						nextGenGrid[i][j] = new JButton();
						nextGenGrid[i][j].setBackground(Color.BLACK);
						nextGenGrid[i][j].addActionListener(buttonListener);
					}	
				}

				/*
				Check for Dead cells
				 */
				if(this.grid[i][j].getBackground() == Color.WHITE)
				{
					String dead = "DEAD";
					boolean isAlive = this.isAlive(dead,i,j,grid,rows,cols);
					if(isAlive)
					{
						nextGenGrid[i][j] = new JButton();
						nextGenGrid[i][j].setBackground(Color.WHITE);
						nextGenGrid[i][j].addActionListener(buttonListener);
					}	
				}

				System.out.println(nextGenGrid[i][j].getBackground());
					
			} 

		}

		this.grid.setGridButtonArray(nextGenGrid);
	}

	/*
	{x-1,y-1}       {x-1,y}         {x-1,y+1} 
     {x,y-1}         {x,y}           {x,y+1}
    {x+1,y-1}       {x+1,y}         {x+1,y+1}
	 */
	public boolean isAlive(String deadOrAlive, int x,int y, JButton[][] grid,int rows , int cols)
	{
		int neighborCount = 0;

		if(x-1 >= 0 && y-1 >= 0)
			if(grid[x-1][y-1].getBackground() == Color.BLACK)
				neighborCount++;

		if(x-1 >= 0 )
			if(grid[x-1][y].getBackground() == Color.BLACK)
				neighborCount++;

		if(x-1 >= 0 && y+1 < cols)
			if(grid[x-1][y+1].getBackground() == Color.BLACK)
				neighborCount++;

		if(y-1 >= 0)
			if(grid[x][y-1].getBackground() == Color.BLACK)
				neighborCount++;

		if(y+1 < cols)
			if(grid[x][y+1].getBackground() == Color.BLACK)
				neighborCount++;

		if(x+1 < rows && y-1 >= 0)	
			if(grid[x+1][y-1].getBackground() == Color.BLACK)
				neighborCount++;

		if(x+1 < rows)
			if(grid[x+1][y].getBackground() == Color.BLACK)
				neighborCount++;

		if(x+1 < rows && y+1 < cols)
			if(grid[x+1][y+1].getBackground() == Color.BLACK)
				neighborCount++;

		if((neighborCount == 2 || neighborCount == 3) && deadOrAlive == "ALIVE")
			return true;
		else if (neighborCount == 3 && deadOrAlive == "DEAD")
			return true;
		else 
			return false;
	}
}