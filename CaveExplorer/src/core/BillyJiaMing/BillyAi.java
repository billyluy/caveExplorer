package core.BillyJiaMing;

public class BillyAi {

	public static String[][] placeShip() {
		//String[][] map = EventBillyJiaMing.botMap;
		int rand1 = (int)Math.random()*10 +1;
		int rand2 = (int)Math.random()*10 +1;
		EventBillyJiaMing.botMap[rand1][rand2] = "X";
		int randDir = (int)Math.random()*4 +1;
		nextPos(randDir, rand1, rand2);
			
		return EventBillyJiaMing.botMap;
	}

	private static void nextPos(int randDir, int x, int y) {
		if(randDir == 1 && y-1>-1){
			EventBillyJiaMing.botMap[x][y-1] = "X";
		}else if(randDir ==2 && x-1>-1){
			EventBillyJiaMing.botMap[x-1][y] = "X";
		}
		else if(randDir ==3 && y+1<11){
			EventBillyJiaMing.botMap[x][y+1] = "X";
		}
		else if(randDir ==4 && x+1<11){
			EventBillyJiaMing.botMap[x+1][y] = "X";
		}
	}

	public static void updateUserHit() {
		// TODO Auto-generated method stub
		
	}

}
