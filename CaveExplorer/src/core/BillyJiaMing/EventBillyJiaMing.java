package core.BillyJiaMing;

import core.CaveExplorer;
import core.Event;

public class EventBillyJiaMing implements Event{
	public static final String[] SEQ_1 = {"You have come across a puzzle.","You can't go on until you paly this game and beat the GHOST",
			"The GHOST challenges you to a game of battleship, but with ghost instead of ships!!!"};
	public static final String[] SEQ_2 = {"You have defeated the ghost in the puzzle","The door magically unlocks"
			,"You are now free to go after you defeated the Ghost"};
	public static final String[] SEQ_3 = {"The Ghost has defeated you","It seems the ghost is smarter than you"};
	
	public static String[][] userHit = new String[10][10]; //USERS MAP
	public static String[][] botHit = new String[10][10]; //BOTS MAP
	
	public void play() {
		readSequence(SEQ_1);
		CaveExplorer.print("Will you battle the ghost");
		if(CaveExplorer.in.nextLine().indexOf("yes")>-1){
			CaveExplorer.print("GAME STARTED");
		}else{ /*fix for other in*/
			CaveExplorer.print("TOO BAD, You have nowhere else to go and you must battle him");
		}
		setSpace(userHit);
		setSpace(botHit);
		boolean[][] userShipMap = JiaMingInput.placeShip();
		boolean[][] botShipMap = BillyAi.placeShip();
		boolean inGame = true;
		while(inGame){
			printArr(botHit); // shows bot map
			JiaMingInput.updateBotHit(); //update user hit
			BillyAi.updateUserHit();
			printArr(userHit);
			CaveExplorer.print(" - - - Press enter to continue - - - ");
			CaveExplorer.in.nextLine();
			break;
//			CaveExplorer.currentRoom = CaveExplorer.caves[1][2];
//			CaveExplorer.startExploring();
//			CaveExplorer.currentRoom.enter();
//			if(noShip(userShipMap)){
//				readSequence(SEQ_2);
//				inGame = false;
//			}
//			else if (noShip(botShipMap)){
//				readSequence(SEQ_3);
//				inGame = false;
//			}
			
		}
	}
	private static boolean noShip(boolean[][] arr) {
		for(int r =0;r < arr.length;r++){
			for(int c =0; c<arr[r].length;c++){
				if(arr[r][c] == true)
					return false;
			}
		}
		return true;
	}

	public static void printArr(String[][] bolArr){
		for(int r =0;r < bolArr.length;r++){
			for(int c =0; c<bolArr[r].length;c++){
				System.out.print("[" + bolArr[r][c] + "]");
			}
			System.out.println("");
		}
	}
	
	public static void setSpace(String[][] arr){
		for(int r =0;r < arr.length;r++){
			for(int c =0; c<arr[r].length;c++){
				arr[r][c] = " ";
			}
		}
	}

	private static void readSequence(String[] seq) {
		for(String s: seq){
			CaveExplorer.print(s);
			CaveExplorer.print(" - - - Press enter - - - ");
			CaveExplorer.in.nextLine();
		}
		
	}
}
