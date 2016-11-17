package iramMakinoon;

import java.util.Scanner; 

public class MakInput extends EventIramMakinoon{
 	public static Scanner in; 
	 
 	public MakInput() {
		in = new Scanner(System.in);
		System.out.println("What number do you want to input for a?");
		String response = in.nextLine();
		while (!response.matches("[0-9]")) {
			//matches checks from 0  to 9 
			System.out.println("Not number");
			response = in.nextLine();
		}
		
		int num = Integer.parseInt(response);
		
 	}

}