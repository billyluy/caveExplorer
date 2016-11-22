package core.KevinAriq;

public class AriqPlayer extends AriqEntity {
	private int energyLevel; // save energy level

	// store directions
	private int queuedDirection, currDirection;

	public AriqPlayer(AriqMap map, int x, int y) {
		super(map, x, y);

		this.queuedDirection = -1;
		this.currDirection = -1;
	}

	// queue upcoming movements
	public void addDirection(int direction) {
		if (canMove(direction)) {
			this.currDirection = direction;
			this.queuedDirection = -1;
		} else {
			this.queuedDirection = direction;
		}
	}

	public void update() {
		// check if can move into queued direction
		if (canMove(this.queuedDirection)) {
			this.currDirection = this.queuedDirection;
		}

		// check if can move in direction before moving
		if (canMove(this.currDirection)) {
			move(this.currDirection);
		}

		// check if tile has energy to pickup
		if (map.tiles[getY()][getX()].hasEnergy()) {
			map.tiles[getY()][getX()].setEnergy(false);
			energyLevel++;
		}
	}

	public int getEnergyLevel() {
		return this.energyLevel;
	}

	public boolean hasSufficientEnergy() {
		return energyLevel > 2;
	}

}