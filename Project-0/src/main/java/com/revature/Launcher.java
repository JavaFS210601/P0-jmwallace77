package com.revature;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.classes.database.DbsManager;
import com.revature.classes.menu.Menu;
import com.revature.classes.menu.submenus.MainMenu;

public class Launcher {
	private static final Logger log = LogManager.getLogger(Launcher.class);
	//used for switching menus based on user input
	static Menu menus; 
	
	//used for terminating program
	static boolean terminate = false;
	
	public static void main(String[] args) {
		
		//test for database connection
		try(Connection conn = DbsManager.getConnection()){
			log.info("Connection Successful");
		} catch (SQLException e) {
			log.debug("Connection Failed", e);
			System.out.println("Connection Failed");
			System.exit(0);
		}
		
		//programs main loop
		//terminated upon user request
		while(terminate == false) {
			menus = new MainMenu(new Scanner(System.in));
			terminate = menus.display(menus, terminate, log);
		}	
	}
}
