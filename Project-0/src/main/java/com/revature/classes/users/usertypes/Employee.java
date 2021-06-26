package com.revature.classes.users.usertypes;

import java.util.Scanner;

import com.revature.classes.database.QueryFormationControl;
import com.revature.classes.users.User;
import com.revature.interfaces.UserBehaviors;

public class Employee extends User implements UserBehaviors{

	public void displayTimeSheet() {
		
	}
	
	public void addTimeSheet(User user) {
		QueryFormationControl.checkTimeSheet(user);
	}
	
	public void addTimeSheetEntries(User user, Scanner in) {
		QueryFormationControl.insertTimeSheetEntries(user, in);
	}
	public void editTimeSheet() {
		
	}
	
}
