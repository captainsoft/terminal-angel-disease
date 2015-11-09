/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr;

import javax.swing.JOptionPane;

import com.captainsoft.TADr.menu.MenuController;
import com.captainsoft.spark.utils.Log;

/**
 * Default exception handler for the TAD.
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
		message = message + "\r\n\r\nError:\r\n" + e.getClass().getName() + "\r\n" + e.getMessage();

        if (TAD.Type != TadAppType.Production) {
            message += "\r\n----------------------";
            StackTraceElement[] st = e.getStackTrace();
            for (int i = 0; i < st.length; i++) {
                message += "\r\n" + e.getStackTrace()[i];
                if (i == 10) {
                    message += "\r\n .......";
                    break;
                }
            }
        }
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
		try {			
			t.printStackTrace();
			if (message != null) {
				Log.force(message);
			}
			if (t != null) {				
				Log.force(t);				
			}			
		} catch(Throwable e) {
			System.out.println("Error while safe logging.");
			System.out.println(e);
		}
	}

}
