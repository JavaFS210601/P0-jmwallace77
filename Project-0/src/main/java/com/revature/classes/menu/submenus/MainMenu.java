package com.revature.classes.menu.submenus;

import java.util.Scanner;

import org.apache.logging.log4j.Logger;

import com.revature.classes.ValidationMethods;
import com.revature.classes.menu.Menu;

public class MainMenu extends Menu {
	private Scanner in;

	public MainMenu(Scanner in) {
		super();
		this.in = in;
	}
	
	//displays the main menu in the console
	@Override
	public boolean display(Menu menus, boolean terminate, Logger log) {
		log.info("In the main menu");
		System.out.println("=======================================");
		System.out.println("Main");
		System.out.println("=======================================");
		System.out.println("1. Login");
		System.out.println("2. Guest");
		System.out.println("3. Exit");
		System.out.println("=======================================");
		
		terminate = menuSelection(menus, terminate, log);
		
		return terminate;
	}
	
	//user can select to login, enter as a guest, or exit
	@Override
	public boolean menuSelection(Menu menus, boolean terminate, Logger log) {
		switch(ValidationMethods.menuValidation(in)) {
			case 1:
				menus = new LoginMenu(new Scanner(System.in));
				terminate = menus.display(menus, terminate, log);
				break;
			case 2:
				menus = new GuestMenu(new Scanner(System.in));
				terminate = menus.display(menus, terminate, log);
				break;
			case 3:
				terminate = true;
				break;
		}
		return terminate;
	}

}
