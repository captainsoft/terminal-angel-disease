/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn;

/**
 * 
 *
 * @author mathias fringes
 */
public class AwtEventDispatcherExceptionHandler {
	
	// TODO BUG does this class work??
	
	public static void registerToAwtThread() {		 
		System.setProperty("sun.awt.exception.handler", AwtEventDispatcherExceptionHandler.class.getName());		
	}
	
	public AwtEventDispatcherExceptionHandler() {
		super();
	}
	
	public void handle(Throwable throwable) {
		try {
			TadExceptionHandler.errorMessageAndExit("A fatal error in the AWT occurred!", throwable);			
		} catch (Throwable e) {
			System.out.println(e);
			e.printStackTrace();
			System.exit(1);
		}
	}

}
