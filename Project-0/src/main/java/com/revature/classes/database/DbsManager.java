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
		String url = "jdbc:postgresql://javafs210601.cen4ygabfg2t.us-east-2.rds.amazonaws.com:5432/postgres?user=postgres&password=1JWall670581";
		return DriverManager.getConnection(url);
	}
	
}
