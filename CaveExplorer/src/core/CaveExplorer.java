package core;

import java.util.Scanner;

import core.EventRoom;
import core.GameStartEvent;
import core.BillyJiaMing.EventBillyJiaMing;
import core.KevinAriq.KevinPacmanEvent;
import core.iramMakinoon.EventIramMakinoon;

public class CaveExplorer {

	public static CaveRoom caves[][];
	public static Scanner in;
	public static CaveRoom currentRoom;
	public static Inventory inventory;

	public static void main(String[] args) {
		in = new Scanner(System.in);
		caves = new CaveRoom[5][5];
		for (int row = 0; row < caves.length; row++) {
			for (int col = 0; col < caves[row].length; col++) {
				caves[row][col] = new CaveRoom("This cave has coordinates " + row + "," + col);
			}
		}
		
		caves[0][1] = new EventRoom("This is where you found the map", new GameStartEvent());
		caves[1][1] = new EventRoom("This is the room where you battled the ghost to battleship.", new EventBillyJiaMing());
		caves[2][2] = new EventRoom("This is where the ghosts comes from", new EventIramMakinoon());
		caves[3][3] = new EventRoom("This is the room where you captured the ghosts.", new KevinPacmanEvent());
		caves[4][3] = new EventRoom("You found the exit here", new GameEndEvent());

		caves[0][0].setConnection(CaveRoom.EAST, caves[0][1], new Door());
		caves[0][1].setConnection(CaveRoom.SOUTH, caves[1][1], new Door());
		caves[1][1].setConnection(CaveRoom.SOUTH, caves[2][1], new Door());
		caves[2][1].setConnection(CaveRoom.EAST, caves[2][2], new Door());
		caves[2][2].setConnection(CaveRoom.EAST, caves[2][3], new Door());
		caves[2][3].setConnection(CaveRoom.SOUTH, caves[3][3], new Door());
		caves[3][3].setConnection(CaveRoom.SOUTH, caves[4][3], new Door());

		currentRoom = caves[0][0];
		currentRoom.enter();

		inventory = new Inventory();
		startExploring();

	}

	public static void startExploring() {
		while (true) {
			System.out.println(inventory.getDescription());
			System.out.println(currentRoom.getDescription());
			System.out.println("What would you like to do?");
			String input = in.nextLine();
			currentRoom.interpretInput(input);
		}
	}

	public static void print(String string) {
		System.out.println(string);
	}
}
