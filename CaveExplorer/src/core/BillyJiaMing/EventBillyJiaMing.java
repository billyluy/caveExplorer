package core.BillyJiaMing;

import core.CaveExplorer;
import core.Event;

public class EventBillyJiaMing implements Event{
	public static final String[] SEQ_1 = {"You have come across a puzzle.","You can't go on until you paly this game and beat the GHOST",
			"The GHOST challenges you to a game of battleship, but with ghost instead of ships!!!"};
	public static final String[] SEQ_2 = {"Ghost: NOOO you can't possibly have defeated me at my own game","You have defeated the ghost in the puzzle",
			"The door magically unlocks","You are now free to go after you defeated the Ghost"};
	public static final String[] SEQ_3 = {"Ghost: HEHE loser loser, NOW GET OUT OF MY ROOM","The Ghost has defeated you",
			"It seems the ghost is smarter than thought"};
	
	public static String[][] botShip = new String[10][10]; //bot ship location
	public static String[][] userMap = new String[10][10]; //user ship location & bot hit location
	public static String[][] botMap = new String[10][10]; //user hit location
	public static boolean inGame;
	public void play() {
		readSequence(SEQ_1);
		CaveExplorer.print("Will you battle the ghost?");
		if(CaveExplorer.in.nextLine().indexOf("yes")>-1){
			CaveExplorer.print("Good luck.");
			pressEnter();
		}else{
			CaveExplorer.print("Too bad, you have nowhere else to go and you must battle him");
			pressEnter();
		}
		inGame = true;
		setSpace(userMap);
		setSpace(botMap);
		setSpace(botShip);
		JiaMingInput.promptPlaceShip();
		if(inGame){
			printArr(userMap);
			CaveExplorer.print("This is where all your ships are");
			pressEnter();
			BillyAi.placeShip();
			CaveExplorer.print("You may skip the game anytime by typing the cheat code while ingame");
			pressEnter();
			while(inGame){
				JiaMingInput.promptAttack();
				BillyAi.updateUserHit();
				pressEnter();
				if(noShip(botShip)){
					readSequence(SEQ_2);
					inGame = false;
				}
				else if (noShip(userMap)){
					readSequence(SEQ_3);
					inGame = false;
				}
			}
		}else {
			CaveExplorer.print("Cheat code accepted");
			pressEnter();
			readSequence(SEQ_2);
			
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
	public static void pressEnter(){
		CaveExplorer.print("- - - Press Enter - - -");
		CaveExplorer.in.nextLine();
	}
}
