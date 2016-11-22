package core.BillyJiaMing;

import core.CaveExplorer;

public class BillyAi {
	
	private static String[] hitRes = {"HAHAHA, I have hit your ship","LOOK, down it goes", "You see that shot, you can't possibly beat me","Another one"};
	private static String[] missRes = {"HMM, you got lucky this time","What I missed??? Not possible","NOOOO","I'll get one of yours next time",
			"Looks like I missed this time","Did you rig this?"};

	public static String[][] placeShip() {
		for(int i=2;i<6;i++){
			//gen random coords and direction to place ship
			int rand1 = (int)(Math.random()*10);
			int rand2 = (int)(Math.random()*10);
			int randDir = (int)(Math.random()*4 +1);
			int loopCheck = -1;
			while(EventBillyJiaMing.botShip[rand1][rand2].equals("O")||validPos(i,rand1,rand2,randDir)){
				//backup in case stuck
				loopCheck++;
				if(loopCheck>50){
					break;
				}
				rand1 = (int)(Math.random()*10);
				rand2 = (int)(Math.random()*10);
			}
			EventBillyJiaMing.botShip[rand1][rand2] = "O";
			nextPos(randDir, rand1, rand2, i);
		}
		CaveExplorer.print("Ghost: I have placed my ships, here we go");
		CaveExplorer.print("- - - Press Enter - - -");
		CaveExplorer.in.nextLine();
		return EventBillyJiaMing.botShip;
	}


	private static boolean validPos(int size, int rand1, int rand2,int dir) {
		//check if any ships are already placed in a certain direction
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
			//check if a valid place position(out of bounds or already occupied)	
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
		//place ships in a straight line
		//able to place behind if ahead is already occupied or is border of map
		int dirPick = randDir;
		int x = currX;
		int y = currY;
		//place ships one by one
		for(int i = count-1; i>0;i--){
			if(dirPick ==1){
				if(x-1>-1){
					x-= 1;
				}else{
					//change direction if border of map
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
		boolean myTurn = true;
		boolean hitAdja = false;
		int prevRand1 = -1;
		int prevRand2 = -1;
		int prevDir = -1;
		int count =0;
		while(myTurn){
			int rand1 = (int)(Math.random()*10);
			int rand2 = (int)(Math.random()*10);
			if(count >=4){
				hitAdja =false;
				count =0;
			}
			while(hitAdja){
				count++;
				int randDir = (int)(Math.random()*4 +1);
//				prevDir = randDir;
//				if(!(prevDir==-1)){
//					if(prevDir==1){
//						
//					}
//				}
				if(randDir ==1 && prevRand1-1>-1){
					rand1 = prevRand1-1;
					break;
				}else if(randDir ==2 &&prevRand2+1<10){
					rand2 = prevRand2+1;
					break;
				}else if(randDir ==3 &&prevRand1+1<10){
					rand1 = prevRand1+1;
					break;
				}else if(randDir==4 &&prevRand2-1>-1){
					rand2 = prevRand2-1;
					break;
				}
			}
			while(EventBillyJiaMing.userMap[rand1][rand2].equals("X")||EventBillyJiaMing.userMap[rand1][rand2].equals("-")){
				System.out.println("not suppose to be here");
				rand1 = (int)(Math.random()*10);
				rand2 = (int)(Math.random()*10);
			}
			if(EventBillyJiaMing.userMap[rand1][rand2].equals("O")){
				EventBillyJiaMing.userMap[rand1][rand2]=("-");
				CaveExplorer.print("Ghost:"+randomFromArray(hitRes));
				EventBillyJiaMing.printArr(EventBillyJiaMing.userMap);
				CaveExplorer.print("- - - Press Enter - - -");
				CaveExplorer.in.nextLine();
				prevRand1=rand1;
				prevRand2=rand2;
				hitAdja =true;
			}else{
				EventBillyJiaMing.userMap[rand1][rand2]=("X");
				CaveExplorer.print("Ghost:"+randomFromArray(missRes));
				EventBillyJiaMing.printArr(EventBillyJiaMing.userMap);
				myTurn =false;
			}
		}
		
	}
	
	private static String randomFromArray(String[] array){
		int responseIndex = 0;
		responseIndex = (int)(Math.random() * array.length);
		return array[responseIndex];
	}
}

