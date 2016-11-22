package core.iramMakinoon;

import java.util.Scanner;

import core.CaveExplorer;
import core.Event;

public class EventIramMakinoon implements Event {
	static Scanner input1 = new Scanner(System.in);

	public static String getInput() {
		return input1.nextLine();
	}

	public static final String[] SEQ_1 = { "You came the wrong way.",
			"Here lies the ghost that takes numbers away from arrays",
			"The ghost will not let you pass until you defeat it by completing the magic square"
			, "Make sure all rows, columns and diagonals are equal"
			,"That's all I am telling you, hahahah!"};
	
	public static final String[] SEQ_2 = { "You shall pass", "You succeeded",
			"You beat the ghost but I advise you to never come back" };
	public static final String[] SEQ_3 = { "Keep Trying" };

	MakGenerate board;
	MakInput input;

	public void play() {
		readSequence(SEQ_1);
		board = new MakGenerate();
		int[][] puzzle = MakGenerate.getPuzzle();
		int[] captureIndex = board.getMissingIndex();

		int wrongs = 0;
		boolean verify = false;
		while (!verify) {
			if (wrongs > 1) {
				System.out.println(
						"You seem to be struggling.\n "
						+ "Do you want to take the easy way"
						+ " out by \n"
						+ "saying 'I love Iram and Makinoon'?");
				String yesLst = "yes yeah ye ";
				if (yesLst.indexOf(getInput()) != -1) {
					System.out.println("Say it!");
					String input = getInput();
					if (input.equals("I love Iram and Makinoon")) {
						break;
					}
				} else {
					wrongs = 0;
				}
			}
			
			input = new MakInput(puzzle, captureIndex);
			verify = input.getCorrect();
			if (!verify) {
				readSequence(SEQ_3);
				board.printPuzzle();
			}
			
			wrongs++;
		}
		
		readSequence(SEQ_2);
	}

	private static void readSequence(String[] seq) {
		for (String s : seq) {
			CaveExplorer.print(s);
			CaveExplorer.print("- - - Press Enter - - -");
			CaveExplorer.in.nextLine();
		}

	}

}