package core.BillyJiaMing;

import core.CaveExplorer;

public class JiaMingInput {
	private static int row; 
	private static int col; 
	private static String input;
	private static int dir;
	private static boolean[]dirAvailable;
	
	public static final int UP=0;
	public static final int RIGHT=1;
	public static final int DOWN=2;
	public static final int LEFT=3;
	
	public static void placeShip() {
		int[] shipLength={5,4,3,2};
		String[] shipTypes={"Carrier(Length 5)","Battleship(Length 4)","Submarine(Length 3)","Destroyer(Length 2)"};
		CaveExplorer.print("It's time to place your 4 ships.");
		CaveExplorer.print("- - - Press Enter - - -");
		input=CaveExplorer.in.nextLine();
		for(int i=0;i<shipTypes.length;i++){
			printUserMap();
			CaveExplorer.print("Where do you want to place your "+shipTypes[i]+"?");
			pickACoord(shipLength[i]);
			printUserMap();
			pickDirectionToPlace(shipLength[i]);			
		}
	}
	
	public static void updateBotMap() {
		EventBillyJiaMing.printArr(EventBillyJiaMing.botMap);
		CaveExplorer.print("This is the bot's map.");
		CaveExplorer.print("Where do you wanna attack? Enter \"(row,col)\"");
		while(!validAttackLoc()){
			CaveExplorer.print("Invalid location, pick another location.\"(row,col)\"");
		}
		if(EventBillyJiaMing.botShip[row][col].equals("O")){
			EventBillyJiaMing.botMap[row][col]="-";
			CaveExplorer.print("Enemy ship hit at ("+row+","+col+").");
		}else{
			EventBillyJiaMing.botMap[row][col]="X";
			CaveExplorer.print("You did not hit any enemy ships at ("+row+","+col+").");
		}
		EventBillyJiaMing.printArr(EventBillyJiaMing.botMap);
		CaveExplorer.print("- - - Press Enter - - -");
		CaveExplorer.in.nextLine();
	}
	
	private static void printUserMap() {
		EventBillyJiaMing.printArr(EventBillyJiaMing.userMap);
	}

	private static void pickDirectionToPlace(int shipLength) {
		CaveExplorer.print("That's a valid spot, now which direction do you want the ship to face in this spot?");
		CaveExplorer.print("Enter \"up\",\"right\",\"down\", or \"left\"");
		while(!validDir()){
			printUserMap();
			CaveExplorer.print("Invalid dir.");
		}
		updateMapWithShip(shipLength,dir);
	}

	private static void updateMapWithShip(int shipLength, int dir) {
		if(dir==UP){
			for(int r=row;r>row-shipLength;r--){
				EventBillyJiaMing.userMap[r][col]="O";
			}
		}
		if(dir==RIGHT){
			for(int c=col;c<col+shipLength;c++){
				EventBillyJiaMing.userMap[row][c]="O";
			}
		}
		if(dir==DOWN){
			for(int r=row;r<row+shipLength;r++){
				EventBillyJiaMing.userMap[r][col]="O";
			}
		}
		if(dir==LEFT){
			for(int c=col;c>col-shipLength;c--){
				EventBillyJiaMing.userMap[row][c]="O";
			}
		}
	}

	private static boolean validDir() {
		input = CaveExplorer.in.nextLine();
		input = input.toLowerCase();
		if(input.indexOf("up")>-1){
			if(dirAvailable[UP]){
				dir=UP;
				return true;
			}
		}
		if(input.indexOf("right")>-1){
			if(dirAvailable[RIGHT]){
				dir=RIGHT;
				return true;
			}
		}
		if(input.indexOf("down")>-1){
			if(dirAvailable[DOWN]){
				dir=DOWN;
				return true;
			}
		}
		if(input.indexOf("left")>-1){
			if(dirAvailable[LEFT]){
				dir=LEFT;
				return true;
			}
		}
		return false;
	}

	private static void pickACoord(int shipLength) {
		CaveExplorer.print("Pick a coord by typing \"(row,col)\"");
			while(!validCoord(shipLength)){
				printUserMap();
				CaveExplorer.print("Enter a valid coord \"(row,col)\"");
			}
			EventBillyJiaMing.userMap[row][col]="O";
		}

