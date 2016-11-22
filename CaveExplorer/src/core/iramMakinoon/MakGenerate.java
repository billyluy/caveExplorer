package core.iramMakinoon;

public class MakGenerate {
	private static int[][] magicSq = new int[3][3];
	static int[][] puzzles = new int[5][9];// arrays and their lengths
	private int[] missingIndex = new int[4];

	static int[] Array1 = { 4, 9, 2, 3, 5, 7, 8, 1, 6 };
	static int[] Array2 = { 8, 3, 4, 1, 5, 9, 6, 7, 2 };
	static int[] Array3 = { 2, 7, 6, 9, 5, 1, 4, 3, 8 };
	static int[] Array4 = { 6, 1, 8, 7, 5, 3, 2, 9, 4 };
	static int[] Array5 = { 2, 9, 4, 7, 5, 3, 6, 1, 8 };

	public MakGenerate() {

		puzzles[0] = Array1;
		puzzles[1] = Array2;
		puzzles[2] = Array3;
		puzzles[3] = Array4;
		puzzles[4] = Array5;
		
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
	public static int[][] getPuzzle(){
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
	//
	

	public void printPuzzle() {
		String[] letters = {"a", "b", "c", "d"};
		int count = 0;
		
		
		for (int row = 0; row < magicSq.length; row++) {
			for (int col = 0; col < magicSq[0].length; col++) {
				System.out.print("|");
				if (isMissing(row, col)) {
						System.out.print(letters[count++]);
				} else {
					System.out.print( magicSq[row][col] );
				}
				
				if (col == magicSq[0].length - 1) {
					System.out.print("|");
				}
			}
			System.out.println();
		}
		
	}

	public static int[] generateIndex() {
		int[] array = { -1, -1, -1, -1 };// doesn't confuse with zeros

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