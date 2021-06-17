package com.revature.classes.menu.submenus;

import java.util.Scanner;

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
		
		terminate = menuSelection(in, menus, terminate);
		
		return terminate;
	}

	public boolean menuSelection(Scanner in, Menu menus, boolean terminate) {
		switch(in.nextInt()) {
			case 1:
				menus = new MainMenu(in);
				terminate = menus.display(menus, terminate);
				break;
			case 2:
				terminate = true;
				break;
		}
		return terminate;
	}

}
