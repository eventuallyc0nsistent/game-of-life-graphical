package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class GameOfLife
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
					JFrame frame = new GameFrame(rows, cols);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
				}
			});
			
		} 
		else 
		{
			System.out.println("Program takes two arguments, try:");
			System.out.println("java game.GameOfLife 5 5");
			System.exit(0);
		}

	}

}




