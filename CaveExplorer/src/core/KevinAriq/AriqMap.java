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
		tiles = new KevinTile[9][16];
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				tiles[i][j] = new KevinTile();
			}
		}
		tiles[0][0].createEntrance(CaveRoom.EAST);
        tiles[0][1].createEntrance(CaveRoom.EAST);
        tiles[0][2].createEntrance(CaveRoom.EAST);
       
       
        tiles[0][0].createEntrance(CaveRoom.SOUTH);
        tiles[1][0].createEntrance(CaveRoom.SOUTH);
        tiles[2][0].createEntrance(CaveRoom.SOUTH);
        tiles[3][0].createEntrance(CaveRoom.SOUTH);
        tiles[4][0].createEntrance(CaveRoom.SOUTH);
        tiles[5][0].createEntrance(CaveRoom.SOUTH);
        tiles[6][0].createEntrance(CaveRoom.SOUTH);
        tiles[7][0].createEntrance(CaveRoom.SOUTH);
       
       
       
        tiles[8][0].createEntrance(CaveRoom.EAST);
        tiles[8][1].createEntrance(CaveRoom.EAST);
        tiles[8][2].createEntrance(CaveRoom.EAST);
       
        tiles[8][3].createEntrance(CaveRoom.NORTH);
        tiles[7][3].createEntrance(CaveRoom.NORTH);
       
        tiles[6][3].createEntrance(CaveRoom.WEST);
        tiles[6][3].createEntrance(CaveRoom.EAST);
       
        tiles[6][2].createEntrance(CaveRoom.NORTH);
        tiles[5][2].createEntrance(CaveRoom.NORTH);
        tiles[4][2].createEntrance(CaveRoom.NORTH);
        tiles[3][2].createEntrance(CaveRoom.NORTH);
       
        tiles[4][0].createEntrance(CaveRoom.EAST);
        tiles[4][1].createEntrance(CaveRoom.EAST);
       
        tiles[0][3].createEntrance(CaveRoom.SOUTH);
        tiles[1][3].createEntrance(CaveRoom.SOUTH);
        tiles[2][3].createEntrance(CaveRoom.WEST);
       
        tiles[6][4].createEntrance(CaveRoom.EAST);
        tiles[6][5].createEntrance(CaveRoom.EAST);
        tiles[6][6].createEntrance(CaveRoom.EAST);
        tiles[6][7].createEntrance(CaveRoom.EAST);
        tiles[6][8].createEntrance(CaveRoom.EAST);
        tiles[6][9].createEntrance(CaveRoom.EAST);
        tiles[6][10].createEntrance(CaveRoom.EAST);
        tiles[6][11].createEntrance(CaveRoom.EAST);
        tiles[6][12].createEntrance(CaveRoom.EAST);
       
        tiles[2][3].createEntrance(CaveRoom.EAST);
        tiles[2][4].createEntrance(CaveRoom.EAST);
        tiles[2][5].createEntrance(CaveRoom.EAST);
        tiles[2][6].createEntrance(CaveRoom.EAST);
        tiles[2][7].createEntrance(CaveRoom.EAST);
        tiles[2][8].createEntrance(CaveRoom.EAST);
        tiles[2][9].createEntrance(CaveRoom.EAST);
        tiles[2][10].createEntrance(CaveRoom.EAST);
        tiles[2][11].createEntrance(CaveRoom.EAST);
        tiles[2][12].createEntrance(CaveRoom.EAST);
       
        tiles[0][5].createEntrance(CaveRoom.SOUTH);
        tiles[1][5].createEntrance(CaveRoom.SOUTH);
        tiles[2][5].createEntrance(CaveRoom.SOUTH);
        tiles[3][5].createEntrance(CaveRoom.SOUTH);
        tiles[4][5].createEntrance(CaveRoom.SOUTH);
        tiles[5][5].createEntrance(CaveRoom.SOUTH);
       
        tiles[4][2].createEntrance(CaveRoom.EAST);
        tiles[4][3].createEntrance(CaveRoom.EAST);
        tiles[4][4].createEntrance(CaveRoom.EAST);
       
        tiles[6][5].createEntrance(CaveRoom.SOUTH);
        tiles[7][5].createEntrance(CaveRoom.SOUTH);
       
        tiles[0][15].createEntrance(CaveRoom.SOUTH);
        tiles[1][15].createEntrance(CaveRoom.SOUTH);
        tiles[2][15].createEntrance(CaveRoom.SOUTH);
        tiles[3][15].createEntrance(CaveRoom.SOUTH);
        tiles[4][15].createEntrance(CaveRoom.SOUTH);
        tiles[5][15].createEntrance(CaveRoom.SOUTH);
        tiles[6][15].createEntrance(CaveRoom.SOUTH);
       
        tiles[4][15].createEntrance(CaveRoom.WEST);
        tiles[4][14].createEntrance(CaveRoom.WEST);
        tiles[4][13].createEntrance(CaveRoom.WEST);
        tiles[4][12].createEntrance(CaveRoom.WEST);
        tiles[4][11].createEntrance(CaveRoom.WEST);
       
        tiles[0][10].createEntrance(CaveRoom.SOUTH);
        tiles[1][10].createEntrance(CaveRoom.SOUTH);     
        tiles[2][10].createEntrance(CaveRoom.SOUTH);
        tiles[3][10].createEntrance(CaveRoom.SOUTH);
        tiles[4][10].createEntrance(CaveRoom.SOUTH);
        tiles[5][10].createEntrance(CaveRoom.SOUTH);
        tiles[6][10].createEntrance(CaveRoom.SOUTH);
        tiles[7][10].createEntrance(CaveRoom.SOUTH);
       
       
        tiles[0][5].createEntrance(CaveRoom.EAST);
        tiles[0][6].createEntrance(CaveRoom.EAST);
        tiles[0][7].createEntrance(CaveRoom.EAST);
        tiles[0][8].createEntrance(CaveRoom.EAST);
        tiles[0][9].createEntrance(CaveRoom.EAST);
       
        tiles[8][5].createEntrance(CaveRoom.EAST);
        tiles[8][6].createEntrance(CaveRoom.EAST);
        tiles[8][7].createEntrance(CaveRoom.EAST);
        tiles[8][8].createEntrance(CaveRoom.EAST);
        tiles[8][9].createEntrance(CaveRoom.EAST);
       
       
        tiles[2][7].createEntrance(CaveRoom.SOUTH);
        tiles[2][8].createEntrance(CaveRoom.SOUTH);
        tiles[3][7].createEntrance(CaveRoom.EAST);
        tiles[3][7].createEntrance(CaveRoom.SOUTH);
        tiles[3][8].createEntrance(CaveRoom.SOUTH);
        tiles[4][7].createEntrance(CaveRoom.EAST);    
       
        tiles[0][12].createEntrance(CaveRoom.SOUTH);
        tiles[1][12].createEntrance(CaveRoom.SOUTH);
       
        tiles[0][12].createEntrance(CaveRoom.EAST);
        tiles[0][13].createEntrance(CaveRoom.EAST);
        tiles[0][14].createEntrance(CaveRoom.EAST);
       
       
        tiles[2][13].createEntrance(CaveRoom.SOUTH);
        tiles[3][13].createEntrance(CaveRoom.SOUTH);
        tiles[4][13].createEntrance(CaveRoom.SOUTH);
        tiles[5][13].createEntrance(CaveRoom.SOUTH);
 
       
        tiles[8][15].createEntrance(CaveRoom.WEST);
        tiles[8][14].createEntrance(CaveRoom.WEST);
        tiles[8][13].createEntrance(CaveRoom.WEST);
       
        tiles[8][12].createEntrance(CaveRoom.NORTH);
        tiles[7][12].createEntrance(CaveRoom.NORTH);

        tiles[8][15].createEntrance(CaveRoom.NORTH);
        
		tiles[2][10].setEnergy(true);
		tiles[7][3].setEnergy(true);
		tiles[0][5].setEnergy(true);
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

	public int getBorderY() {
		return tiles.length;
	}

	public int getBorderX() {
		return tiles[0].length;
	}
}