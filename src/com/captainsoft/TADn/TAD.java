/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn;
 
import java.io.IOException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.captainsoft.TADn.engine.GameEngine;
import com.captainsoft.TADn.menu.MenuController;
import com.captainsoft.spark.utils.Log;
import com.captainsoft.spark.utils.SysUtils;

/**
 * The "Terminal Angel Disease" main class.
 * 
 * @author mathias fringes
 */
public final class TAD {
	
	// constants
	
	public final static String Version = "0.1j";
	
	// constructors
	
	/**
	 * Default constructor.
	 */
	private TAD() {
		super();					
	}
	
	// methods
	
	private void init() throws IOException {
		//
		// set up java ui
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {		  
		}
		try {
			AwtEventDispatcherExceptionHandler.registerToAwtThread();
		} catch (Exception e) {
			Log.error("Cannot register AWT uncaught exception handler", e);			
			e.printStackTrace();
		}
		//
		// set up TAD init routine.
		TadRepo.inst().setUp();
	}
	
	private void play() {				
		final GameEngine ge = TadRepo.inst().GameEngine();
		SwingUtilities.invokeLater(new Runnable() {			
			@Override
			public void run() {
				try {
					final MenuController mc = new MenuController(ge);
					mc.show();
				} catch (Exception e) {
					TadExceptionHandler.errorMessageAndExit("A fatal error during the game occurred.", e);
				}
			}
		});				
	}	
	
	// static methods
	
	/**
	 * Main method. Starts the TAD. No arguments are used.
	 * 
	 * @param args arguments are not used.
	 */
	public static void main(String[] args) {		
		try {	
			Log.info("Properties:");
			for(String p : SysUtils.getParameters()) {
				Log.info(p);
			}		
			//
			// TODO DEBUG force german language for now
			// TadLang.toGerman();
			
			TAD tad = new TAD();
			tad.init();
			tad.play(); // TODO maybe give in game number for quick load
		} catch (Exception e) {
			TadExceptionHandler.errorMessageAndExit("A fatal error during initialization occurred.", e);
		}		
	}

}