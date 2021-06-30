package com.revature.classes.menu.submenus;

import java.util.Scanner;

import org.apache.logging.log4j.Logger;

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

	//displays the manager menu
	@Override
	public boolean display(Menu menus, boolean terminate, Logger log) {
		log.info("In the manager menu");
		user.setName(LoginMenu.user.getName());
		user.setUserID(LoginMenu.user.getUserID());
		user.setUsername(LoginMenu.user.getUsername());
		user.setPassword(LoginMenu.user.getPassword());
		user.setUserType(LoginMenu.user.getUserType());
		
		System.out.println("=======================================");
		System.out.println("     Welcome " + QueryFormationControl.getNameWage(LoginMenu.user, log));
		System.out.println("=======================================");
		System.out.println("	1. Add Employee");
		System.out.println("	2. Remove Employee");
		System.out.println("	3. Change Username & Password");
		System.out.println("	4. Main Menu");
		System.out.println("	5. Exit");
		System.out.println("=======================================");
		
		terminate = menuSelection(menus, terminate, log);
		
		return terminate;
	}

	//user can either add or remove another user, return to main menu, or exit
	@Override
	public boolean menuSelection(Menu menus, boolean terminate, Logger log) {
		switch(ValidationMethods.menuValidation(in)) {
			case 1:
				user.addEmployee(in, log);
				terminate = menus.display(menus, terminate, log);
				break;
			case 2:
				user.removeEmployee(in, log);
				terminate = menus.display(menus, terminate, log);
				break;
			case 3:
				user.changeUsernamePassword(user, in, log);
				terminate = menus.display(menus, terminate, log);
				break;
			case 4:
				menus = new MainMenu(new Scanner(System.in));
				terminate = menus.display(menus, terminate, log);
				break;
			case 5:
				terminate = true;
				break;
		}
		return terminate;
	}
}
