package com.revature.classes.menu.submenus;

import java.io.IOException;
import java.util.Scanner;

import com.revature.classes.ValidationMethods;
import com.revature.classes.menu.Menu;

public class EmployeeMenu extends Menu{
	private Scanner in;
	
	public EmployeeMenu(Scanner in) {
		super();
		this.in = in;
	}

	public boolean display(Menu menus, boolean terminate) {
		System.out.println("=======================================");
		System.out.println("Employee");
		System.out.println("=======================================");
		System.out.println("1. Main");
		System.out.println("2. Exit");
		System.out.println("=======================================");

		terminate = menuSelection(menus, terminate);
		
		return terminate;
	}

	public boolean menuSelection(Menu menus, boolean terminate) {
		switch(ValidationMethods.menuValidation(in)) {
			case 1:
				menus = new MainMenu(new Scanner(System.in));
				terminate = menus.display(menus, terminate);
				break;
			case 2:
				terminate = true;
				break;
		}
		return terminate;
	}

}
