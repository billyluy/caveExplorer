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
			if(validInput(placedShip))
			EventBillyJiaMing.printArr(EventBillyJiaMing.userMap);
		}
	}

	private static boolean validInput(boolean[] placedShip) {
		for(int i=0;i<placedShip.length;i++){
			if(CaveExplorer.in.nextLine().indexOf(i)>-1 &&){
				
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
