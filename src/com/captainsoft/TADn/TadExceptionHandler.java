/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn;

import javax.swing.JOptionPane;

import com.captainsoft.TADn.menu.MenuController;
import com.captainsoft.spark.utils.Log;

/**
 * 
 *
 * @author mathias fringes
 */
public final class TadExceptionHandler {
	
	// fields
	
	private static final String dlgTitle = "The Terminal Angel Disease. (" + TAD.Version + ")";
	
	// constructor
	
	private TadExceptionHandler() {
		super();
	}
	
	// public		
	
	public static void errorMessage(String message, Throwable e) {
		log(message, e);	
		message = message + "\r\n\r\nError:\r\n" + e.getMessage(); 
		JOptionPane.showMessageDialog(null, message, dlgTitle, JOptionPane.ERROR_MESSAGE, null);
	}
	
	public static void errorMessageAndExit(String message, Throwable e) {
		errorMessage(message, e);
		System.exit(1);
	}
	
	public static void errorMessageAndMenu(String message, Throwable e) {
		errorMessage(message, e);
		MenuController.showFromError();
	}
	
	// private	
	
	private static void log(String message, Throwable t) {
		safeLog(message, t);
	}
	
	private static void safeLog(String message, Throwable t) {		
		try {			
			t.printStackTrace();
			if (message != null) {
				Log.force(message);
			}
			if (t != null) {				
				Log.force(t);				
			}			
		} catch(Exception e) {
			System.out.println("Error while logging.");
			System.out.println(e);
		}
	}

}
