package com.revature.classes.menu.submenus;

import java.util.Scanner;

import com.revature.classes.menu.Menu;

public class LoginMenu extends Menu {
	private Scanner in;
	
	public LoginMenu(Scanner in) {
		super();
		this.in = in;
	}

	public boolean display(Menu menus, boolean terminate) {
		
		terminate = menuSelection(in, menus, terminate);
		return terminate;

	}

	public boolean menuSelection(Scanner in, Menu menus, boolean terminate) {
		// TODO Auto-generated method stub
		switch(1) {
			case 1:
				menus = new EmployeeMenu(in);
				terminate = menus.display(menus, terminate);
				break;
		}
		return terminate;
	}

}
