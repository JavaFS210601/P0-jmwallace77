package com.revature.classes.menu.submenus;

import java.util.Scanner;

import org.apache.logging.log4j.Logger;

import com.revature.classes.ValidationMethods;
import com.revature.classes.database.QueryFormationControl;
import com.revature.classes.menu.Menu;
import com.revature.classes.users.User;

public class LoginMenu extends Menu {
	private Scanner in;
	static User user = new User();
	private boolean loginTryAgain = true;
	public LoginMenu(Scanner in) {
		super();
		this.in = in;
	}
	
	//displays the login menu, and asks user for username and password
	@Override
	public boolean display(Menu menus, boolean terminate, Logger log) {
		log.info("In the login menu");
		while(loginTryAgain) {
			System.out.println("=======================================");
			System.out.println("		Login");
			System.out.println("=======================================");
			System.out.println("Username: ");
			String username = in.nextLine();
			user.setUsername(username);
			System.out.println("Password: ");
			String password = in.nextLine();
			user.setPassword(password);
			if(QueryFormationControl.tryLogin(user)) {
				//if user logins in with correct credentials
				log.info("User successfully logged in");
				loginTryAgain = false;
				terminate = menuSelection(menus, terminate, log);
			}
			else {
				//if user uses invalid credentials
				log.info("User failed to login");
				System.out.println("	1. Continue");
				System.out.println("	2. MainMenu");
				System.out.println("=======================================");
				terminate = menuSelection(menus, terminate, log);
			}
			
		}
		
		return terminate;

	}
	
	@Override
	public boolean menuSelection(Menu menus, boolean terminate, Logger log) {
		if(loginTryAgain == true) {
			//switch used for invalid credentials
			switch(ValidationMethods.menuValidation(in)) {
				case 1:
					loginTryAgain = true;
					break;
				case 2:
					loginTryAgain = false;
					terminate = false;
					break;
			}
		}
		else {
			//switch used for valid credentials
			switch(QueryFormationControl.getUserType(user, log)) {
				case "employee":
					//if user is an employee
					menus = new EmployeeMenu(new Scanner(System.in));
					terminate = menus.display(menus, terminate, log);
					break;
				case "manager":
					//if user is a manager
					menus = new ManagerMenu(new Scanner(System.in));
					terminate = menus.display(menus, terminate, log);
					break;
			}
		}
		
		return terminate;
	}

}
