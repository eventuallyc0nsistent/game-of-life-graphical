import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.*;


class GameOfLifeGui extends JFrame {
private int rows;
private int columns;
/*Constructor*/
public GameOfLifeGui(final int rows, final int columns) {
this.rows=rows;
this.columns=columns;
setSize(600, 600); // width, height
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setTitle("GameOfLife!!!");
JButton startbutton= new JButton("Start");
JButton clearbutton= new JButton("Clear");
JPanel panel= new JPanel();
JPanel gridPanel= new JPanel();
final JButton[][] gridButton;
final JButton[][] copyOfGrid;
final JTextField numOfGen=new JTextField(3);
JLabel numOfGenlabel=new JLabel("Number of generations :");


gridPanel.setLayout(new GridLayout(rows,columns));

add(panel, BorderLayout.NORTH);
add(gridPanel);
panel.add(numOfGenlabel);
panel.add(numOfGen);

panel.add(startbutton);
panel.add(clearbutton);
gridButton=new JButton[rows+2][columns+2]; //allocate the size of grid
copyOfGrid=new JButton[rows+2][columns+2];


	for(int x=1; x<=rows; x++)
	{
		for(int y=1; y<=columns; y++)
		{
             gridButton[x][y]=new JButton(""); //creates new button  
			 gridButton[x][y].setBackground(Color.white);
			 gridPanel.add(gridButton[x][y]); //adds button to grid
        }
	}
	/* Listener for selecting the cells(buttons) on the grid to make cells alive(Red) */
	ActionListener listener = new GridButtonClickedListener();
		for(int x=1; x<=rows; x++)
		{
           for(int y=1; y<=columns; y++)
		   {
			gridButton[x][y].addActionListener(listener);
								//System.out.println(gridButton[x][y]);
			}
		}
		/* Listener for Clear Button to make the cells as Dead(white) */
	clearbutton.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			for(int i=1;i<=rows;i++)
			{
              for(int j=1;j<=columns;j++)

              gridButton[i][j].setBackground(Color.white);

            }
		}
    });
	
	/* Listener for Start Button to create the generation outputs */
	ActionListener generationsListener = new ActionListener() {
			int numofGenerations;
			Timer timer = new Timer(100, new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(numofGenerations > 0){
						//numofGenerations--;
						numOfGen.setText(""+numofGenerations--);
						performGenerations(gridButton,copyOfGrid,rows,columns);
					}
					else{
						timer.stop();
						numOfGen.setText("0");
					}
				}
			});

			public void actionPerformed(ActionEvent e) {
				if(numOfGen.getText().length() == 0)
					numofGenerations = 1; // If the field is kept blank then it is considered as a single generation
				else
					numofGenerations = Integer.parseInt(numOfGen.getText()); 
				
				timer.start();
			}
		};
		startbutton.addActionListener(generationsListener);
								   
}
			/**
     * performGenerations: This function counts the neighbours and depending on count makes a cell as dead(White) or alive(Red) 
     * @params:Old button array, CopyofbuttonArray, rows and columns of the grid
     *       
     */
		public void performGenerations(JButton[][] gridButton,JButton[][] copyOfGrid ,int rows,int columns)
			{
				copyOldGridToNew(gridButton,copyOfGrid,rows,columns);
				 for(int i=1;i<=rows;i++)
					for(int j=1;j<=columns;j++)
			{
				int neighbour=0;
                                        // gridButton[i][j].setBackground(Color.white);
					if (copyOfGrid[i - 1][ j - 1].getBackground()==Color.red)
						neighbour++;
					if (copyOfGrid[i - 1][ j].getBackground()==Color.red)
                    {
                        neighbour++;
                    }
                    if (copyOfGrid[i - 1][ j + 1].getBackground()==Color.red)
                    {
                        neighbour++;
                    }
                    if (copyOfGrid[i][j - 1].getBackground()==Color.red)
                    {
                        neighbour++;
                    }
                    if (copyOfGrid[i][j + 1].getBackground()==Color.red)
                    {
                        neighbour++;
                    }
                    if (copyOfGrid[i + 1][j - 1].getBackground()==Color.red)
                    {
                        neighbour++;
                    }
                    if (copyOfGrid[i + 1][j].getBackground()==Color.red)
                    {
                        neighbour++;
                    }
                    if (copyOfGrid[i + 1][j + 1].getBackground()==Color.red)
                    {
                        neighbour++;
                    }
						if(copyOfGrid[i][j].getBackground()==Color.red)
						{
						if(neighbour==2 ) gridButton[i][j].setBackground(Color.red);

                        else if(neighbour<2||neighbour>3) gridButton[i][j].setBackground(Color.white);
						}
						else
						{
						if(neighbour==3) gridButton[i][j].setBackground(Color.red);
						}
            }
			}
		/**
     * copyOldGridToNew: This function will create a copy of GridButtonArray .
     * @params:Old Gridbutton array, New Gridbuttonarray, rows and columns of the grid
     *       
     */
		public void copyOldGridToNew(JButton[][] gridButton,JButton[][] copyOfGrid ,int rows,int columns)
		{
            for(int i=0;i<rows+2;i++)
                   for(int j=0;j<columns+2;j++)
                   {
                     copyOfGrid[i][j]=new JButton();
                     copyOfGrid[i][j].setBackground(Color.white);
                    }
                                                            
			for(int i=1;i<=rows;i++)
					for(int j=1;j<=columns;j++)
					{
                                                                   
                                                                    //System.out.println(gridButton[i][j].getBackground());
						if(gridButton[i][j].getBackground()==Color.red)
							copyOfGrid[i][j].setBackground(Color.red);
						else if(gridButton[i][j].getBackground()==Color.white)
							copyOfGrid[i][j].setBackground(Color.white);
						else
							copyOfGrid[i][j].setBackground(Color.white);
					}
		}
}
/*This class sets the selected cell(button) as alive(red) or dead(white) */
 class GridButtonClickedListener implements ActionListener {
  public void actionPerformed(ActionEvent e) {
     //System.out.println("Button pressed: " + (e.getSource()));
	 if(((JButton)e.getSource()).getBackground()== Color.white)
			((JButton)e.getSource()).setBackground(Color.red);
	else
			((JButton)e.getSource()).setBackground(Color.white);
     //((JButton)e.getSource()).setEnabled(false);
	 
  }
}

class GameOfLifeTest{
public static void main(String[] args){

final int rows=Integer.parseInt(args[0]);
final int columns=Integer.parseInt(args[1]);


EventQueue.invokeLater(new Runnable() {
public void run() {
GameOfLifeGui myj = new GameOfLifeGui(rows,columns);
myj.setLocation(300, 300); // x, y
myj.setVisible(true);



}
});
}

}   
                         