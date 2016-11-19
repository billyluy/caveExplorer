package core.BillyJiaMing;

import core.CaveExplorer;
import core.Event;

public class EventBillyJiaMing implements Event{
	public static final String[] SEQ_1 = {"You have come across a puzzle.","You can't go on until you paly this game and beat the GHOST",
			"The GHOST challenges you to a game of battleship, but with ghost instead of ships!!!"};
	public static final String[] SEQ_2 = {"You have defeated the ghost in the puzzle","The door magically unlocks"
			,"You are now free to go after you defeated the Ghost"};
	public static final String[] SEQ_3 = {"The Ghost has defeated you","It seems the ghost is smarter than you"};
	
	public static String[][] botShip = new String[10][10]; //bot ship location
	public static String[][] userMap = new String[10][10]; //user ship location & bot hit location
	public static String[][] botMap = new String[10][10]; //user hit location
	public void play() {
		readSequence(SEQ_1);
		CaveExplorer.print("Will you battle the ghost");
		if(CaveExplorer.in.nextLine().indexOf("yes")>-1){
			CaveExplorer.print("GAME STARTED");
		}else{ /*fix for other in*/
			CaveExplorer.print("TOO BAD IDC, You have nowhere else to go and you must battle him");
		}
		setSpace(userMap);
		setSpace(botMap);
		setSpace(botShip);
		JiaMingInput.placeShip();
		printArr(userMap);
		CaveExplorer.print("This is where all your ships are");
		CaveExplorer.print("- - - Press Enter - - -");
		CaveExplorer.in.nextLine();
		BillyAi.placeShip();
		boolean inGame = true;
		while(inGame){
			printArr(botMap);
			JiaMingInput.updateBotHit();
			BillyAi.updateUserHit();
			printArr(userMap);
			CaveExplorer.print("- - - Press Enter - - -");
			CaveExplorer.in.nextLine();
			if(noShip(userMap)){
				readSequence(SEQ_2);
				inGame = false;
			}
			else if (noShip(botShip)){
				readSequence(SEQ_3);
				inGame = false;
			}
			
		}
	}
	private static boolean noShip(String[][] arr) {
		for(int r =0;r < arr.length;r++){
			for(int c =0; c<arr[r].length;c++){
				if(arr[r][c].equals("O"))
					return false;
			}
		}
		return true;
	}

	public static void printArr(String[][] bolArr){
		for(int r =0;r < bolArr.length;r++){
			System.out.print(r+" ");
			for(int c =0; c<bolArr[r].length;c++){
				System.out.print("[" + bolArr[r][c] + "]");
			}
			CaveExplorer.print("");
		}
			System.out.print("  ");
			for(int i=0;i<bolArr.length;i++){
				System.out.print(" "+i+" ");
			}
			CaveExplorer.print("");
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
			CaveExplorer.print("- - - Press Enter - - -");
			CaveExplorer.in.nextLine();
		}
		
	}
}
