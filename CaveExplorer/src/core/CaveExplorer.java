package core;

import java.util.Scanner;

import core.BillyJiaMing.EventBillyJiaMing;
import core.KevinAriq.PacmanEvent;
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

		caves[1][2] = new EventRoom("ship", new EventBillyJiaMing());
		caves[3][2] = new EventRoom("magicsquare", new EventIramMakinoon());
		caves[2][3] = new EventRoom("pacman", new PacmanEvent());

		caves[2][2].setConnection(CaveRoom.SOUTH, caves[3][2], new Door());
		caves[2][2].setConnection(CaveRoom.NORTH, caves[1][2], new Door());
		caves[2][2].setConnection(CaveRoom.EAST, caves[2][3], new Door());

		currentRoom = caves[2][2];
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
