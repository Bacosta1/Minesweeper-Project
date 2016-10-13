import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JFrame;

public class MyMouseAdapter extends MouseAdapter {
	//private Random generator = new Random();
	public void mousePressed(MouseEvent e) {

		Component c = e.getComponent();
		while (!(c instanceof JFrame)) {
			c = c.getParent();
			if (c == null) {
				return;
			}
		}
		JFrame myFrame = (JFrame) c;
		MinesweeperPanel myPanel = (MinesweeperPanel) myFrame.getContentPane().getComponent(0);
		Insets myInsets = myFrame.getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		e.translatePoint(-x1, -y1);
		int x = e.getX();
		int y = e.getY();
		myPanel.x = x;
		myPanel.y = y;
		myPanel.mouseDownGridX = myPanel.getGridX(x, y);
		myPanel.mouseDownGridY = myPanel.getGridY(x, y);
		myPanel.repaint();
	}
	public void mouseReleased(MouseEvent e) {
		
		Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame)c;
			MinesweeperPanel myPanel = (MinesweeperPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			int gridX = myPanel.getGridX(x, y);
			int gridY = myPanel.getGridY(x, y);
		switch (e.getButton()) {
		case 1:		//Left mouse button
			
			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
				//Had pressed outside
				//Do nothing
			} else {
				if ((gridX == -1) || (gridY == -1)) {
					//Is releasing outside
					//Do nothing
				} else {
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					} else {
						//Released the mouse button on the same cell where it was pressed

						Color newColor = null;
						if(myPanel.mineLocation[myPanel.mouseDownGridX][myPanel.mouseDownGridY] == true){
							for (int i=0; i<9; i++){
								for(int j=0; j<9; j++){
									if(myPanel.mineLocation[i][j]==true){
										newColor = Color.BLACK;
										myPanel.colorArray[i][j] = newColor;
										myPanel.repaint();
									}
								}
							}
							System.out.println("You lose");
						}
						else{
							if(myPanel.surroundedByMine(myPanel.mouseDownGridX, myPanel.mouseDownGridY )==true){ 
								newColor = Color.BLUE;
							}
							else{
								for(int i=myPanel.mouseDownGridX-1; i<(myPanel.mouseDownGridX+2); i++){
									for(int j=myPanel.mouseDownGridY-1; j<(myPanel.mouseDownGridY+2); j++){
										if(i <0 || j <0 || i > 8 || j > 8){
											//Do nothing
										}
										else{
											if(myPanel.surroundedByMine(i, j)==true){
												newColor = Color.GREEN;
												myPanel.colorArray[i][j] = newColor;
												myPanel.repaint();
											}
											else{
												newColor = Color.LIGHT_GRAY;
												myPanel.colorArray[i][j] = newColor;
												myPanel.repaint();
											}
										}									
									}
								}
							}
						}

						myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
						myPanel.repaint();

					}
				}
			}
			myPanel.repaint();
			break;
		case 3:		//Right mouse button
			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
				//Had pressed outside
				//Do nothing
			} else {
				if ((gridX == -1) || (gridY == -1)) {
					//Is releasing outside
					//Do nothing
				} else {
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					} else {
						//Released the mouse button on the same cell where it was pressed
						Color newColor = null;
						newColor = Color.RED;
						myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
						myPanel.repaint();


					}
				}
			}
			break;
			
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
}
