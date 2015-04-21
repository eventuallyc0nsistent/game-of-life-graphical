package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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
		grid_panel.setLayout(new GridLayout(rows, cols));

		// make a grid of buttons  and add buttons to the emptyGrid
		// create instances of game grid and next generation grid to use
		MakeGrid gameGrid = new MakeGrid(rows,cols);
		JPanel emptyGridPanel = this.createEmptyGridOfButtons(rows, cols, grid_panel, gameGrid.grid);
		gameGrid.setGridPanel(grid_panel);

		// action listeners
		clear_btn.addActionListener(new ClearButtonListener(rows,cols, gameGrid.grid));
		next_btn.addActionListener(new NextButtonListener(rows, cols, gameGrid));

		menu_panel.add(next_btn);
		menu_panel.add(clear_btn);
		add(menu_panel, BorderLayout.NORTH);
		add(emptyGridPanel);
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

	/*
	Set height and width of frame
	 */
	public static final int DEFAULT_HEIGHT = 200;
	public static final int DEFAULT_WIDTH = 300;
	private JButton[][] grid ;
	private JButton[][] nextGenGrid;
	private JPanel emptyGridPanel;
}
