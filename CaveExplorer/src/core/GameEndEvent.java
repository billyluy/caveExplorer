package core;


public class GameEndEvent implements Event {
	
	public static final String[] SEQ_1 = {"You have found your way through the cave","You have defeated all the ghostes on the way"};

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