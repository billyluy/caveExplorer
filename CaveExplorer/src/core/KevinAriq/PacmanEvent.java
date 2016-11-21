package core.KevinAriq;

import core.CaveExplorer;
import core.CaveRoom;
import core.Event;

public class PacmanEvent implements Event {
	private AriqMap map;

	private Thread input;
	private boolean lock;
	private boolean cheat;
	private boolean hasWon;

	public void play() {
		map = new AriqMap();

		start();
	}

	public void start() {
		System.out.print(
				"\n\n\nYou have been warped to a dungeon. Collect the energy for your sucker and defeat the ghosts to escape!\n\n\n");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		input = new Thread(() -> {
			while (!cheat) {
				if (!lock) {
					int update = update();

					display();
					if (update == 0) {
						hasWon = true;
						break;
					} else if (update == 1) {
						System.out.print("\n\n\nYou have died! Respawning in 3 seconds...\n\n\n");
						map = new AriqMap();

						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					try {
						Thread.sleep(250);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		});
		input.start();

		while (!cheat) {
			if (hasWon) {
				break;
			}
			
			if (CaveExplorer.in.hasNextLine()) {
				String input = CaveExplorer.in.nextLine();

				lock = true;
				switch (input) {
				case "w":
				case "a":
				case "s":
				case "d":
					int direc = AriqMap.directions.indexOf(input);
					map.player.addDirection(direc);
					break;
				case "cheat":
					cheat = true;
					Thread.currentThread().stop();
					break;
				}
				lock = false;
			}
		}

		input.interrupt();
		System.out.print("\n\n\nYou have defeated all the ghosts! Congrats. The room is unlocked.\n\n\n");
	}

	// ??????????????????????? yolo
	public int update() {
		map.player.update();

		int check = check();
		if (check != 2) {
			return check;
		}

		for (int i = 0; i < map.ghosts.length; i++)
			map.ghosts[i].update();

		return check();
	}

	public int check() {
		for (int i = 0; i < map.ghosts.length; i++) {
			if (map.ghosts[i].getX() == map.player.getX() && map.ghosts[i].getY() == map.player.getY()) {
				if (!map.player.hasSufficientEnergy()) {
					// return 1;
				} else {
					map.ghosts[i].setAlive(false);
				}
			}
		}

		boolean won = true;
		for (int i = 0; i != map.ghosts.length; i++) {
			won &= !map.ghosts[i].isAlive();
		}

		return won ? 0 : 2;
	}

	public void display() {
		for (int i = 0; i != 5; i++) {
			System.out.println();
		}

		System.out.print(" ");
		for (int i = 0; i < map.getBorderX() - 1; i++) {
			System.out.print("____");
		}
		System.out.println("___");

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
						System.out.print(" " + getSymbolAt(i, j) + " ");
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

		// System.out.println(map.player.getY() + " " + map.player.getX());

		if (map.player.hasSufficientEnergy()) {
			System.out.println("Di do di dongerino. Your ghost sucker has enough energy. Go capture some ghosterino!");
		} else {
			System.out.println("You do not have enough energy. (" + map.player.getEnergyLevel() + "/3)");
		}

	}

	public String getSymbolAt(int tileX, int tileY) {
		if (map.player.getY() == tileX && map.player.getX() == tileY) {
			return "X";
		}

		for (int i = 0; i < map.ghosts.length; i++) {
			if (map.ghosts[i].getY() == tileX && map.ghosts[i].getX() == tileY && map.ghosts[i].isAlive()) {
				return "#";
			}
		}

		if (map.tiles[tileX][tileY].hasEnergy()) {
			return "*";
		}

		return " ";
	}

}