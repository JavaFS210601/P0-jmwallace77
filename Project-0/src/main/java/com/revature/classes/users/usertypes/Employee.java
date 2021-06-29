package com.revature.classes.users.usertypes;

import java.util.Scanner;

import org.apache.logging.log4j.Logger;

import com.revature.classes.database.QueryFormationControl;
import com.revature.classes.users.User;
import com.revature.interfaces.UserBehaviors;

public class Employee extends User implements UserBehaviors{

	public void displayTimeSheet() {
		
	}
	
	//creates a time sheet for the user to add entries if one does not exist for that year
	public void addTimeSheet(User user, Logger log) {
		QueryFormationControl.checkTimeSheet(user, log);
	}
	
	//add time entries to a users time sheet 
	public void addTimeSheetEntries(User user, Scanner in, Logger log) {
		QueryFormationControl.insertTimeSheetEntries(user, in, log);
	}
	
	public void editTimeSheet() {
		
	}
	
	//changes a users password
	@Override
	public void changeUsernamePassword(User user, Scanner in, Logger log) {
		QueryFormationControl.updatingUserPass(user, in, log);
	}
	
}
