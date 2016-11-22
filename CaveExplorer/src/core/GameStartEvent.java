package core;


public class GameStartEvent implements Event {
	
	public static final String[] SEQ_1 = {"While in the cave", "You have to beat the three puzzles to get rid of the ghost",
			"Here is a map to guide you out","You will bot be able to exit unless you have beat all the puzzles!"};

	public GameStartEvent() {
		
	}

	public void play() {
		readSequence(SEQ_1);
		CaveExplorer.inventory.setHasMap(true);
	}

	public static void readSequence(String[] seq) {
		for(String s: seq){
			CaveExplorer.print(s);
			CaveExplorer.print(" - - - Press enter - - - ");
			CaveExplorer.in.nextLine();
		}
		
	}

}
