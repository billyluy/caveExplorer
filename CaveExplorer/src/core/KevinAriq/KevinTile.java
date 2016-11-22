package core.KevinAriq;

public class KevinTile {
	private boolean hasEnergy;
	private boolean[] exits;
	
	public KevinTile() {
		// starts as north - false, east - false, south - false, west - false
		exits = new boolean[4];
	}

	// create an entrance in a direction
	public void createEntrance(int direction) {
		exits[direction] = true;
	}

	// check if a direction has an entrance
	public boolean hasEntrance(int direction) {
		return exits[direction];
	}

	// set if tile has energy
	public void setEnergy(boolean hasEnergy) {
		this.hasEnergy = hasEnergy;
	}

	public boolean hasEnergy() {
		return hasEnergy;
	}
}