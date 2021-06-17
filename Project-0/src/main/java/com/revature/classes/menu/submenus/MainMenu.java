package com.revature.classes.menu.submenus;

import java.util.Scanner;

import com.revature.classes.menu.Menu;

public class MainMenu extends Menu {
	private Scanner in;

	public MainMenu(Scanner in) {
		super();
		this.in = in;
	}

	public boolean display(Menu menus, boolean terminate) {
		System.out.println("=======================================");
		System.out.println("Main");
		System.out.println("=======================================");
		System.out.println("1. Login");
		System.out.println("2. Guest");
		System.out.println("3. Exit");
		System.out.println("=======================================");
		
		terminate = menuSelection(in, menus, terminate);
		
		return terminate;
	}

	public boolean menuSelection(Scanner in, Menu menus, boolean terminate) {
		switch(in.nextInt()) {
			case 1:
				menus = new LoginMenu(in);
				terminate = menus.display(menus, terminate);
				break;
			case 2:
				menus = new GuestMenu(in);
				terminate = menus.display(menus, terminate);
				break;
			case 3:
				terminate = true;
				break;
		}
		return terminate;
	}

}
