package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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