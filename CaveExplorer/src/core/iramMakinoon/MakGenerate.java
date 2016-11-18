package core.iramMakinoon;

public class MakGenerate {
	private static int[][] magicSq = new int[3][3];
	static int[][] puzzles = new int[2][9];// arrays and their lengths
	private int[] missingIndex = new int[3];

	static int[] Array1 = { 4, 9, 2, 3, 5, 7, 8, 1, 6 };
	static int[] Array2 = { 8, 3, 4, 1, 5, 9, 6, 7, 2 };

	public MakGenerate() {

		puzzles[0] = Array1;
		puzzles[1] = Array2;
		int rand = (int) (Math.random() * puzzles.length);// randomize the
															// puzzles

		for (int i = 0; i < magicSq.length; i++) {
			for (int j = 0; j < magicSq[0].length; j++) {

				magicSq[i][j] = puzzles[rand][3 * i + j];
			}
		}
		
		missingIndex = generateIndex();

		printPuzzle();
		
	}
	public int[][] getPuzzle(){
		return magicSq; 
	}
	public int[] getMissingIndex(){
		return missingIndex; 
		
	}
	public boolean isMissing(int row, int col) {
		for (int j : missingIndex) {
			if (j == (row * 3) + col) {
				return true;
			}
		}

		return false;
	}
	
	

	public void printPuzzle( ) {
		String[] letters = {"a", "b", "c"};
		int count = 0;
		for (int row = 0; row < magicSq.length; row++) {
			for (int col = 0; col < magicSq[0].length; col++) {
				if (isMissing(row, col)) {
					
						System.out.print(letters[count++]);
					
					
				} else {
					System.out.print(magicSq[row][col] );
				}
			}
			System.out.println();
		}
	}

	public static int[] generateIndex() {
		int[] array = { -1, -1, -1 };// doesn't confuse with zeros

		for (int index = 0; index < array.length; index++) {

			int rand = (int) (Math.random() * puzzles[0].length);
			boolean duplicate = false;
			int temp = searchUnsorted(array, rand);
			if (temp != -1) {
				duplicate = true;
			}
			while (duplicate) {
				rand = (int) (Math.random() * puzzles[0].length);
				duplicate = false;
				temp = searchUnsorted(array, rand);
				if (temp != -1) {
					duplicate = true;
				}

			}
			array[index] = rand;
		}
		for (int j = 0; j < array.length; j++) {
			for (int k = 0; k < array.length; k++) {
				if (array[j] < array[k]) {
					int buffer = array[j];
					array[j] = array[k];
					array[k] = buffer;
				}
			}
		}
		return array;

	}

	public static int searchUnsorted(int[] arrayToSearch, int key) {

		for (int i = 0; i < arrayToSearch.length; i++) {
			if (key == arrayToSearch[i]) {
				return i;
			}
		}
		return -1;

	}

	public static void print(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");

		}
	}
}