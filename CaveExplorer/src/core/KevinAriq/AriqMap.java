package core.KevinAriq;

import core.CaveRoom;

public class AriqMap {
	public static final String directions = "wdsa"; // w - 0 d - 1 s - 2 a - 3

	public KevinTile[][] tiles;
	public KevinGhost[] ghosts;
	public AriqPlayer player;

	public AriqMap() {
		generateTiles();

		// create the ghosts
		ghosts = new KevinGhost[2];
		ghosts[0] = new KevinGhost(this, 7, 4);
		ghosts[1] = new KevinGhost(this, 5, 1);

		// create the player
		player = new AriqPlayer(this, 0, 0);
	}

	// generates the tiles
	private void generateTiles() {
		tiles = new KevinTile[10][10];
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				tiles[i][j] = new KevinTile();
			}
		}

		// cancer b0ss
		tiles[0][1].createEntrance(CaveRoom.WEST);
		tiles[0][1].createEntrance(CaveRoom.SOUTH);
		tiles[1][1].createEntrance(CaveRoom.SOUTH);
		tiles[2][1].createEntrance(CaveRoom.SOUTH);
		tiles[3][1].createEntrance(CaveRoom.SOUTH);
		tiles[4][1].createEntrance(CaveRoom.SOUTH);
		tiles[5][1].createEntrance(CaveRoom.SOUTH);
		tiles[5][2].createEntrance(CaveRoom.WEST);
		tiles[5][3].createEntrance(CaveRoom.WEST);
		tiles[5][4].createEntrance(CaveRoom.WEST);
		tiles[5][5].createEntrance(CaveRoom.WEST);

		tiles[5][5].createEntrance(CaveRoom.NORTH);
		tiles[4][5].createEntrance(CaveRoom.NORTH);
		tiles[3][5].createEntrance(CaveRoom.NORTH);
		tiles[2][5].createEntrance(CaveRoom.WEST);
		tiles[2][4].createEntrance(CaveRoom.WEST);
		tiles[2][3].createEntrance(CaveRoom.WEST);
		tiles[2][2].createEntrance(CaveRoom.WEST);

		tiles[2][5].createEntrance(CaveRoom.NORTH);
		tiles[1][5].createEntrance(CaveRoom.NORTH);
		tiles[0][5].createEntrance(CaveRoom.EAST);
		tiles[0][6].createEntrance(CaveRoom.EAST);
		tiles[0][7].createEntrance(CaveRoom.SOUTH);
		tiles[1][7].createEntrance(CaveRoom.SOUTH);
		tiles[2][7].createEntrance(CaveRoom.SOUTH);
		tiles[3][7].createEntrance(CaveRoom.SOUTH);
		tiles[4][7].createEntrance(CaveRoom.SOUTH);
		tiles[5][7].createEntrance(CaveRoom.SOUTH);
		tiles[6][7].createEntrance(CaveRoom.SOUTH);
		tiles[7][7].createEntrance(CaveRoom.SOUTH);

		tiles[8][7].createEntrance(CaveRoom.WEST);
		tiles[8][6].createEntrance(CaveRoom.WEST);
		tiles[8][5].createEntrance(CaveRoom.WEST);
		tiles[8][4].createEntrance(CaveRoom.WEST);
		tiles[8][3].createEntrance(CaveRoom.WEST);
		tiles[8][2].createEntrance(CaveRoom.WEST);
		tiles[8][1].createEntrance(CaveRoom.NORTH);
		tiles[7][1].createEntrance(CaveRoom.NORTH);

		tiles[8][5].createEntrance(CaveRoom.NORTH);
		tiles[7][5].createEntrance(CaveRoom.NORTH);
		tiles[6][5].createEntrance(CaveRoom.NORTH);

		tiles[5][7].createEntrance(CaveRoom.EAST);
		tiles[5][8].createEntrance(CaveRoom.EAST);
		tiles[5][9].createEntrance(CaveRoom.NORTH);
		tiles[4][9].createEntrance(CaveRoom.NORTH);
		tiles[3][9].createEntrance(CaveRoom.WEST);
		tiles[3][8].createEntrance(CaveRoom.WEST);

		tiles[4][1].setEnergy(true);
		tiles[5][1].setEnergy(true);
		tiles[5][2].setEnergy(true);
	}

	// get the symbol at a tile
	public String getSymbolAt(int tileX, int tileY) {
		if (player.isAtTile(tileX, tileY)) {
			return "X";
		}

		for (int i = 0; i < ghosts.length; i++) {
			if (ghosts[i].isAtTile(tileX, tileY) && ghosts[i].isAlive()) {
				return "#";
			}
		}

		if (tiles[tileX][tileY].hasEnergy()) {
			return "*";
		}

		return " ";
	}

	public int getBorderX() {
		return tiles.length;
	}

	public int getBorderY() {
		return tiles[0].length;
	}
}