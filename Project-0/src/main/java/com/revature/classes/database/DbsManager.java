package com.revature.classes.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbsManager {

	static public Connection getConnection() throws SQLException{
		try {
			Class.forName("org.postgresql.Driver");
		}
		catch(ClassNotFoundException e) {
			System.out.println("Class was not found");
			e.printStackTrace();
		}
		String url = System.getenv("url");
		return DriverManager.getConnection(url);
	}
	
}
