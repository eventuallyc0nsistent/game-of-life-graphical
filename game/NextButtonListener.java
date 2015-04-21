package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class NextButtonListener implements ActionListener
{
	private int rows;
	private int cols;
	private MakeGrid mgrid;
	public JButton[][] nextGenGrid;

	public NextButtonListener(int rows, int cols, MakeGrid prev_grid)
	{
		this.rows = rows;
		this.cols = cols;
		this.mgrid = prev_grid;
	}

	public void actionPerformed(ActionEvent e)
	{
		this.nextGenGrid = new JButton[rows][cols];
		ActionListener buttonListener = new GridButtonListener();

		for(int i=0; i < this.rows; i++)
		{
			for(int j=0; j< this.cols; j++)
			{
				this.nextGenGrid[i][j] = new JButton();
				this.nextGenGrid[i][j].setBackground(Color.WHITE);
				this.nextGenGrid[i][j].addActionListener(buttonListener);

				/*
				Check for Alive cells
				 */
				if(this.mgrid.grid[i][j].getBackground() == Color.BLACK)
				{
					String alive = "ALIVE";
					boolean isAlive = this.isAlive(alive, i, j, this.mgrid.grid, rows, cols);
					if(isAlive)
					{
						this.nextGenGrid[i][j].setBackground(Color.BLACK);
					}
						
				}

				/*
				Check for Dead cells
				*/
				else if(this.mgrid.grid[i][j].getBackground() == Color.WHITE)
				{
					String dead = "DEAD";
					boolean isAlive = this.isAlive(dead, i, j, this.mgrid.grid, rows, cols);
					if(isAlive)
					{
						this.nextGenGrid[i][j].setBackground(Color.BLACK);
					}
						
				}
				
				else
				{
					this.nextGenGrid[i][j] = new JButton();
					this.nextGenGrid[i][j].setBackground(Color.WHITE);
				}
			}	 
		}

		// set new grid color
		for (int i=0; i < rows; i++)
		{
			for(int j=0 ; j < cols ; j++)
			{
				this.mgrid.grid[i][j].setBackground(this.nextGenGrid[i][j].getBackground());
			}
		}
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

		// System.out.println(x + ":" + y + "=" + neighborCount);

		if((neighborCount == 2 || neighborCount == 3) && deadOrAlive.equals("ALIVE")){
			return true;
		}else if ((neighborCount == 3) && deadOrAlive.equals("DEAD")){
			return true;
		}else{ 
			return false;
		}
	}

}