package com.revature.interfaces;

import java.util.Scanner;

import org.apache.logging.log4j.Logger;

import com.revature.classes.users.User;

public interface UserBehaviors {
	public void changeUsernamePassword(User user, Scanner in, Logger log);
}
