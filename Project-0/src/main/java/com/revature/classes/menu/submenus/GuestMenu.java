package com.revature.classes.menu.submenus;

import java.util.Scanner;

import org.apache.logging.log4j.Logger;

import com.revature.classes.ValidationMethods;
import com.revature.classes.menu.Menu;

public class GuestMenu extends Menu {
	private Scanner in;

	public GuestMenu(Scanner in) {
		super();
		this.in = in;
	}
	
	//displays guest menu
	@Override
	public boolean display(Menu menus, boolean terminate, Logger log) {
		log.info("In the guest menu");
		System.out.println("=======================================");
		System.out.println("		Guest");
		System.out.println("=======================================");
		System.out.println("	1. Main");
		System.out.println("	2. Exit");
		System.out.println("=======================================");
		
		terminate = menuSelection(menus, terminate, log);
		
		return terminate;
	}
	
	//user can either return to the main menu or exit
	@Override
	public boolean menuSelection(Menu menus, boolean terminate, Logger log) {
		switch(ValidationMethods.menuValidation(in)) {
			case 1:
				menus = new MainMenu(new Scanner(System.in));
				terminate = menus.display(menus, terminate, log);
				break;
			case 2:
				terminate = true;
				break;
		}
		return terminate;
	}
}
