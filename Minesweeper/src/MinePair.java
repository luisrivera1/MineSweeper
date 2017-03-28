public class MinePair {
	
	public int x;
	public int y;
	
	public MinePair(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean isEqual(MinePair pair) {
		return ((this.x == pair.x) && (this.y == pair.y));
	}
}