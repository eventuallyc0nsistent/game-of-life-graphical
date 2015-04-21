package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MakeGrid
{
	public JButton[][] grid;
	public JPanel gpanel; 

	public MakeGrid(int rows, int cols)
	{
		this.grid = new JButton[rows][cols];
	}

	public void setGridButtonArray(JButton[][] new_grid)
	{
		this.grid = new_grid;
	}

	public void setGridPanel(JPanel grid_panel)
	{
		this.gpanel = grid_panel;
	}

	public JButton[][] getGridButtonArray()
	{
		return grid;
	}

}