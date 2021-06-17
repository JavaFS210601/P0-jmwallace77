package com.revature;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.classes.menu.Menu;
import com.revature.classes.menu.submenus.MainMenu;

public class Launcher {
	private static final Logger log = LogManager.getLogger(Launcher.class);
	//used for switching menus based on user input
	static Menu menus = new MainMenu(new Scanner(System.in));
	
	//used for terminating program
	static boolean terminate = false;
	
	public static void main(String[] args) {
		
		//programs main loop
		//terminated upon user request
		while(terminate == false) {
			terminate = menus.display(menus, terminate);
		}	
	}
}
