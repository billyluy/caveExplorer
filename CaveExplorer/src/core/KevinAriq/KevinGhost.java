package core.KevinAriq;

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
		tick %= 10;

		if (tick % 2 != 0)
			return;

		/*
		 * if ((int) (Math.random() * 100) < 50 && tick % 4 == 0) {
		 * changeDirection(); }
		 */

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

}