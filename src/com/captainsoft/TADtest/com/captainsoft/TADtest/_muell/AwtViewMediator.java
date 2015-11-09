/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADtest._muell;

import java.awt.*;

/**
 * 
 *
 * @author mathias fringes
 */
public class AwtViewMediator {

	private InterfaceUpdater interfaceUpdater;
	private MapViewUpdater mapViewUpdater;
	private WindowManager windowManager;
	private Canvas screen;

	public AwtViewMediator() {
		this.interfaceUpdater  = new VbInterfaceUpdater();	
		this.windowManager = new VbWindowManager();
	}	
	

	
	public InterfaceUpdater getInterfaceUpdater() {
		return interfaceUpdater;
	}

	
	public MapViewUpdater getMapViewUpdater() {
		return mapViewUpdater;
	}


	public WindowManager getWindowManager() {
		return windowManager;
	}

}
