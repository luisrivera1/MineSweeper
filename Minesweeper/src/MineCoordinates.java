import java.util.ArrayList;
import java.util.Random;

public class MineCoordinates {
	private int x=0;
	private int y=0;
	
	Random generator = new Random();
	
	ArrayList<MineCoordinates> mine = new ArrayList<MineCoordinates>();
	public MineCoordinates(){
		
	}
	public MineCoordinates (int x, int y){
		this.x=x;
		this.y=y;
		
		}


	public int getxPos() {
		return generator.nextInt(9);
	}

	public int getyPos() {
		return generator.nextInt(9);
	}

	public MineCoordinates MineCoordinates () {
		
		return new MineCoordinates(getxPos(), getyPos());

	}
	
	public ArrayList<MineCoordinates> coordinates (){
		
		for (int i = 0; i < 10; i++) {
			
			mine.add(MineCoordinates());
			System.out.println("("+mine.get(i).getX()+","+mine.get(i).getY()+")\n");
		}
		return mine;	
		
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}


	
	
}
