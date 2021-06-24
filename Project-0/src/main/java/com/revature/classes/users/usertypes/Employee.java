package com.revature.classes.users.usertypes;

import com.revature.classes.users.User;
import com.revature.interfaces.UserBehaviors;

public class Employee extends User implements UserBehaviors{
	private String name;

	public void displayTimeSheet() {
		
	}
	
	public void editTimeSheet() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	
}
