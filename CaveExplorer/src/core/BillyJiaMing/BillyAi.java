package core.BillyJiaMing;

import core.CaveExplorer;

public class BillyAi {

	public static String[][] placeShip() {
		//String[][] map = EventBillyJiaMing.botShip;
		int rand1 = (int)(Math.random()*10 +1);
		int rand2 = (int)(Math.random()*10 +1);
		while(EventBillyJiaMing.botShip[rand1][rand2].equals("1")){
			CaveExplorer.print("why u run");
			rand1 = (int)(Math.random()*10 +1);
			rand2 = (int)(Math.random()*10 +1);
		}
		EventBillyJiaMing.botShip[rand1][rand2] = "1";
		int randDir = (int)(Math.random()*4 +1);
		int num =1;
		nextPos(randDir, rand1, rand2, num);
			
		return EventBillyJiaMing.botShip;
	}


	private static void nextPos(int randDir, int currX, int currY, int count) {
		//&& !EventBillyJiaMing.botShip[x][y--].equals("X")
		int x = currX;
		int y = currY;
		if(randDir == 1 || randDir == 3){
			if(currY-1>-1){
				y-= count;
			}else{
				y+= count;
			}
		}
		if(randDir == 2 || randDir == 4){
			if(currX-1>-1){
				x-= count;
			}else{
				x+= count;
			}
		}
		EventBillyJiaMing.botShip[x][y] = "2";
	}
	public static void updateUserHit() {
		// TODO Auto-generated method stub
		
	}

	/**
	private static void nextPos(int currX, int currY) {
		int x = currX;
		int y = currY;
		boolean inLoop =true;
		while(inLoop){
			int randDir = (int)(Math.random()*4 +1);
			if(randDir == 1 && currY-1>-1){
				y--;
				break;
			}else if(randDir ==2 && currX+1<10){
				x++;
				break;
			}else if(randDir ==3 && currY+1<10){
				y++;
				break;
			}else if(randDir ==4 && currX-1>1-1){
				x--;
				break;
			}
		}
		EventBillyJiaMing.botShip[x][y] = "2";
	}
	 */
}
