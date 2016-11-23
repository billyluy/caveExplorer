package core.KevinAriq;

import java.util.concurrent.atomic.AtomicBoolean;

import core.CaveExplorer;
import core.CaveRoom;
import core.Event;

public class KevinPacmanEvent implements Event {
	private static final String[] INTRO = { "You have been warped to a dungeon against your wishes.",
			"Collect the energy for your sucker and capture the ghosts to escape!" };
	public static final String[] WIN = { "You have captured all the ghosts! Congrats.", "You have been warped back.",
			"Press enter to continue." };
	private static final String[] DEATH = { "You have died! Unfortunately even death does not grant mercy.",
			"Try again." };

	public AriqMap map;
	private AriqInput input;

	private Thread update;
	public AtomicBoolean hasWon; // using atomic boolean for multi thread access safety

	// starting method here
	public void play() {
		map = new AriqMap();
		input = new AriqInput(this);

		hasWon = new AtomicBoolean(false);
	
		// create separate thread for updating and display to screen
		update = new Thread(() -> {
			while (!hasWon.get()) {
				int update = update();

				display();
				if (update == 0) {
					readSequence(WIN);
					hasWon.set(true);
					break;
				} else if (update == 1) {
					readSequence(DEATH);
					map = new AriqMap();
				}

				sleep(350);
			}
		});
		start();
	}

	public void start() {
		readSequence(INTRO);
		
		// start the thread
		update.start();
		
		// continously ask for input
		input.handleInput();
		
		// stop the thread
		update.interrupt();
	}

	// print a string array
	public static void readSequence(String[] seq) {
		for (String s : seq) {
			CaveExplorer.print("\n- - - - - - - -");
			CaveExplorer.print(s);
			CaveExplorer.print("- - - - - - - -");
			sleep(1500);
		}
	}

	// program sleep
	private static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
		}
	}

	// updates the map (ghosts, players, pickups, etc...) and sees if player has won or lost
	public int update() {
		map.player.update();

		// check if player has won or lost after player moved
		int check = check();
		if (check != 2) {
			return check;
		}

		for (int i = 0; i < map.ghosts.length; i++)
			map.ghosts[i].update();

		// check if player has won or lost again after ghosts moved
		return check();
	}

	// check if player has won or lost
	public int check() {
		
		// go through ghosts and see if touching player
		for (int i = 0; i < map.ghosts.length; i++) {
			if (map.ghosts[i].isTouching(map.player)) {
				if (!map.player.hasSufficientEnergy()) {
					// kill player when touching ghost if player doesnt have enough energy 
					return 1;
				} else {
					// kill ghost touching player when player has enough energy
					map.ghosts[i].setAlive(false);
				}
			}
		}

		// check if all ghosts are dead, concluding player won
		boolean won = true;
		for (int i = 0; i != map.ghosts.length; i++) {
			won &= !map.ghosts[i].isAlive();
		}

		// if won return 0 otherwise 2
		return won ? 0 : 2;
	}

	// display grid to screen
	public void display() {
		// print roof
		System.out.print("\n\n\n\n ");
		for (int i = 0; i < map.getBorderX() - 1; i++) {
			System.out.print("____");
		}
		System.out.println("___");

		// print rooms
		for (int i = 0; i < map.getBorderY(); i++) {
			for (int k = 0; k < 3; k++) {
				for (int j = 0; j < map.getBorderX(); j++) {
					if (!map.tiles[i][j].hasEntrance(CaveRoom.WEST)
							&& !(j > 0 && map.tiles[i][j - 1].hasEntrance(CaveRoom.EAST))) {
						System.out.print("|");
					} else {
						System.out.print(" ");
					}

					if (k == 2) {
						if (map.tiles[i][j].hasEntrance(CaveRoom.SOUTH)
								|| (i < map.getBorderY() - 1 && map.tiles[i + 1][j].hasEntrance(CaveRoom.NORTH))) {
							System.out.print("   ");
						} else {
							System.out.print("___");
						}
					} else if (k == 1) {
						System.out.print(" " + map.getSymbolAt(i, j) + " ");
					} else {
						System.out.print("   ");
					}

					if (j == map.getBorderX() - 1) {
						System.out.print("|");
					}
				}
				System.out.println();
			}
		}

		// display info related to energy
		if (map.player.hasSufficientEnergy()) {
			System.out.println("\nYou have enough energy. Go capture some ghosterino!");
		} else {
			System.out.println("\nYou do not have enough energy. (" + map.player.getEnergyLevel() + "/3)");
		}
	}
}