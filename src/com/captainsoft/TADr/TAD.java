/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr;
 
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.captainsoft.TADr.engine.GameEngine;
import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.TADr.menu.MenuController;
import com.captainsoft.TADr.model.Game;
import com.captainsoft.spark.ui_swing.Messenger;
import com.captainsoft.spark.utils.Log;

/**
 * The "Terminal Angel Disease" game main class.
 * 
 * @author mathias fringes
 */
public final class TAD {

	// constants
	
	public final static TadAppType Type = TadAppType.Production;
	public final static String Version = "1.0";
	public final static String Copyright = "2010 - 2015";

	// constructors
	
	/**
	 * Default constructor.
	 */
	private TAD() {
		super();					
	}
	
	// methods

    /**
     * Initialize the TAD. Set up TAD repository.
     *
     * @throws IOException
     */
	private void init() throws IOException {
        //
        // language and files folder
        Log.force("file folder is set to: " + TadLang.folder());
		//
		// set up default ui
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {		  
			Log.error("Cannot set look and feel.", e);
		}
        //
		try {
			AwtEventDispatcherExceptionHandler.registerToAwtThread();
		} catch (Exception e) {
			Log.error("Cannot register AWT uncaught exception handler", e);			
		}
		//
		// set up TAD game services repository.
		TadRepo.inst().setUp();
	}

    /**
     * Start the TAD. Will immediately load the game, if specified.
     *
     * @param gameNr
     */
	private void start(final int gameNr) {

		final GameEngine ge = TadRepo.inst().GameEngine();

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				try {
					MenuController mc = new MenuController(ge);					
					if (gameNr == Game.NEW_GAME_NR) {
						mc.show();
					} else {
						mc.loadGame(gameNr);
					}
				} catch (Exception e) {
					TadExceptionHandler.errorMessageAndExit("A fatal error during the game occurred.", e);
				}
			}
		});				
	}
	
	private void checkExpired() {
		if (Type == TadAppType.Demo) {
			Calendar expireDate = Calendar.getInstance();
			expireDate.set(2100, Calendar.APRIL, 1);
			if (new Date().compareTo(expireDate.getTime()) > 0) {
				Messenger.msg("Oh, it seems that this demo version has expired! Thanks for playing, we hope you enjoyed it :) Check www.captainsoft.net for news, Goodbye! :)");
				System.exit(0);
			}
		}
	}
	
	// main
		
	/**
	 * Main method. Starts the TAD game. Command line arguments are respected (see TadArgs docu).
	 * 
	 * @param args arguments are not used.
	 */
	public static void main(String[] args) {		
		try {										
			//
			TadArgs tadArgs = new TadArgs();
			tadArgs.process(args);
			//
			TAD tad = new TAD();
			tad.checkExpired();
			tad.init();
			tad.start(tadArgs.gameNr);
			//
		} catch (Exception e) {
			TadExceptionHandler.errorMessageAndExit("A fatal error during initialization occurred.", e);		
		}		
	}
	
}