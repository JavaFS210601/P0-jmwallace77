package com.revature.classes.menu.submenus;

import java.util.Scanner;

import com.revature.classes.ValidationMethods;
import com.revature.classes.menu.Menu;

public class MainMenu extends Menu {
	private Scanner in;

	public MainMenu(Scanner in) {
		super();
		this.in = in;
	}

	@Override
	public boolean display(Menu menus, boolean terminate) {
		System.out.println("=======================================");
		System.out.println("Main");
		System.out.println("=======================================");
		System.out.println("1. Login");
		System.out.println("2. Guest");
		System.out.println("3. Exit");
		System.out.println("=======================================");
		
		terminate = menuSelection(menus, terminate);
		
		return terminate;
	}

	@Override
	public boolean menuSelection(Menu menus, boolean terminate) {
		switch(ValidationMethods.menuValidation(in)) {
			case 1:
				menus = new LoginMenu(new Scanner(System.in));
				terminate = menus.display(menus, terminate);
				break;
			case 2:
				menus = new GuestMenu(new Scanner(System.in));
				terminate = menus.display(menus, terminate);
				break;
			case 3:
				terminate = true;
				break;
		}
		return terminate;
	}

}
