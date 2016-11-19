package core.BillyJiaMing;

import core.CaveExplorer;

public class JiaMingInput {
	private static int row; //y
	private static int col; //x
	private static String input;
	
	public static String[][] placeShip() {
		int[] shipLength={5,4,3,2};
		String[] shipTypes={"Carrier(Length 5)","Battleship(Length 4)","Submarine(Length 3)","Destroyer(Length 2)"};
		CaveExplorer.print("It's time to place your 4 ships.");
		CaveExplorer.print("- - - Press Enter - - -");
		for(int i=0;i<shipTypes.length;i++){
			CaveExplorer.print("Where do you want to place your "+shipTypes[i]+"? Pick a coord by typing \"(row,col)\"");
			input=CaveExplorer.in.nextLine();
			pickACoord();
			pickDirectionToPlace();			
		}
		return null;
		
	}
	
	private static void pickDirectionToPlace() {
		
		
	}

	private static void pickACoord() {
		boolean notValid=true;
		while(notValid){
			while(!validCoord()){
				System.out.println("Enter a valid coord \"(row,col)\"");
				input=CaveExplorer.in.nextLine();
			}
			if(EventBillyJiaMing.userMap[row][col].equals(" ")){
				notValid=false;
			}
		}
	}

	private static boolean validCoord() {
		int commaIndex=input.indexOf(",");
		String row=input.substring(commaIndex-1, commaIndex);
		String col=input.substring(commaIndex+1, commaIndex+2);
		if(isNumber(row) && isNumber(col)){
			changeRowCol(col,row);
			return true;
		}
		return false;
	}
	private static void changeRowCol(String sCol, String sRow) {
		row=Integer.parseInt(sRow);
		col=Integer.parseInt(sCol);
	}
	private static boolean isNumber(String str){
		try{  
			double d = Double.parseDouble(str);  
		}  
		catch(NumberFormatException err){ //if try statement returns this error, then return false 
		    return false;  
		}  
		return true;  
	}
	private static void updateBotHit() {
		// TODO this returns coords of place user picks
		//CHEAT CODE
		//EventBillyJiaMing.userHit[1][1];
	}

}
