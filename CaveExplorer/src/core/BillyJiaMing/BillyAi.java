package core.BillyJiaMing;

import core.CaveExplorer;

public class BillyAi {
	
	private static String[] hitRes = {"HAHAHA, I have hit your ship","LOOK, down it goes", "You see that shot, you can't possibly beat me","Another one"};
	private static String[] missRes = {"HMM, you got lucky this time","I can't possibly lose!!!","Not like this","NOOOO","I'll get one of yours",
			"You seem better than you look"};

	public static String[][] placeShip() {
		for(int i=2;i<6;i++){
			int rand1 = (int)(Math.random()*10);
			int rand2 = (int)(Math.random()*10);
			int randDir = (int)(Math.random()*4 +1);
			int loopCheck = -1;
			while(EventBillyJiaMing.botShip[rand1][rand2].equals("O")||validPos(i,rand1,rand2,randDir)){
				loopCheck++;
				if(loopCheck>50){
					break;
				}
				rand1 = (int)(Math.random()*10);
				rand2 = (int)(Math.random()*10);
			}
//			System.out.println(randDir+" ");
//			System.out.println(rand2+" "+rand1);
			EventBillyJiaMing.botShip[rand1][rand2] = "O";
			nextPos(randDir, rand1, rand2, i);
		}
		CaveExplorer.print("Ghost: I have placed my ships, here we go");
		return EventBillyJiaMing.botShip;
	}


	private static boolean validPos(int size, int rand1, int rand2,int dir) {
		int dirPick = dir;
		for(int i =1;i<size;i++){
			if(dirPick ==1 && rand1-i<=-1){
				dirPick =3;
			}else if(dirPick ==2 &&rand2+i>=10){
				dirPick =4;
			}else if(dirPick ==3 &&rand1+i>=10){
				dirPick =1;
			}else if(dirPick==4 &&rand2-i<=-1){
				dirPick =2;
			}
				
			if(dirPick ==1 &&EventBillyJiaMing.botShip[rand1-i][rand2].equals("O")|| rand1-i<=-1){
				return true;
			}else if(dirPick ==2 &&EventBillyJiaMing.botShip[rand1][rand2+i].equals("O")|| rand2+i>=10){
				return true;
			}else if(dirPick ==3 &&EventBillyJiaMing.botShip[rand1+i][rand2].equals("O")|| rand1+i>=10){
				return true;
			}else if(dirPick ==4 &&EventBillyJiaMing.botShip[rand1][rand2-i].equals("O")|| rand2-i<=-1){
				return true;
			}
		}
		return false;
	}


	private static void nextPos(int randDir, int currX, int currY, int count) {
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
			EventBillyJiaMing.botShip[x][y] = "O";
		}
	}
	public static void updateUserHit() {
		int rand1 = (int)(Math.random()*10);
		int rand2 = (int)(Math.random()*10);
		while(EventBillyJiaMing.userMap[rand1][rand2].equals("X")){
			rand1 = (int)(Math.random()*10);
			rand2 = (int)(Math.random()*10);
		}
		if(EventBillyJiaMing.userMap[rand1][rand2].equals("O")){
			EventBillyJiaMing.userMap[rand1][rand2].equals("-");
			CaveExplorer.print("Ghost:"+randomFromArray(hitRes));
		}else{
			EventBillyJiaMing.userMap[rand1][rand2].equals("X");
			CaveExplorer.print("Ghost:"+randomFromArray(missRes));
		}
	}
	
	private static String randomFromArray(String[] array){
		int responseIndex = 0;
		responseIndex = (int)(Math.random() * array.length);
		return array[responseIndex];
	}
}

