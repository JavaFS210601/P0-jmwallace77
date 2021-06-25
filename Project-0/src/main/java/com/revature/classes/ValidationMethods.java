package com.revature.classes;

import java.util.Scanner;

public class ValidationMethods {
	static int input;
	static double inputD;
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
	
	public static String checkTitle(Scanner in) {
		boolean titleCheck = true;
		String title;
		do {
			title = in.nextLine().toLowerCase();
			if(title.equals("employee") || title.equals("manager")) {
				titleCheck = false;
			}
			else {
				System.out.println("Invalid title. (employee, manager)");
			}
		}while(titleCheck == true);
		
		return title;
	}
	
	public static double wageCheck(Scanner in) {
		
		try {
			inputD = Double.parseDouble(in.nextLine());
			if(inputD < 8 | inputD > 99) {
				System.out.println("Wage to little or to great");
				wageCheck(in);
			}
			return inputD;
		}
		catch(ArithmeticException e) {
			System.out.println("Invalid input");
			wageCheck(in);
		}
		catch(NumberFormatException e) {
			System.out.println("Invalid input");
			wageCheck(in);
		}
		return inputD;
	}
}
