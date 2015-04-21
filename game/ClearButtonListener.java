package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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