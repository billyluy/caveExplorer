package core.BillyJiaMing;

import core.CaveExplorer;
import core.Event;

public class EventBillyJiaMing implements Event{

	public static final String[] SEQ_1 = {"You have come across a puzzle.","You can't go on until you paly this game and beat the GHOST",
			"The GHOST challenges you to a game of battleship, but with ghost instead of ships!!!"};
	public static final String[] GAME_RES = {"NOOOO I LOST","HAAAA I BEAT YOU"};
	
//	public static boolean[][] userShip = new boolean[10][10];
//	public static boolean[][] botShip = new boolean[10][10];
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
			printArr(botHit);
			JiaMingInput.updateBotHit(); //update user hit
			BillyAi.updateUserHit();
			printArr(userHit);
			if(noShip(userShipMap)){
				CaveExplorer.print(GAME_RES[1]);
				inGame = false;
			}
			else if (noShip(botShipMap)){
				CaveExplorer.print(GAME_RES[0]);
				inGame = false;
			}
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
		public static void print(int roomsX, int roomsY) {
			for (int j = 0; j != roomsX; j++) {
				System.out.print("____");
			}
			System.out.println();

			for (int i = 0; i != roomsY; i++) {
				for (int j = 3; j > 0; j--) {
					for (int k = 0; k != roomsX; k++) {
						if (j == 1) {
							System.out.print("|___");
						} else {
							System.out.print("|   ");
						}
					}
					System.out.println("|");
				}
			}
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
