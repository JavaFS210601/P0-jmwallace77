package com.revature.classes.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbsManager {

	static public Connection getConnection() throws SQLException{
		try {
			//returns a Class object of the org.postgresql.Driver
			Class.forName("org.postgresql.Driver");
		}
		catch(ClassNotFoundException e) {
			System.out.println("Class was not found");
			e.printStackTrace();
		}
		
		//returns a connection to a database of the given url stored in an environment variable
		return DriverManager.getConnection(System.getenv("url"));
	}
	
}
