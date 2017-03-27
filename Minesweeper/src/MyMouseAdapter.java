import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MyMouseAdapter extends MouseAdapter {
	Main main = new Main();
	boolean state=true; 
	public boolean getState(){
		return state;
	}
	MyPanel myPanel = new MyPanel();
	ArrayList<MineCoordinates> mines = new MineCoordinates().coordinates();
	
	
	public int minesCloseBy(ArrayList<MineCoordinates> mines){
		int counter = 0;
		for(int i=0; i<mines.size(); i++){
			if(mines.get(i).getxPos()==myPanel.mouseDownGridX-1){
				counter++;
			}
			if (mines.get(i).getxPos()==myPanel.mouseDownGridX+1){
				counter++;										
			}
			if (mines.get(i).getxPos()==myPanel.mouseDownGridX+1 && mines.get(i).getyPos()==myPanel.mouseDownGridY-1){
				counter++;										
			}
			if (mines.get(i).getxPos()==myPanel.mouseDownGridX+1 && mines.get(i).getyPos()==myPanel.mouseDownGridY+1){
				counter++;										
			}
			if (mines.get(i).getxPos()==myPanel.mouseDownGridX-1 && mines.get(i).getyPos()==myPanel.mouseDownGridY+1){
				counter++;										
			}
			if (mines.get(i).getxPos()==myPanel.mouseDownGridX-1 && mines.get(i).getyPos()==myPanel.mouseDownGridY-1){
				counter++;										
			}
			if (mines.get(i).getxPos()==myPanel.mouseDownGridX && mines.get(i).getyPos()==myPanel.mouseDownGridY+1){
				counter++;										
			}
			if (mines.get(i).getxPos()==myPanel.mouseDownGridX && mines.get(i).getyPos()==myPanel.mouseDownGridY-1){
				counter++;	
			}
		}
		System.out.println(counter);
		return counter;
	}
	
	public void mousePressed(MouseEvent e) {
		switch (e.getButton()) {
		case 1:		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
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
			myPanel.mouseDownGridX = myPanel.getGridX(x, y);
			myPanel.mouseDownGridY = myPanel.getGridY(x, y);
			myPanel.repaint();
			break;
		case 3:		//Right mouse button
			//Do nothing
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		switch (e.getButton()) {
		case 1:		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame)c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
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
			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
				//Had pressed outside
				//Do nothing
			} else {
				if ((gridX == -1) || (gridY == -1)) { //outside does nothing
				} else { //inside does:
					
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) { //release on other cell
					} else {//Released the mouse button on the same cell where it was pressed
						
					//functional code begins here
						
						for(int a=0; a<mines.size(); a++) {
								 
								  if(mines.get(a).getxPos()==myPanel.mouseDownGridX && mines.get(a).getyPos()==myPanel.mouseDownGridY){									 
									  System.out.println("Explosion: "+gridX+" , "+gridY);
									  state = false;
									  
									  }
										
									  else {
									  myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.BLUE;
									  myPanel.repaint();
									  minesCloseBy(mines);
								  	  }
							      }
							
							if(state){
								//check around
//								int counter = 0;
//								for(int i=0; i>mines.size(); i++){
//									if(mines.get(i).getxPos()==myPanel.mouseDownGridX-1){
//										counter++;
//									}
//									if (mines.get(i).getxPos()==myPanel.mouseDownGridX+1){
//										counter++;										
//									}
//									if (mines.get(i).getxPos()==myPanel.mouseDownGridX+1 && mines.get(i).getyPos()==myPanel.mouseDownGridY-1){
//										counter++;										
//									}
//									if (mines.get(i).getxPos()==myPanel.mouseDownGridX+1 && mines.get(i).getyPos()==myPanel.mouseDownGridY+1){
//										counter++;										
//									}
//									if (mines.get(i).getxPos()==myPanel.mouseDownGridX-1 && mines.get(i).getyPos()==myPanel.mouseDownGridY+1){
//										counter++;										
//									}
//									if (mines.get(i).getxPos()==myPanel.mouseDownGridX-1 && mines.get(i).getyPos()==myPanel.mouseDownGridY-1){
//										counter++;										
//									}
//									if (mines.get(i).getxPos()==myPanel.mouseDownGridX && mines.get(i).getyPos()==myPanel.mouseDownGridY+1){
//										counter++;										
//									}
//									if (mines.get(i).getxPos()==myPanel.mouseDownGridX && mines.get(i).getyPos()==myPanel.mouseDownGridY-1){
//										counter++;										
//									}
//								
//							}
							}else{
								//State is false, game is over
								myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.BLACK;
								myPanel.repaint();
								
								JOptionPane.showMessageDialog(myFrame, "GameOver");
								myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								System.exit(0);
							}	
					}
				}
			}
						
		case 3:		//Right mouse button
			//Do nothing
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
}