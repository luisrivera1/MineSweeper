import java.util.Random;

public class MineCoordinates {
	private int x;
	private int y;
	private int xPos;
	private int yPos;
	Random generator = new Random();
	 MineCoordinates mine [] = new MineCoordinates[10] ;
	
	
	public MineCoordinates ( int x, int y){
		
		this.xPos= x;
		this.yPos = y;
		
		}


	public int getxPos() {
		return generator.nextInt(9);
	}


	public void setxPos(int xPos) {
		this.xPos = xPos;
	}


	public int getyPos() {
		return generator.nextInt(9);
	}


	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	
	public MineCoordinates genCoordinates () {
		
		return new MineCoordinates(getxPos(), getyPos());

	}
	
	public void assignCoordinates (){
		
		for (int i = 0; i < 10; i++) {
			
			mine[i] = genCoordinates();
			System.out.println("("+mine[i].xPos+","+mine[i].yPos+")");
		}
		
	}

}
