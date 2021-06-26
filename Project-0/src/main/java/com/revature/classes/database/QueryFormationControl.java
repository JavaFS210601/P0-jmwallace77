package com.revature.classes.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;



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
	
	static public String getNameWage(User user) {
		try(Connection conn = DbsManager.getConnection()){
			
			ResultSet result = null;
			String sql = "SELECT currentWagePerHour, (firstName || ' ' || lastName) AS \"name\" FROM \"p0\".users WHERE userID = ?;";
			
			PreparedStatement pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, user.getUserID());
			result = pStatement.executeQuery();
			result.next();
			user.setName(result.getString("name"));
			user.setWage(result.getDouble("currentWagePerHour"));
			
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
	
	static public void checkTimeSheet(User user) {
		try(Connection conn = DbsManager.getConnection()){
			ResultSet result = null;
			int year = ZonedDateTime.now(ZoneId.of("US/Central")).getYear();
			
			String sql = "SELECT periodYear FROM \"p0\".timeSheets "
					+ "WHERE userID = ?;";
			
			PreparedStatement pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, user.getUserID());
			result = pStatement.executeQuery();
			if(result.next() == true) {
				do {
					if(year == result.getInt("periodYear")) {
						break;
					}
					else {
						addTimeSheet(user, year);
					}
				}while(result.next());
			}
			else {
				addTimeSheet(user, year);
			}
			
		} catch (SQLException e) {
			System.out.println("Failed to check timesheet existence.");
		}
	}
	
	private static void addTimeSheet(User user, int year) {
		try(Connection conn = DbsManager.getConnection()){
			
			String sql = "INSERT INTO \"p0\".timeSheets(userID, periodYear)"
					+ "VALUES(?, ?);";
			
			PreparedStatement pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, user.getUserID());
			pStatement.setInt(2, year);
			pStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Failed to add time sheet.");
		}
	}

	public static void insertTimeSheetEntries(User user, Scanner in) {
		try(Connection conn = DbsManager.getConnection()){
			ResultSet rPayPeriod = null;
			ResultSet rTimeSheetID = null;
			
			String sql = "SELECT payPeriod FROM \"p0\".payPeriods "
					+ "WHERE months @> DATE ?;";
			
			PreparedStatement pStatement = conn.prepareStatement(sql);
			String date = ZonedDateTime.now(ZoneId.of("US/Central")).format(DateTimeFormatter.ISO_LOCAL_DATE);
			//Date sqlDate = Date.valueOf(date);
			pStatement.setString(1, date);
			pStatement = conn.prepareStatement(pStatement.toString());
			rPayPeriod = pStatement.executeQuery();
			
			sql = "SELECT timeSheetID FROM \"p0\".timeSheets "
					+ "WHERE userID = ? AND periodYear = ?;";
			
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, user.getUserID());
			pStatement.setInt(2, ZonedDateTime.now(ZoneId.of("US/Central")).getYear());
			rTimeSheetID = pStatement.executeQuery();
			
			sql = "INSERT INTO \"p0\".timePeriodEntries(userID, timeSheetID, payPeriod, wagePerHour) "
					+ "VALUES(?, ?, ?, ?)";
			
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, user.getUserID());
			rTimeSheetID.next();
			pStatement.setInt(2, rTimeSheetID.getInt("timeSheetID"));
			rPayPeriod.next();
			pStatement.setInt(3, rPayPeriod.getInt("payPeriod"));
			pStatement.setDouble(4, user.getWage());
			pStatement.executeUpdate();
			
			weeklyEntries(user, in, rTimeSheetID);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	private static void weeklyEntries(User user, Scanner in, ResultSet rTimeSheetID) {
		try(Connection conn = DbsManager.getConnection()){
			ResultSet rEntry = null;
		
			String sql = "SELECT Max(entryID) AS maxID FROM \"p0\".timePeriodEntries "
				+ "WHERE userID = ? AND timeSheetID = ?";
		
			PreparedStatement pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, user.getUserID());
			pStatement.setInt(2, rTimeSheetID.getInt("timeSheetID"));
			rEntry = pStatement.executeQuery();
			
			System.out.println("Enter hours worked: ");
			System.out.println("Sunday: ");
			double sunday = ValidationMethods.hoursValidation(in);
			System.out.println("Monday: ");
			double monday = ValidationMethods.hoursValidation(in);
			System.out.println("Tuesday: ");
			double tuesday = ValidationMethods.hoursValidation(in);
			System.out.println("Wednesday: ");
			double wednesday = ValidationMethods.hoursValidation(in);
			System.out.println("Thursday: ");
			double thursday = ValidationMethods.hoursValidation(in);
			System.out.println("Friday: ");
			double friday = ValidationMethods.hoursValidation(in);
			System.out.println("Saturday: ");
			double saturday = ValidationMethods.hoursValidation(in);
			
			sql = "INSERT INTO \"p0\".weeklyEntries(entryID, sunday, monday, tuesday, wednesday, thursday, friday, saturday) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			
			pStatement = conn.prepareStatement(sql);
			rEntry.next();
			pStatement.setInt(1, rEntry.getInt("maxID"));
			pStatement.setDouble(2, sunday);
			pStatement.setDouble(3, monday);
			pStatement.setDouble(4, tuesday);
			pStatement.setDouble(5, wednesday);
			pStatement.setDouble(6, thursday);
			pStatement.setDouble(7, friday);
			pStatement.setDouble(8, saturday);
			pStatement.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}
