/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.tools.editorx.controller;

/**
 * 
 * @author mathias fringes
 */
public final class MainMenuBarController {
	
	private MapEditViewController mapEditController;

	public MainMenuBarController(MapEditViewController mapEditController) {
		super();
		this.mapEditController = mapEditController;
	}

	public void showMapPropertyWindow() {
		MapPropertyWindowController c = new MapPropertyWindowController(mapEditController);
		c.show();
	}
	
}