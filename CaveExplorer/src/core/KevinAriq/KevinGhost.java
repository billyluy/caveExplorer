package core.KevinAriq;

import core.CaveRoom;

public class KevinGhost {

	private AriqMap map;
	private int x, y;
	private boolean alive;

	private int tick;
	private int direction;

	public KevinGhost(AriqMap map, int x, int y) {
		this.map = map;
		this.x = x;
		this.y = y;

		this.alive = true;
	}

	public void update() {
		tick++;
		tick %= 10;

		/*if ((int) (Math.random() * 100) < 50 && tick % 4 == 0) {
			changeDirection();
		}*/

		if (!canMove(direction)) {
			changeDirection();
		} else {
			move(direction);
		}
	}

	public void changeDirection() {
		int dir = -1;
		while ((dir = chooseRandomDirection()) == direction || !canMove(dir)) {
			System.out.println(dir);
		}
		setDirection(dir);
	}

	public int chooseRandomDirection() {
		return (int) (Math.random() * 4);
	}

	private void setDirection(int direction) {
		this.direction = direction;
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

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public boolean isAlive() {
		return alive;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}