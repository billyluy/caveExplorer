package core.BillyJiaMing;

import core.CaveExplorer;

public class JiaMingInput {

	public static String[][] placeShip() {
		boolean[] placedShip=new boolean[4];
		String[] shipTypes={"Carrier(Length 5)","Battleship(Length 4)","Submarine(Length 3)","Destroyer(Length 2)"};
		System.out.println("It's time to place your 4 ships.");
		System.out.println("- - - Press Enter - - -");
		CaveExplorer.in.nextLine();
		while(stillShipLeft(placedShip)){
			for(int i=0;i<4;i++){
				if(placedShip[i]==false){
					System.out.println("Enter "+i+" to place "+shipTypes[i]);
				}
			}
			String input = CaveExplorer.in.nextLine();
			if(validInput(placedShip,input)){
				EventBillyJiaMing.printArr(EventBillyJiaMing.userMap);
				System.out.println("Where would you like to place the ship? Pick a coord(x,y)");
			}
		}
	}

	private static boolean validInput(boolean[] placedShip, String input) {
		for(int i=0;i<placedShip.length;i++){
			if(input.indexOf(i)>-1 && placedShip[i]==false){
				return true
			}
		}
		return false;
	}

	private static boolean stillShipLeft(boolean[] placedShip) {
		for(int i=0;i<placedShip.length;i++){
			if(placedShip[i]==false){
				return true;
			}
		}
		return false;
	}

	public static void updateBotHit() {
		// TODO this returns coords of place user picks
		//CHEAT CODE
		//EventBillyJiaMing.userHit[1][1];
	}

}
