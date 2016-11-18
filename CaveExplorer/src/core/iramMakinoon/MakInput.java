package core.iramMakinoon;

import java.util.Scanner; 

	public class MakInput extends EventIramMakinoon{
	 	public static Scanner in; 
		private int[][] puzzle; 
		private int[] captureIndex; 
		private boolean correct; 
		
	 	public MakInput(int[][] puzzle, int[] captureIndex) {
	 		this.puzzle = puzzle; 
	 		this.captureIndex = captureIndex; 
	 		
	 		int numA = interpretResponse("a");
	 		int numB = interpretResponse("b");
	 		int numC = interpretResponse("c");
	 		
	 		int[][] catchArray = getArray(numA, numB, numC); 
//	 		IramVerify verify = new IramVerify(catchArray);
//	 		correct = verify.getCheck();
	 		
	 		
	 		
	 		print(catchArray);
	 		
	 		
	 		
	 	}
	 	public boolean getCorrect(){
	 		return correct; 
	 	}
	 	private void print(int[][] catchArray) {
	 
			for (int row = 0; row < catchArray.length; row++) {
				for (int col = 0; col < catchArray[0].length; col++) {
					
						System.out.print(catchArray[row][col] );
				}
					System.out.println();
					}
				
			}
		

		public int[][] getArray(int numA, int numB, int numC){
	 			
	 			int xA = getX(this.captureIndex[0]);
	 			int yA = getY(this.captureIndex[0]); 
	 			this.puzzle[xA][yA] = numA; 
	 			
	 			int xB = getX(this.captureIndex[1]);
	 			int yB = getY(this.captureIndex[1]); 
	 			this.puzzle[xB][yB] = numB; 
	 			
	 			int xC = getX(this.captureIndex[2]);
	 			int yC = getY(this.captureIndex[2]); 
	 			this.puzzle[xC][yC] = numC; 
	 			
	 			
	 			return this.puzzle;
	 	}
	 	
	 	
	 	private int getY(int index) {
			return index % 3; //returns column 
		}
		private static int getX(int index) {
			return index/3; //returns the row 
			
		}
		public static int interpretResponse(String input){
	 		in = new Scanner(System.in);
			System.out.println("What number do you want to input for " 
								+ input +"?");
			
			String response = in.nextLine();
			
			while (!response.matches("[0-9]")) {
				//matches checks from 0  to 9 
				System.out.println("Please input a number betweeen 0 and 9");
				response = in.nextLine();
			}
			
	 		return Integer.parseInt(response); 
	 	}

}