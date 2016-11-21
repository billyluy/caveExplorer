package core.KevinAriq;

import core.CaveRoom;

public class AriqPlayer {
	private AriqMap map;
	private int energyLevel;
	private int x, y;

	private int queuedDirection, currDirection;

	public AriqPlayer(AriqMap map, int x, int y) {
		this.map = map;
		this.x = x;
		this.y = y;

		this.queuedDirection = -1;
		this.currDirection = -1;
	}

	public void addDirection(int direction) {
		if (canMove(direction)) {
			this.currDirection = direction;
			this.queuedDirection = -1;
		} else {
			this.queuedDirection = direction;
		}
	}

	public boolean canMove(int direction) {
		if (direction == -1) {
			return false;
		}

		int prevX = this.x, prevY = this.y;

		move(direction);
		boolean can = map.tiles[prevY][prevX].hasEntrance(direction) || ((this.x != prevX || this.y != prevY)
				&& map.tiles[this.y][this.x].hasEntrance(CaveRoom.oppositeDirection(direction)));

		this.x = prevX;
		this.y = prevY;

		return can;
	}

	public void move(int direction) {
		switch (direction) {
		case CaveRoom.EAST:
			if (x < map.getBorderX() - 1)
				x++;
			break;
		case CaveRoom.NORTH:
			if (y > 0)
				y--;
			break;
		case CaveRoom.SOUTH:
			if (y < map.getBorderY() - 1)
				y++;
			break;
		case CaveRoom.WEST:
			if (x > 0)
				x--;
			break;
		}
	}

	public void update() {
		if (canMove(this.queuedDirection)) {
			this.currDirection = this.queuedDirection;
		}

		if (canMove(this.currDirection)) {
			move(this.currDirection);
		}

		if (map.tiles[this.y][this.x].hasEnergy()) {
			map.tiles[this.y][this.x].setEnergy(false);
			energyLevel++;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getEnergyLevel() {
		return this.energyLevel;
	}

	public boolean hasSufficientEnergy() {
		return energyLevel > 2;
	}

}