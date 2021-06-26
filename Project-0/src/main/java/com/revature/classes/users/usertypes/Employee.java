package com.revature.classes.users.usertypes;

import java.util.Scanner;

import org.apache.logging.log4j.Logger;

import com.revature.classes.database.QueryFormationControl;
import com.revature.classes.users.User;
import com.revature.interfaces.UserBehaviors;

public class Employee extends User implements UserBehaviors{

	public void displayTimeSheet() {
		
	}
	
	public void addTimeSheet(User user, Logger log) {
		QueryFormationControl.checkTimeSheet(user, log);
	}
	
	public void addTimeSheetEntries(User user, Scanner in, Logger log) {
		QueryFormationControl.insertTimeSheetEntries(user, in, log);
	}
	public void editTimeSheet() {
		
	}
	
}
