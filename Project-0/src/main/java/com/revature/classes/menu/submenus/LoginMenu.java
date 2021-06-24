package com.revature.classes.menu.submenus;

import java.util.Scanner;

import com.revature.classes.ValidationMethods;
import com.revature.classes.database.QueryFormationControl;
import com.revature.classes.menu.Menu;
import com.revature.classes.users.User;

public class LoginMenu extends Menu {
	private Scanner in;
	private User user = new User();
	private boolean loginTryAgain = true;
	public LoginMenu(Scanner in) {
		super();
		this.in = in;
	}

	public boolean display(Menu menus, boolean terminate) {
		while(loginTryAgain) {
			System.out.println("=======================================");
			System.out.println("Login");
			System.out.println("=======================================");
			System.out.println("Username: ");
			String username = in.nextLine();
			user.setUsername(username);
			System.out.println("Password: ");
			String password = in.nextLine();
			user.setPassword(password);
			if(QueryFormationControl.tryLogin(user)) {
				loginTryAgain = false;
				terminate = menuSelection(menus, terminate);
			}
			else {
				System.out.println("1. Continue");
				System.out.println("2. MainMenu");
				System.out.println("=======================================");
				terminate = menuSelection(menus, terminate);
			}
			
		}
		
		return terminate;

	}

	public boolean menuSelection(Menu menus, boolean terminate) {
		if(loginTryAgain == true) {
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
			switch(QueryFormationControl.getUserType(user)) {
				case "employee":
					menus = new EmployeeMenu(new Scanner(System.in));
					terminate = menus.display(menus, terminate);
					break;
				case "manager":
					menus = new ManagerMenu(new Scanner(System.in));
					terminate = menus.display(menus, terminate);
					break;
			}
		}
		
		return terminate;
	}

}
