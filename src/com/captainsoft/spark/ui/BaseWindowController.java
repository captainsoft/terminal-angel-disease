/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.ui;

/**
 * Default base implementation for the WindowController interface.
 *
 * @author mathias fringes
 */
public abstract class BaseWindowController implements WindowController {

    // constructors
	
	protected BaseWindowController() {
		super();
	}

    // WindowController
	
	public boolean isLenientModal() {
		return false;
	}
	
	public void onShow() {
	}

}