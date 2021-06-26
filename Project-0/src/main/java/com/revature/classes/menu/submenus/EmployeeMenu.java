package com.revature.classes.menu.submenus;

import java.util.Scanner;

import org.apache.logging.log4j.Logger;

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
	public boolean display(Menu menus, boolean terminate, Logger log) {
		log.info("In the employee menu");
		user.setName(LoginMenu.user.getName());
		user.setUserID(LoginMenu.user.getUserID());
		user.setUsername(LoginMenu.user.getUsername());
		user.setPassword(LoginMenu.user.getPassword());
		user.setUserType(LoginMenu.user.getUserType());
		
		System.out.println("=======================================");
		System.out.println("Welcome " + QueryFormationControl.getNameWage(user, log));
		System.out.println("=======================================");
		System.out.println("1. Time Sheet");
		System.out.println("2. Main");
		System.out.println("3. Exit");
		System.out.println("=======================================");

		terminate = menuSelection(menus, terminate, log);
		
		return terminate;
	}
	@Override
	public boolean menuSelection(Menu menus, boolean terminate, Logger log) {
		switch(ValidationMethods.menuValidation(in)) {
			case 1:
				user.addTimeSheet(user, log);
				user.addTimeSheetEntries(user, in, log);
				terminate = menus.display(menus, terminate, log);
				break;
			case 2:
				menus = new MainMenu(new Scanner(System.in));
				terminate = menus.display(menus, terminate, log);
				break;
			case 3:
				terminate = true;
				break;
		}
		return terminate;
	}

}
