package com.revature.classes.users.usertypes;

import java.util.Scanner;

import org.apache.logging.log4j.Logger;

import com.revature.classes.database.QueryFormationControl;
import com.revature.classes.users.User;
import com.revature.interfaces.UserBehaviors;


public class Manager extends User implements UserBehaviors{
	
	
	public void addEmployee(Scanner in, Logger log) {
		QueryFormationControl.insertUser(in, log);
	}
	
	public void removeEmployee(Scanner in, Logger log) {
		QueryFormationControl.removeUser(in, log);
	}

}
