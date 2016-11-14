

public class GameStartEvent implements Event {
	
	public static final String[] SEQ_1 = {"A piky-haired guy with orange pants files up to you.",
			"Have you Arrived to save us from the treacherous Buu?","We need your help."};
	public static final String[] SEQ_2 = {"Great, I will be busy meditating on my super yellow version while you figure out where Buu is",
			"KAAAAA, MEEEEEE, HAAAAA, MEEEE HAAAAAAAAAAAAAAAAAAAAAA","By the way, here is a map"};

	public GameStartEvent() {
		
	}

	public void play() {
		readSequence(SEQ_1);
		CaveExplorer.print("Will you help us?");
		while(CaveExplorer.in.nextLine().indexOf("yes")<0){
			CaveExplorer.print("Please say yes. I beg you!");
		}
		readSequence(SEQ_2);
		//gives the user the map
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
