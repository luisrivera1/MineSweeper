import java.util.Random;

import javax.swing.JOptionPane;

public class Mines {
	
	private static final int TOTAL_COLUMNS = 9;
	private static final int TOTAL_ROWS = 9;
	private static final int TOTAL_MINES = 10;
	private MinePairArray minePositions = new MinePairArray(TOTAL_MINES);
	public int[][] minesArray = new int[TOTAL_COLUMNS][TOTAL_ROWS];
	
	
	public Mines() { //This is the constructor... this code runs first to initialize
		
		for (int x = 0; x < TOTAL_COLUMNS; x++) {   //Fill the array with 0
			for (int y = 0; y < TOTAL_ROWS; y++) {
				minesArray[x][y] = 0;
			}
		}
		while (minePositions.getNumCount() < TOTAL_MINES) { //Generate the random coordinates for mines
			int x = (new Random()).nextInt(9);
			int y = (new Random()).nextInt(9);
			MinePair pair = new MinePair(x, y);
		minePositions.addPair(pair);  //The positions are stored as OrderedPairs
		}
		for (MinePair p : getMinePositions()) {
			minesArray[p.x][p.y] = 1;
		}
	}
	
	public boolean isMine(MinePair p) {
		return minesArray[p.x][p.y] > 0;
	}
	public void clickedCell(MinePair p) {
		if (!isMine(p))
			minesArray[p.x][p.y] = -1;
	}
	public boolean areAllCellsPressed() {
		for (int x = 0; x < TOTAL_COLUMNS; x++) {   //Fill the array with 0
			for (int y = 0; y < TOTAL_ROWS; y++) {
				if (minesArray[x][y] == 0)
					return false;
			}
		}
		return true;
	}
	public int getTotalMines() {
		return TOTAL_MINES;
	}
	public MinePair[] getMinePositions() {
		return minePositions.getPairArray();
	}
	public void mineExploted() {
		JOptionPane.showMessageDialog(null,"MINE TRIGERED","YOU LOSE",JOptionPane.PLAIN_MESSAGE);
	
	}
	public void noMineExploted() {
		JOptionPane.showMessageDialog(null,"YOU WON!!!","VICTORY",JOptionPane.PLAIN_MESSAGE);
	}
	
	public int findMinesAround(MinePair minePos) {
		//Return the number of Mines around the cell which was pressed.
		int mineCounter = 0;
		for (int x = -1; x < 2; x++) {
			for (int y = -1; y < 2; y++) {
				int cellX = minePos.x + x;
				int cellY = minePos.y + y;
				MinePair aroundMine = new MinePair(cellX, cellY);
				
				if (cellX >= 0 && cellY >= 0 && cellX < TOTAL_COLUMNS && cellY < TOTAL_ROWS) {
					if (isMine(aroundMine)) {
						mineCounter++;
					}
				}
			}
		}
		return mineCounter; //
	}
	
	public MinePairArray findBlocksAround(MinePair p, MinePairArray pArray) {
		//Return an OrderedPairArray of the positions of the empty squares
		for (int x = -1; x < 2; x++) {
			for (int y = -1; y < 2; y++) {
				int cellX = p.x + x;
				int cellY = p.y + y;
				MinePair aroundMine = new MinePair(cellX, cellY);

				if (cellX >= 0 && cellY >= 0 && cellX < TOTAL_COLUMNS && cellY < TOTAL_ROWS) { //The cell is inside the grid
					if (cellX != p.x || cellY != p.y) { //The cell is around the center pair and not the center itself
						pArray.addPair(aroundMine);
					}
				}
			}
		}
		return pArray;
	}
	
	public MinePairArray uncoverEmptyBlocks(MinePair p) { //Uncovers all the empty cells
															    // around an empty cell
		MinePairArray firstBlocks = findBlocksAround(p, new MinePairArray(8));
		MinePairArray emptyBlocksArray = new MinePairArray(8);
		MinePairArray numberedBlocksArray = new MinePairArray(8);
		
		for (int i=0; i < firstBlocks.getNumCount(); i++) {
			MinePair firstPairs = firstBlocks.getPairArray()[i];
			if (findMinesAround(firstPairs) == 0) {
				emptyBlocksArray.addPair(firstPairs);
			}
			else if (!isMine(firstPairs)) {
				numberedBlocksArray.addPair(firstPairs);
			}
		}
		
		for (int i=0; i < emptyBlocksArray.getNumCount(); i++) { //Iterates through all the empty cells recursively (in a sense!)
			
			MinePair emptyPairs = emptyBlocksArray.getPairArray()[i];
			MinePairArray aroundBlocks = findBlocksAround(emptyPairs, new MinePairArray(8));
			
			for (int j=0; j < aroundBlocks.getNumCount(); j++) {
				MinePair aroundPairs = aroundBlocks.getPairArray()[j];
				if (findMinesAround(aroundPairs) == 0) {
					emptyBlocksArray.addPair(aroundPairs);
				}
				else if (!isMine(aroundPairs)) {
					numberedBlocksArray.addPair(aroundPairs);
				}
			}
		}
		return emptyBlocksArray.union(numberedBlocksArray);
		
	}
	
	
	
}