package com.revature.classes.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.postgresql.core.Query;

import com.revature.classes.ValidationMethods;
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
	
	static public String getName(User user) {
		try(Connection conn = DbsManager.getConnection()){
			
			ResultSet result = null;
			String sql = "SELECT (firstName || ' ' || lastName) AS \"name\" FROM \"p0\".users WHERE userID = ?;";
			
			PreparedStatement pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, user.getUserID());
			result = pStatement.executeQuery();
			result.next();
			user.setName(result.getString("name"));
			
			return user.getName();
			
		} catch (SQLException e) {
			System.out.println("Name not found");
			//e.printStackTrace();
			return " ";
		}
	}
	
	//add user to the database
	static public void insertUser(Scanner in) {
		try(Connection conn = DbsManager.getConnection()){
			//get user info from manager input
			System.out.println("Fill in the data fields: \n");
			System.out.println("First Name: ");
			String fName = in.nextLine();
			System.out.println("Last Name: ");
			String lName = in.nextLine();
			System.out.println("Employment Title: (manager or employee)");
			String title = ValidationMethods.checkTitle(in);
			System.out.println("Wage: ");
			double wage = ValidationMethods.wageCheck(in);
			System.out.println("Temp Username: ");
			String username = in.nextLine();
			System.out.println("Temp Password: ");	
			String password = in.nextLine();
			
			String sql = "INSERT INTO \"p0\".userAuth(userName, userPass)"
					+ "VALUES(?, ?);";
			
			PreparedStatement pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, username);
			pStatement.setString(2, password);
			pStatement.executeUpdate();

			sql = "INSERT INTO \"p0\".users(userType, firstName, lastName, currentWagePerHour)"
					+ "VALUES(?, ?, ?, ?);";
			
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, title);
			pStatement.setString(2, fName);
			pStatement.setString(3, lName);
			pStatement.setDouble(4, wage);
			pStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Employee not added");
			//e.printStackTrace();
		}
	}
	
	static public void removeUser(Scanner in) {
		try(Connection conn = DbsManager.getConnection()){
			//delete user info which the manager input selected
			ResultSet result = null;
			
			String sql = "SELECT CONCAT(firstName, ' ', lastName) AS \"name\" FROM \"p0\".users "
					+ "WHERE userType = 'employee';";
			
			PreparedStatement pStatement = conn.prepareStatement(sql);
			result = pStatement.executeQuery();
			
			while(result.next()) {
				System.out.println(result.getString("name"));
			}
			
			System.out.println("Which employee is to be removed: \n");
			System.out.println("First Name: ");
			String fName = in.nextLine();
			System.out.println("Last Name: ");
			String lName = in.nextLine();
			
			sql = "SELECT userID FROM \"p0\".users "
					+ "WHERE firstName = ? AND lastName = ?;";
			
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, fName);
			pStatement.setString(2, lName);
			result = pStatement.executeQuery();
			
			sql = "DELETE FROM \"p0\".userAuth "
					+ "WHERE userID = ?;";
			
			pStatement = conn.prepareStatement(sql);
			result.next();
			pStatement.setInt(1, result.getInt("userID"));
			pStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Employee not removed");
			//e.printStackTrace();
		}
	}
}
