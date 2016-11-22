package core.KevinAriq;

import core.CaveExplorer;

public class AriqInput {
	KevinPacmanEvent event;

	public AriqInput(KevinPacmanEvent event){
		this.event = event;
	}

	// handle input
	public void handleInput(){
		while (!event.hasWon.get()){
			String input = CaveExplorer.in.nextLine();

			switch (input){
			case "w":
			case "a":
			case "s":
			case "d":
				int direc = AriqMap.directions.indexOf(input); // convert direction to int
				event.map.player.addDirection(direc);
				break;
			case "cheat":
				event.hasWon.set(true);
				KevinPacmanEvent.readSequence(KevinPacmanEvent.WIN);
				CaveExplorer.in.nextLine();
				return;
			}
		}
	}

}
