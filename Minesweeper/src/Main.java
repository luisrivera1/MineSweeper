import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame myFrame = new JFrame("Anthony's and Luis MineSweeper");
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame.setLocation(400, 150);
		myFrame.setSize(400, 400);

		MyPanel myPanel = new MyPanel();
		myFrame.add(myPanel);
		
		Mines myMines = new Mines();
		
		MyMouseAdapter myMouseAdapter = new MyMouseAdapter(myMines);
		myFrame.addMouseListener(myMouseAdapter);

		myFrame.setVisible(true);
	}
}