	private static boolean coordNotTaken(int shipLength) {
		dirAvailable= new boolean[]{true,true,true,true};
		for(int r=row;r<row+shipLength;r++){ //checks if there is space under of this coord
			if(isOutOfBounds(shipLength,DOWN)){ 
				dirAvailable[DOWN]=false;
				break; //added break for more efficiency, no need to go thru the whole loop
			}else if(EventBillyJiaMing.userMap[r][col].equals("O")){
				dirAvailable[DOWN]=false;
				break;
			}
		}
		for(int r=row;r>row-shipLength;r--){ //checks if there is space above of this coord
			if(isOutOfBounds(shipLength,UP)){ 
				dirAvailable[UP]=false;
				break;
			}else if(EventBillyJiaMing.userMap[r][col].equals("O")){
				dirAvailable[UP]=false;
				break;
			}
		}
		for(int c=col;c>col-shipLength;c--){ //checks if there is space left of this coord
			if(isOutOfBounds(shipLength,LEFT)){ 
				dirAvailable[LEFT]=false;
				break;
			}else if(EventBillyJiaMing.userMap[row][c].equals("O")){
				dirAvailable[LEFT]=false;
				break;
			}
		}
		for(int c=col;c<col+shipLength;c++){ //checks if there is space right of this coord
			if(isOutOfBounds(shipLength,RIGHT)){ 
				dirAvailable[RIGHT]=false;
				break;
			}else if(EventBillyJiaMing.userMap[row][c].equals("O")){
				dirAvailable[RIGHT]=false;	
				break;
			}
		}
		boolean anyDirAvail=false;
		for(int i=0;i<dirAvailable.length;i++){
			if(dirAvailable[i]){
				anyDirAvail=true;
			}
		}
		return anyDirAvail;
	}
	private static boolean isOutOfBounds(int shipLength, int dir) {
		if(dir==DOWN){
			if(row+shipLength>9){
				return true;
			}
		}
		if(dir==UP){
			if(row-shipLength<0){
				return true;
			}
		}
		if(dir==LEFT){
			if(col-shipLength<0){
				return true;
			}
		}
		if(dir==RIGHT){
			if(col+shipLength>9){
				return true;
			}
		}
		return false;
	}

	private static boolean validCoord(int shipLength) {
		input=CaveExplorer.in.nextLine();
		int commaIndex=input.indexOf(",");
		int leftParaIndex=input.indexOf("(");
		int rightParaIndex=input.indexOf(")");
		if(commaIndex>leftParaIndex && rightParaIndex>commaIndex){
			String row=input.substring(leftParaIndex+1, commaIndex);
			String col=input.substring(commaIndex+1, rightParaIndex);
			if(isNumber(row) && isNumber(col)){
				changeRowCol(row,col);
				if(JiaMingInput.row>=0 && JiaMingInput.row<=9 && JiaMingInput.col>=0 && JiaMingInput.col<=9){
					return coordNotTaken(shipLength);
				}
			}
		}
		return false;
	}
	
	private static void changeRowCol(String sRow, String sCol) {
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

	private static boolean validAttackLoc() {
		input=CaveExplorer.in.nextLine();
		int commaIndex=input.indexOf(",");
		int leftParaIndex=input.indexOf("(");
		int rightParaIndex=input.indexOf(")");
		if(commaIndex>leftParaIndex && rightParaIndex>commaIndex){
			String row=input.substring(leftParaIndex+1, commaIndex);
			String col=input.substring(commaIndex+1, rightParaIndex);
			if(isNumber(row) && isNumber(col)){
				changeRowCol(row,col);
				if(JiaMingInput.row>=0 && JiaMingInput.row<=9 && JiaMingInput.col>=0 && JiaMingInput.col<=9){
					if(EventBillyJiaMing.botMap[JiaMingInput.row][JiaMingInput.col].equals("X")==false || EventBillyJiaMing.botMap[JiaMingInput.row][JiaMingInput.col].equals("-")==false){
						return true;
					}
				}
			}
		}
		return false;
	}

}
