package core.iramMakinoon;

public class IramVerify extends EventIramMakinoon{
	private boolean check;
	public IramVerify(int[][] catchArray) {
		int sum = 0;
		check = true;
		//row check
		for(int i = 0; i < 3; i++){
			sum=0;
			for(int j = 0 ;j < 3; j++) {
				sum = sum + catchArray[i][j];
			}
			if(sum != 15) {
				check=false;
				break;
			}
			//column check
			sum=0;
			for(int j = 0;j < 3;j++) {
				sum= sum+ catchArray[j][i];
			}
			if(sum!=15) {
				check=false;
				break;
			}
			//diagonal check 
			sum=0;
			for(int j = 0; j < 3;j++) {
				sum = sum + catchArray[j][j];
			}
			if(sum!=15) {
				check=false;
			}
			sum=0;
			for(int j = 2;j >= 0; j--) {
				sum+= catchArray[i][j];
			}
			if(sum!=15) {
				check=false;
			}

		}
		if(check){
			System.out.println("Good job you can move on!");
		}
		else{
			System.out.println("Keep Trying!");
		}
	}
	public boolean getCheck(){
		return check; 
	}

}