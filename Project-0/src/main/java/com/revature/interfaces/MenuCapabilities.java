package com.revature.interfaces;

import com.revature.classes.menu.Menu;

public interface MenuCapabilities {
	public abstract boolean display(Menu menus, boolean terminate);
	public abstract boolean menuSelection(Menu menus, boolean terminate);
}
