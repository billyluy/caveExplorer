package core.iramMakinoon;

import java.util.Scanner;

import core.CaveExplorer;
import core.Event;


public class EventIramMakinoon implements Event {
	static Scanner input1 = new Scanner(System.in);
	public static String getInput(){
		return input1.nextLine();
	}
	public static final String[] SEQ_1 = {"You came the wrong way.","Here lies the ghost that takes numbers away from arrays",
	"The ghost will not let you pass until you defeat it by completing the magic square"};
	public static final String[] SEQ_2 = {"You shall pass","You succeeded"
			,"You beat the ghost but I advise you to never come back"};
	public static final String[] SEQ_3 = {"Keep Trying"};


	MakGenerate board; 
	MakInput input; 

	public static void main (String[] args){
		CaveExplorer.in = new Scanner(System.in);
		EventIramMakinoon test = new EventIramMakinoon();
		test.play();
	}
	public void play(){
		readSequence(SEQ_1);
		int wrongs = 0;
		board = new MakGenerate();
		int[][] puzzle = MakGenerate.getPuzzle(); 
		int[] captureIndex = board.getMissingIndex(); 
		input = new MakInput(board, puzzle, captureIndex);

		boolean verify = input.getCorrect();
		System.out.println(verify);
		while(verify){
			readSequence(SEQ_2);
			break;
		}
		while(!verify){
			wrongs++;
			readSequence(SEQ_3);
			if(wrongs >= 2 ){
				System.out.println("You seem to be struggling do you want to take the easy way out by saying you love Iram and Makinoon?"); 
				String [] yesLst = {"yes", "yeah", "ye",};
				if(input1.equals(yesLst)){
					System.out.println("Say it!");
					String input = getInput();
					if(input.equals("I love Iram and Makinoon.")){
						return;
					}
				}
				else{
					wrongs = 0;
				}
			}
		}
	}
	private static void readSequence(String[] seq) {
		for(String s: seq){
			CaveExplorer.print(s);
			CaveExplorer.print("- - - Press Enter - - -");
			CaveExplorer.in.nextLine();
		}

	}

}