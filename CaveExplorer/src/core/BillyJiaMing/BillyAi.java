package core.BillyJiaMing;

import core.CaveExplorer;

public class BillyAi {

	public static String[][] placeShip() {
		//String[][] map = EventBillyJiaMing.botShip;
		//USE 2 FOR LOOPS 1 FOR SHIP SIZE 1 FOR PLACE AMOUNT OF X
		for(int i=2;i<6;i++){
			int rand1 = (int)(Math.random()*10);
			int rand2 = (int)(Math.random()*10);
			int randDir = (int)(Math.random()*4 +1);
			//validPos(i,rand1,rand2,randDir)
			while(EventBillyJiaMing.botShip[rand1][rand2].equals("X")){
				rand1 = (int)(Math.random()*10);
				rand2 = (int)(Math.random()*10);
			}
			System.out.print(randDir+" ");
			CaveExplorer.print(rand2+" "+rand1);
			EventBillyJiaMing.botShip[rand1][rand2] = "X";
			nextPos(randDir, rand1, rand2, i);
		}
		return EventBillyJiaMing.botShip;
	}


	private static boolean validPos(int size, int rand1, int rand2,int dir) {
		for(int i =0;i<size;i++){
			if(dir ==1 &&EventBillyJiaMing.botShip[rand1-i][rand2].equals("X")){
				return true;
			}else if(dir ==2 &&EventBillyJiaMing.botShip[rand1][rand2+i].equals("X")){
				return true;
			}else if(dir ==3 &&EventBillyJiaMing.botShip[rand1+i][rand2].equals("X")){
				return true;
			}else if(dir ==4 &&EventBillyJiaMing.botShip[rand1][rand2-i].equals("X")){
				return true;
			}
		}
		return false;
	}


	private static void nextPos(int randDir, int currX, int currY, int count) {
		//&& !EventBillyJiaMing.botShip[x][y--].equals("X")
		int dirPick = randDir;
		int x = currX;
		int y = currY;
		for(int i = count-1; i>0;i--){
			if(dirPick ==1){
				if(x-1>-1){
					x-= 1;
				}else{
					x+=(x+currX)+1;
					dirPick = 3;
				}
			}else if(dirPick == 3){ 
				if(x+1<10){
					x+= 1;
				}else{
					x-=(x-currX)+1; 
					dirPick = 1;
				}
			}else if(dirPick == 2){
				if(y+1<10){
					y+= 1;
				}else{
					y-=(y-currY)+1; 
					dirPick = 4;
				}
			}else if(dirPick == 4){ 
				if(y-1>-1){
					y-= 1;
				}else{
					y+=(y+currY)+1;
					dirPick = 2;
				}
			}
			EventBillyJiaMing.botShip[x][y] = "X";
		}
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
