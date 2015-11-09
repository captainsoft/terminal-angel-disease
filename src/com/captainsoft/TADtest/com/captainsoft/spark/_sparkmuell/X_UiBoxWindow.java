/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.spark._sparkmuell;

import com.captainsoft.spark.ui.*;
import com.captainsoft.spark.ui.box.UiBoxContainer;

/**
 * A window.
 *
 * @author mathias fringes
 */
public class X_UiBoxWindow extends UiBoxContainer {
	
	//
	
	private boolean modal = false;
	private boolean closed = false;

	// constructors
	
	public X_UiBoxWindow() {		
		this("", 0, 0);
	}

	public X_UiBoxWindow(int width, int height) {
		this("", width, height);		
	}

	public X_UiBoxWindow(String name, int width, int height) {
		super(name, 0, 0, width, height);		
	}
	
	//
	
	public final boolean modal() {
		return modal;
	}

	public final void modal(boolean modal) {
		this.modal = modal;
	}
	
	public final void close() {
		if (closed) {
			throw new RuntimeException("UiBoxWindow is already closed!");
		}
		visible(false);
		closed = true;
	}

}
