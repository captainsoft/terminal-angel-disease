/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.menu;

/**
 * The interface for the menu. 
 *
 * @author mathias fringes
 */
public interface MenuViewer {
	
	public void close();
	
	public void display();
	
	public void showLoadView();

	public void showMainMenuView();
	
	public void showSaveView();	

	public void showSettingsView();

}