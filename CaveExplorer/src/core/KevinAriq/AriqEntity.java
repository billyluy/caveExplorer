package core.KevinAriq;

import core.CaveRoom;

// superclass for ariqplayer and kevinghost
public abstract class AriqEntity{

	// store instance of the map so this entity can access locations of other entities and etc.
	protected AriqMap map;
	private int x, y;

	// all subclass must implement this method
	public abstract void update();

	public AriqEntity(AriqMap map, int x, int y){
		this.map = map;
		this.x = x;
		this.y = y;
	}

	// see if this entity can move in a direction
	public boolean canMove(int direction){
		if(direction == -1){
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

	// move this entity in a direction w/o checking collision
	public void move(int direction){
		switch (direction) {
		case CaveRoom.EAST:
			if(x < map.getBorderX() - 1)
				x++;
			break;
		case CaveRoom.NORTH:
			if(y > 0)
				y--;
			break;
		case CaveRoom.SOUTH:
			if(y < map.getBorderY() - 1)
				y++;
			break;
		case CaveRoom.WEST:
			if(x > 0)
				x--;
			break;
		}
	}

	// check if this entity is touching another entity
	public boolean isTouching(AriqEntity entity){
		return entity.getX() == getX() && entity.getY() == getY();
	}

	// check if this entity is at a tile
	public boolean isAtTile(int tileX, int tileY){
		return this.getY() == tileX && this.getX() == tileY;
	}

	public int getX(){
		return x;
	}

	public int getY() {
		return y;
	}
}
