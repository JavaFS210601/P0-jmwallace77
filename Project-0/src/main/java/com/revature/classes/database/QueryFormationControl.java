package com.revature.classes.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.classes.users.User;




public class QueryFormationControl {
	static public boolean tryLogin(User user) {
		try(Connection conn = DbsManager.getConnection()){
			ResultSet result = null;
			String sql = "SELECT userID FROM \"p0\".userAuth "
					+ "WHERE userName = ? AND userPass = ?;";
			
			PreparedStatement pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, user.getUsername());
			pStatement.setString(2, user.getPassword());
			result = pStatement.executeQuery();
			result.next();
			user.setUserID(result.getInt("userID"));

			return true;
		} catch (SQLException e) {
			System.out.println("Invalid username or password.");
			return false;
		}
	}
	
	static public String getUserType(User user) {
		try(Connection conn = DbsManager.getConnection()){
			ResultSet result = null;
			String sql = "SELECT userType FROM \"p0\".users "
					+ "WHERE userID = ?;";
			
			PreparedStatement pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, user.getUserID());
			result = pStatement.executeQuery();
			result.next();
			user.setUserType(result.getString("userType"));
			
			return user.getUserType();
		} catch (SQLException e) {
			System.out.println("userID not found");
			return " ";
		}
		
	}
}
