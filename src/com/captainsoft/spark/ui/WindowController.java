/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.ui;

import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.mouse.BoxCommandList;

/** 
 * Interface for window controllers.
 * 
 * @author mathias fringes
 */
public interface WindowController {

	/**
	 * Create a new instance of the window.
	 * 
	 * @param mg
	 * @return
	 */
	public UiBoxContainer createWindow(BoxCommandList mg);
	
	/**
	 * Whether the window is modal.
	 * 
	 * @return
	 */
	public boolean isLenientModal();
	
	/**
	 * What to do on a show event. May be overridden.
	 */
	public void onShow();

}