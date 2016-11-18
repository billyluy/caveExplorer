package core.iramMakinoon;

import core.Event;

public class EventIramMakinoon implements Event {

	MakGenerate board; 
	MakInput input; 
	
	public static void main (String[] args){
		 EventIramMakinoon test = new EventIramMakinoon();
		 test.play();
	}
	public void play(){
		board = new MakGenerate();
		int[][] puzzle = board.getPuzzle(); 
		int[] captureIndex = board.getMissingIndex(); 
		 boolean verify = false; 
		
		 while(!verify){
			input = new MakInput(puzzle, captureIndex);
			verify = input.getCorrect(); 
			
		}
		
	}

}