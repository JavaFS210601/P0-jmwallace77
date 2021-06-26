package com.revature.classes.menu.submenus;

import java.util.Scanner;

import com.revature.classes.ValidationMethods;
import com.revature.classes.database.QueryFormationControl;
import com.revature.classes.menu.Menu;
import com.revature.classes.users.usertypes.Employee;

public class EmployeeMenu extends Menu{
	private Scanner in;
	Employee user = new Employee();
	
	public EmployeeMenu(Scanner in) {
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
		System.out.println("Welcome " + QueryFormationControl.getNameWage(user));
		System.out.println("=======================================");
		System.out.println("1. Time Sheet");
		System.out.println("2. Main");
		System.out.println("3. Exit");
		System.out.println("=======================================");

		terminate = menuSelection(menus, terminate);
		
		return terminate;
	}
	@Override
	public boolean menuSelection(Menu menus, boolean terminate) {
		switch(ValidationMethods.menuValidation(in)) {
			case 1:
				user.addTimeSheet(user);
				user.addTimeSheetEntries(user, in);
				terminate = menus.display(menus, terminate);
				break;
			case 2:
				menus = new MainMenu(new Scanner(System.in));
				terminate = menus.display(menus, terminate);
				break;
			case 3:
				terminate = false;
				break;
		}
		return terminate;
	}

}
