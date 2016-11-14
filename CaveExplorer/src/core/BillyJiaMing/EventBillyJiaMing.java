package core.BillyJiaMing;

import core.CaveExplorer;
import core.Event;

public class EventBillyJiaMing implements Event{

	public static final String[] SEQ_1 = {"You have come across a puzzle.","You can't go on until you paly this game and beat the GHOST",
			"The GHOST challenges you to a game of battleship, but with ghost instead of ships!!!"};
	public static final String[] HIT_RES = {"Oh no you hit my Ghost","NOOOO I LOST","HAAAA I BEAT YOU"};
	public static boolean[][] userShip = new boolean[10][10];
	public static boolean[][] botShip = new boolean[10][10];
	public static boolean[][] userHit = new boolean[10][10];
	public static boolean[][] botHit = new boolean[10][10];
	
	public void play() {
		readSequence(SEQ_1);
		CaveExplorer.print("Will you battle the ghost");
		if(CaveExplorer.in.nextLine().indexOf("yes")>-1){
			CaveExplorer.print("GAME STARTED");
		}else{ /*fix for other in*/
			CaveExplorer.print("TOO BAD, You have nowhere else to go and you must battle him");
		}
		JiaMingInput.generateMap();
		JiaMingInput.placeShip();
	}

	public static void readSequence(String[] seq) {
		for(String s: seq){
			CaveExplorer.print(s);
			CaveExplorer.print(" - - - Press enter - - - ");
			CaveExplorer.in.nextLine();
		}
		
	}
}
