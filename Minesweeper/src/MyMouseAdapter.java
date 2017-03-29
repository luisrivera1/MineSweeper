import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

public class MyMouseAdapter extends MouseAdapter {
	private Mines myMines;
	private boolean GAME_OVER = false;
	
	public MyMouseAdapter(Mines mines) {//This is the constructor... this code runs first to initialize
		myMines = mines; //Use the Mines object to obtain the position of the mines.
	}
	
	
	private MyPanel getGridComponents(MouseEvent e) {	
		Component c = e.getComponent();
		while (!(c instanceof JFrame)) {
			c = c.getParent();
			if (c == null) {
				return null;
			}
		}
		JFrame myFrame = (JFrame) c;
		MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
		Insets myInsets = myFrame.getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		e.translatePoint(-x1, -y1);
		int x = e.getX();
		int y = e.getY();
		myPanel.x = x;
		myPanel.y = y;
		
		return myPanel;
	}

	public boolean isInsideGrid(MyPanel myPanel, int gridX, int gridY) {

		if ((myPanel.mouseDownGridX != -1) && (myPanel.mouseDownGridY != -1)) {
			//Had not pressed outside
			if ((gridX != -1) && (gridY != -1)) {
				//Is not releasing outside
				if ((myPanel.mouseDownGridX == gridX) && (myPanel.mouseDownGridY == gridY)) {
					//Released the mouse button on the same cell where it was pressed
					return true;
				} 
			}
		} 
		return false;
	}
	
	public void mousePressedPos(MyPanel myPanel, MouseEvent e) {
		//Returns the grid's x and y indices of the position of the mouse
		//The return type is an integer array.
		myPanel.mouseDownGridX = myPanel.getGridX(e.getX(), e.getY());
		myPanel.mouseDownGridY = myPanel.getGridY(e.getX(), e.getY());

	}
	
	public void mousePressed(MouseEvent e) {
		
		MyPanel myPanel = getGridComponents(e); //Obtain all the frame and grid components
		if (myPanel == null) {return;}
		switch (e.getButton()) {
		case 1:		//Left mouse button
			
			mousePressedPos(myPanel, e);
			myPanel.repaint();
			break;
			
		case 3:		//Right mouse button
			mousePressedPos(myPanel, e);
			myPanel.repaint();
			break;
			
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		
		if (GAME_OVER) return; //If mine was pressed do not react to mouse
		MyPanel myPanel = getGridComponents(e); //Obtain all the frame and grid components
		if (myPanel == null) {return;}
		int gridX = myPanel.getGridX(e.getX(), e.getY());
		int gridY = myPanel.getGridY(e.getX(), e.getY());
		MinePair mouseReleasePos = new MinePair(gridX, gridY);
		
		
		switch (e.getButton()) {
		case 1:		//Left mouse button	

			if (isInsideGrid(myPanel, gridX, gridY)) { //If released on the same cell as it was pressed...

				Color oldColor = myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY];

				if (oldColor != Color.RED && oldColor != Color.LIGHT_GRAY) { //The cell does not have a flag or has been
					// previously pressed.
					if (!myMines.isMine(mouseReleasePos)) { 
						//If the cell is not a mine paint it gray and draw the number of mines nearby
						
						myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.LIGHT_GRAY;
						myPanel.numberArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = myMines.findMinesAround(mouseReleasePos);
						myMines.clickedCell(mouseReleasePos);
						
						if (myMines.findMinesAround(mouseReleasePos) == 0){
							
							//If the pressed cell is empty, open recursively all the blank cells.
							MinePairArray emptyBlocks = myMines.uncoverEmptyBlocks(mouseReleasePos);
							
							for (int i = 0; i < emptyBlocks.getNumCount(); i++) {
								MinePair p = emptyBlocks.getPairArray()[i];
								myPanel.colorArray[p.x][p.y] = Color.LIGHT_GRAY;
								myPanel.numberArray[p.x][p.y] = myMines.findMinesAround(p);
								myMines.clickedCell(p);;  //Mark cells as pressed in the mines array
							}
						}
						
						if (myMines.areAllCellsPressed()) {
							myPanel.repaint();
							GAME_OVER = true;
							myMines.noMineExploted();
						}
						
					} else { //The cell is a mine
						myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.BLACK;
						for (MinePair p : myMines.getMinePositions()) { //Show the position of all the mines.
							
							myPanel.colorArray[p.x][p.y] = Color.BLACK;	//Paint black all the mine positions
						}
						myPanel.repaint();
						myMines.mineExploted();
						GAME_OVER = true;
					}
				}
			}
			myPanel.repaint();
			
			break;
			
		case 3:		//Right mouse button

			if (isInsideGrid(myPanel, gridX, gridY)) { //If released on the same cell as it was pressed...
				//Set or remove the RED FLAG
				Color oldColor = myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY];
				if (oldColor == Color.WHITE) {
					myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.RED;
					myPanel.numberArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = -1;
					myPanel.numFlags++;
				} else if (oldColor == Color.RED) {
					myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.WHITE;
					myPanel.numberArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = 0;
					myPanel.numFlags--;
				}	
				myPanel.repaint();
			}
			break;
			
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
}