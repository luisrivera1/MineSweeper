import java.util.ArrayList;
import java.util.Random;

public class MineCoordinates {
	private int x;
	private int y;
	private int xPos;
	private int yPos;
	Random generator = new Random();
	
	ArrayList<MineCoordinates> mine = new ArrayList<MineCoordinates>();
	
	
	public MineCoordinates (){
		this.x=0;
		this.y=0;
		
		}
	
	public MineCoordinates (int x, int y){
		this.x=x;
		this.y=y;
		
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
	
	public ArrayList<MineCoordinates> coordinates (){
		
		for (int i = 0; i < 10; i++) {
			
			mine.add(genCoordinates());
			//System.out.println("("+mine[i].xPos+","+mine[i].yPos+")");
		}
		return mine;	
		
	}

	public void compareCoordinates (){
		
		
		
	}
	
	
}
