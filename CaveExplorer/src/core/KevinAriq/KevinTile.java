package core.KevinAriq;

public class KevinTile {
	private boolean hasEnergy;
	private boolean[] exits;
	
	public KevinTile() {
		exits = new boolean[4];
	}

	public void createEntrance(int direction) {
		exits[direction] = true;
	}

	public boolean hasEntrance(int direction) {
		return exits[direction];
	}

	public void setEnergy(boolean hasEnergy) {
		this.hasEnergy = hasEnergy;
	}

	public boolean hasEnergy() {
		return hasEnergy;
	}
}