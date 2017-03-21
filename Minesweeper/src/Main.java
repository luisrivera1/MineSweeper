import java.util.Random;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame myFrame = new JFrame("Luis & Anthony Minesweeper");
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame.setLocation(400, 150);
		myFrame.setSize(400, 400);

Random rand = new Random();
int x = rand.nextInt(9);
int y = rand.nextInt(9);

		MyPanel myPanel = new MyPanel();
		myFrame.add(myPanel);
		MineCoordinates c = new MineCoordinates(x,y);
		c.assignCoordinates();
		
		MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
		myFrame.addMouseListener(myMouseAdapter);

		myFrame.setVisible(true);
	}
}