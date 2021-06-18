package com.revature.classes;

import java.util.Scanner;

public class ValidationMethods {
	static int input;
	
	//checking menu selection input
	public static int menuValidation(Scanner in) {
		
		try {
			input = Integer.parseInt(in.nextLine());
			return input;
		}
		catch(ArithmeticException e) {
			menuValidation(in);
		}
		catch(NumberFormatException e) {
			menuValidation(in);
		}
		return input;
	}
}
