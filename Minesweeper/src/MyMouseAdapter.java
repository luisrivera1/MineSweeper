import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

public class MyMouseAdapter extends MouseAdapter {
	boolean state=true; 
	public boolean getState(){
		return state;
	}
	ArrayList<MineCoordinates> mines = new MineCoordinates().coordinates();
	
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
//	public checkAround(){
//		int counter = 0;
//		for(int i=0; i>mines.size(); i++){
//			if(mines.get(i).getxPos()==myPanel.mouseDownGridX-1){
//				counter++;
//			}
//			if (mines.get(i).getxPos()==myPanel.mouseDownGridX+1){
//				counter++;										
//			}
//			if (mines.get(i).getxPos()==myPanel.mouseDownGridX+1 && mines.get(i).getyPos()==myPanel.mouseDownGridY-1){
//				counter++;										
//			}
//			if (mines.get(i).getxPos()==myPanel.mouseDownGridX+1 && mines.get(i).getyPos()==myPanel.mouseDownGridY+1){
//				counter++;										
//			}
//			if (mines.get(i).getxPos()==myPanel.mouseDownGridX-1 && mines.get(i).getyPos()==myPanel.mouseDownGridY+1){
//				counter++;										
//			}
//			if (mines.get(i).getxPos()==myPanel.mouseDownGridX-1 && mines.get(i).getyPos()==myPanel.mouseDownGridY-1){
//				counter++;										
//			}
//			if (mines.get(i).getxPos()==myPanel.mouseDownGridX && mines.get(i).getyPos()==myPanel.mouseDownGridY+1){
//				counter++;										
//			}
//			if (mines.get(i).getxPos()==myPanel.mouseDownGridX && mines.get(i).getyPos()==myPanel.mouseDownGridY-1){
//				counter++;										
//			}
//			
//		return counter;
//		
//	}
	
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
								 
								  if(mines.get(a).getxPos()!=gridX && mines.get(a).getyPos()!=gridY){
									  
									  
									  Color newColor = Color.GRAY;
									  myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
									  myPanel.repaint();
									  
									  
									  }
										
									  else {
									  Color newColor = Color.YELLOW;
									  myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
									  myPanel.repaint();
									  System.out.println("Explosion: "+gridX+" , "+gridY);
								  	  }
							      }
							
							if(state){
								//check around
								int counter = 0;
								for(int i=0; i>mines.size(); i++){
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
							}else{
								//State is false, game is over
								System.out.println("Game Over");
								
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