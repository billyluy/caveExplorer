package core;


import java.util.Scanner;

import core.BillyJiaMing.EventBillyJiaMing;

public class CaveExplorer {
	
	public static CaveRoom caves [][];
	public static Scanner in;
	public static CaveRoom currentRoom;
	public static Inventory inventory;
	
	public static void main(String[] args){
		in = new Scanner(System.in);
		caves = new CaveRoom[5][5];
		for(int row =0; row <caves.length; row++){
			for(int col =0; col<caves[row].length;col++){
				caves[row][col] = new CaveRoom("This cave has coordinates"+row+","+col);
			}
		}
		//ADD EVENT ROOMS
		//caves[0][2] = new EventRoom("This is the room where that guy with a tail met you", new GameStartEvent());
		caves[0][2] = new EventRoom("ship", new EventBillyJiaMing());
		currentRoom = caves[0][1];
		currentRoom.enter();
		//[0][1] is connected to [0][2]
		caves[0][1].setConnection(CaveRoom.EAST, caves[0][2], new Door());
		caves[0][2].setConnection(CaveRoom.SOUTH, caves[1][2], new Door());
		caves[1][2].setConnection(CaveRoom.SOUTH, caves[2][2], new Door());
		inventory = new Inventory();
		startExploring();
		
	}

	public static void startExploring() {
		while(true){
			System.out.println(inventory.getDescription());
			System.out.println(currentRoom.getDescription());
			System.out.println("What would you like to do?");	
			String input = in.nextLine();
			currentRoom.interpretInput(input);
		}
	}
	
	public static void print(String string){
		System.out.println(string);
	}
}
