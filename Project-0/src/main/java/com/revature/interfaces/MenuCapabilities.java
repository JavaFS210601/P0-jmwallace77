package com.revature.interfaces;

import org.apache.logging.log4j.Logger;

import com.revature.classes.menu.Menu;

public interface MenuCapabilities {
	//methods use to display a menu and handle user selection
	public abstract boolean display(Menu menus, boolean terminate, Logger log);
	public abstract boolean menuSelection(Menu menus, boolean terminate, Logger log);
}
