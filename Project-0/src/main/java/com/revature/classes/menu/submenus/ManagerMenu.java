package com.revature.classes.menu.submenus;

import java.util.Scanner;

import com.revature.classes.ValidationMethods;
import com.revature.classes.database.QueryFormationControl;
import com.revature.classes.menu.Menu;
import com.revature.classes.users.usertypes.Manager;

public class ManagerMenu extends Menu {
	private Scanner in;
	Manager user = new Manager();

	public ManagerMenu(Scanner in) {
		super();
		this.in = in;
	}

	@Override
	public boolean display(Menu menus, boolean terminate) {
		user.setName(LoginMenu.user.getName());
		user.setUserID(LoginMenu.user.getUserID());
		user.setUsername(LoginMenu.user.getUsername());
		user.setPassword(LoginMenu.user.getPassword());
		user.setUserType(LoginMenu.user.getUserType());
		
		System.out.println("=======================================");
		System.out.println("Welcome " + QueryFormationControl.getName(LoginMenu.user));
		System.out.println("=======================================");
		System.out.println("1. Add Employee");
		System.out.println("2. Remove Employee");
		System.out.println("3. Main Menu");
		System.out.println("4. Exit");
		System.out.println("=======================================");
		
		terminate = menuSelection(menus, terminate);
		
		return terminate;
	}

	@Override
	public boolean menuSelection(Menu menus, boolean terminate) {
		switch(ValidationMethods.menuValidation(in)) {
			case 1:
				user.addEmployee(in);
				terminate = menus.display(menus, terminate);
				break;
			case 2:
				user.removeEmployee(in);
				terminate = menus.display(menus, terminate);
				break;
			case 3:
				menus = new MainMenu(new Scanner(System.in));
				terminate = menus.display(menus, terminate);
				break;
			case 4:
				terminate = true;
				break;
		}
		return terminate;
	}
}
