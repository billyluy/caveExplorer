package core.KevinAriq;

import java.util.ArrayList;
import java.util.Comparator;

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
		tick %= 37;

		if (tick % 2 != 0)
			return;

		// move in random behavior for 10 ticks of 20 tick cycle
		if (tick <= 20 || map.player.hasSufficientEnergy()) {
			// randomly change direction if can
			double rand = Math.random();
			if (rand < 0.4) {
				if (direction == CaveRoom.EAST || direction == CaveRoom.WEST) {
					if (canMove(CaveRoom.NORTH) && rand < 0.20) {
						setDirection(CaveRoom.NORTH);
					} else if (canMove(CaveRoom.SOUTH)) {
						setDirection(CaveRoom.SOUTH);
					} else if (canMove(CaveRoom.NORTH)) {
						setDirection(CaveRoom.NORTH);
					}
				} else if (direction == CaveRoom.NORTH || direction == CaveRoom.SOUTH) {
					if (canMove(CaveRoom.EAST) && rand > 0.20) {
						setDirection(CaveRoom.EAST);
					} else if (canMove(CaveRoom.WEST)) {
						setDirection(CaveRoom.WEST);
					} else if (canMove(CaveRoom.EAST)) {
						setDirection(CaveRoom.EAST);
					}
				}
			}

			// if hit an obstacle, change directions otherwise keep moving
			if (!canMove(direction)) {
				changeDirection();
			} else {
				move(direction);
			}
		} else {

			GraphNode goal = new GraphNode(null, map.player.getX(), map.player.getY());
			GraphNode nextRoom = findShortestPath(goal);

			GraphNode current = nextRoom;
			while (current.getParent().getParent() != null) {
				current = current.getParent();
			}

			setDirection(current.directionToParent);
			move(current.directionToParent);
		}
	}

	public String getCharacter() {
		switch (direction) {
		case CaveRoom.NORTH:
			return "^";
		case CaveRoom.EAST:
			return ">";
		case CaveRoom.SOUTH:
			return "v";
		case CaveRoom.WEST:
			return "<";
		}

		return "#";
	}

	public void changeDirection() {
		int dir = -1;
		while ((dir = chooseRandomDirection()) == direction || !canMove(dir))
			;
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

	// ****************************
	// * *
	// * A* pathfinding algorithm *
	// * *
	// ****************************

	public GraphNode findShortestPath(GraphNode goal) {
		ArrayList<GraphNode> explored = new ArrayList<>();
		ArrayList<GraphNode> queue = new ArrayList<>();

		// insert starting node of ghosts location
		GraphNode loc = new GraphNode(goal, this.getX(), this.getY());
		queue.add(loc);

		// keep running until all paths have been explored
		while (!queue.isEmpty()) {
			// retrieve node from queue with lowest cost
			GraphNode current = queue.get(0);
			for (GraphNode queued : queue) {
				if (queued.fCost() < current.fCost()
						|| queued.fCost() == current.fCost() && queued.hCost < current.hCost) {
					current = queued;
				}
			}

			queue.remove(current);
			explored.add(current);

			// check if node is target
			if (current.equals(goal)) {
				return current;
			}

			// else handle the neighbors
			for (GraphNode neighbor : current.getNeighbors()) {
				// check if already explored path
				if (explored.contains(neighbor)) {
					continue;
				}

				// create neighbor nodes and add to queue to be explored
				int newCost = current.gCost + neighbor.distanceFrom(current);
				if (newCost < neighbor.gCost || !explored.contains(neighbor)) {
					neighbor.gCost = newCost;
					neighbor.hCost = neighbor.distanceFrom(goal);

					if (queue.contains(neighbor))
						queue.remove(neighbor);

					queue.add(neighbor);
				}
			}
		}

		return null;
	}

	class GraphNode {

		private GraphNode parent, goal;
		private int x, y;
		private int hCost, gCost;
		private int directionToParent;

		public GraphNode(GraphNode goal, int x, int y) {
			this.goal = goal;
			this.x = x;
			this.y = y;
		}

		public GraphNode(GraphNode parent, GraphNode goal, int x, int y, int directionToParent) {
			this(goal, x, y);
			this.parent = parent;
			this.directionToParent = directionToParent;
		}

		public GraphNode getParent() {
			return parent;
		}

		@Override
		public boolean equals(Object o) {
			if (!(o instanceof GraphNode)) {
				return false;
			}

			GraphNode node = (GraphNode) o;
			return node.getX() == this.x && node.getY() == this.y;
		}

		public ArrayList<GraphNode> getNeighbors() {
			ArrayList<GraphNode> neighbors = new ArrayList<GraphNode>();

			if (y > 0 && (map.tiles[y][x].hasEntrance(CaveRoom.NORTH)
					|| map.tiles[y - 1][x].hasEntrance(CaveRoom.SOUTH))) {
				neighbors.add(new GraphNode(this, goal, x, y - 1, CaveRoom.NORTH));
			}

			if (y < map.getBorderY() - 1 && (map.tiles[y][x].hasEntrance(CaveRoom.SOUTH)
					|| map.tiles[y + 1][x].hasEntrance(CaveRoom.NORTH))) {
				neighbors.add(new GraphNode(this, goal, x, y + 1, CaveRoom.SOUTH));
			}

			if (x < map.getBorderX() - 1
					&& (map.tiles[y][x].hasEntrance(CaveRoom.EAST) || map.tiles[y][x + 1].hasEntrance(CaveRoom.WEST))) {
				neighbors.add(new GraphNode(this, goal, x + 1, y, CaveRoom.EAST));
			}

			if (x > 0
					&& (map.tiles[y][x].hasEntrance(CaveRoom.WEST) || map.tiles[y][x - 1].hasEntrance(CaveRoom.EAST))) {
				neighbors.add(new GraphNode(this, goal, x - 1, y, CaveRoom.WEST));
			}

			return neighbors;
		}

		public int distanceFrom(GraphNode node) {
			int dx = Math.abs(this.getX() - node.getX());
			int dy = Math.abs(this.getY() - node.getY());

			return 10 * (dx + dy);
		}

		public int fCost() {
			return gCost + hCost;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

	}

}