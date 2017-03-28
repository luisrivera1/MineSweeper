import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class MyMouseAdapter extends MouseAdapter {

	
	private int counter = 0;
	private boolean state=true; 
	public boolean getState(){ return state;}
	MyPanel myPanel = new MyPanel();

	ArrayList<MineCoordinates> mines = new MineCoordinates().ScatterMinesCoordinates(); // Arraylist mines

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
				} else { 		//inside does:

					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) { //release on other cell
					} else {		//Released the mouse button on the same cell where it was pressed

						//functional code begins here
						
					    
					    
						for(int i=0; i<mines.size(); i++) {

							if((mines.get(i).getX() == gridX) && (mines.get(i).getY() == gridY)){
								myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.BLACK;
								myPanel.repaint();
								System.out.println("Explosion: "+gridX+" , "+gridY);
								JOptionPane.showMessageDialog(myFrame, "Game Over");
								myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								System.exit(0); 
								state = false;

							}


							else {
								myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.LIGHT_GRAY;


								if (mines.get(i).getX() == gridX+1 && mines.get(i).getY()== gridY-1){
									counter++;
									
								}
								if (mines.get(i).getX()== gridX+1 && mines.get(i).getY()==gridY+1){
									counter++;										
								}
								if (mines.get(i).getX() == gridX-1 && mines.get(i).getY()==gridY+1){
									counter++;										
								}
								if (mines.get(i).getX()==gridX-1 && mines.get(i).getY()==gridY-1){
									counter++;										
								}
								if (mines.get(i).getX()==gridX && mines.get(i).getY()==gridY+1){
									counter++;										
								}
								if (mines.get(i).getX()==gridX && mines.get(i).getY()==gridY-1){
									counter++;	
								}
								if (mines.get(i).getX()+1==gridX && mines.get(i).getY()==gridY){
									counter++;	
								}
								if (mines.get(i).getX()-1==gridX && mines.get(i).getY()==gridY){
									counter++;	
								}
								
								

							}
							myPanel.repaint();
						} 
						System.out.println(counter);

						
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
	public int getCounter()
	{
		return counter;
	}
}