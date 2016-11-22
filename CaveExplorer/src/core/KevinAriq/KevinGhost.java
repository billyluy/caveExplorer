package core.KevinAriq;

import core.CaveRoom;

public class KevinGhost extends AriqEntity {

	private boolean alive;

	private int tick;
	private int direction;

	public KevinGhost(AriqMap map, int x, int y) {
		super(map, x, y);

		this.alive = true;
	}

	public void update() {
		tick++;
		tick %= 20;

		if (tick % 2 != 0)
			return;

		/*
		 * if ((int) (Math.random() * 100) < 50 && tick % 4 == 0) {
		 * changeDirection(); }
		 */

		double rand = Math.random();
		if (rand < 0.5) {
			if (direction == CaveRoom.EAST || direction == CaveRoom.WEST) {
				if (canMove(CaveRoom.NORTH) && rand < 0.25) {
					setDirection(CaveRoom.NORTH);
				} else if (canMove(CaveRoom.SOUTH)) {
					setDirection(CaveRoom.SOUTH);
				}
			} else if (direction == CaveRoom.NORTH || direction == CaveRoom.SOUTH) {
				if (canMove(CaveRoom.EAST) && rand > 0.25) {
					setDirection(CaveRoom.EAST);
				} else if (canMove(CaveRoom.WEST)) {
					setDirection(CaveRoom.WEST);
				}
			}
		}

		if (!canMove(direction)) {
			changeDirection();
		} else {
			move(direction);
		}
	}

	public void changeDirection() {
		int dir = -1;
		while ((dir = chooseRandomDirection()) == direction || !canMove(dir)) {

		}
		setDirection(dir);
	}

	public int chooseRandomDirection() {
		return (int) (Math.random() * 4);
	}

	private void setDirection(int direction) {
		this.direction = direction;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public boolean isAlive() {
		return alive;
	}

	class MoveTree {

	}

}