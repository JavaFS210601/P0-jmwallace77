package com.revature.classes.users.usertypes;

import java.util.Scanner;

import com.revature.classes.database.QueryFormationControl;
import com.revature.classes.users.User;
import com.revature.interfaces.UserBehaviors;


public class Manager extends User implements UserBehaviors{
	
	
	public void addEmployee(Scanner in) {
		QueryFormationControl.insertUser(in);
	}
	
	public void removeEmployee(Scanner in) {
		QueryFormationControl.removeUser(in);
	}

}
