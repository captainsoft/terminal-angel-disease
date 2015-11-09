/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.menu;

import java.util.ArrayList;
import java.util.List;

import com.captainsoft.TADr.TadExceptionHandler;
import com.captainsoft.TADr.engine.GameEngine;
import com.captainsoft.TADr.engine.Settings;
import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.TADr.loader.GameLoader;
import com.captainsoft.TADr.menu.swing.SwingMenuViewer;
import com.captainsoft.TADr.menu.swing.QuitGameFrame;
import com.captainsoft.TADr.model.Game;
import com.captainsoft.spark.i18n.Translator;
import com.captainsoft.spark.utils.Log;

/**
 * Controller for the game menu.
 * 
 * @author mathias fringes
 */
public final class MenuController {

	// fields

	public final Translator trans;
	public final Settings settings;
	
	private final Game currentGame;
	private final GameEngine gameEngine;
	private final GamesList gamesList;	
	private final GameLoader gameLoader;
	private MenuViewer menuViewer;

	// constructors

	public MenuController(GameEngine gameEngine) {
		super();
		this.gameEngine = gameEngine;
		this.currentGame = gameEngine.game();				
		this.gamesList = new GamesList();
		this.gameLoader = TadRepo.inst().GameLoader();
		this.settings = gameEngine.settings();
		this.trans = TadRepo.inst().Trans();		
	}
	
	public static void showFromError() {
		GameEngine ge = TadRepo.inst().GameEngine();
		ge.close();				
		MenuController mc = new MenuController(ge);		
		mc.show();
	}

	// public
	
	public void close() {
		if (menuViewer != null) {
			menuViewer.close();
		}
	}
	
	public void loadGame(int index) {
		loadGame(index, gamesList.load().get(index));
	}
	
	public void loadGame(int index, String name) {
		try {
			Game game;		
			game = gameLoader.load(index);
			game.name(name);
			updateSettingsWithGame(game);
			gameEngine.startGame(game);		
			close();	
		} catch(Exception e) {
			handleException("Error loading game.", e);			
		}
	}
	
	public List<String> loadGameList() {
		List<String> games = new ArrayList<String>();
		try {		
			games = gamesList.load();			
		} catch (Exception e) {
			handleException("Error loading game list.", e);			
		}
		return games;
	}

	public void saveGame(int index, String[] names) {
		try { 
			if (index != -1) {					
				Log.info("saving game " + index);
				currentGame.number(index);
				currentGame.name(names[index]);
				gameLoader.save(currentGame);
				gamesList.save(names);			
				close();
			}
		} catch(Exception e) {
			handleException("Error saving game.", e);
		}
	}

	public void saveSettings() {	
		try {
			updateGameWithSettings(currentGame);
			gameEngine.settingsHaveChanged();
			TadRepo.inst().SndPlayer().enabled(settings.playSounds);
			settings.save();			
		} catch (Exception e) {
			handleException("Error saving settings.", e);			
		}		
	}

	public void show() {		
		Log.info("Showing MenuDialog");
		this.menuViewer = new SwingMenuViewer(this, currentGame);
		this.menuViewer.display();
	}
	
	public void showLoadGameView() {
		menuViewer.showLoadView();
	}

	public void showMainMenuView() {
		menuViewer.showMainMenuView();
	}

	public void showSaveGameView() {
		menuViewer.showSaveView();
	}

	public void showSettingsView() {
		menuViewer.showSettingsView();
	}

    public void showAboutView() {
        menuViewer.showAboutView();
    }
	
	public void showQuitGameFrame() {	
		if (currentGame == null) {			
			quitGame();
		} else {					
			new QuitGameFrame(null, this);
		}
	}

	public void startNewGame() {
		try {
			close();
			Game game = gameLoader.loadNewGame();
			updateGameWithSettings(game);			
			gameEngine.startGame(game);					
		} catch(Exception e) {
			handleException("Error while starting new game.", e, true);			
		}
	}

	public void quitGame() {
		Log.info("Closing in menu controller");
		close();		
		System.exit(0);
	}
	
	// private
		
	private void handleException(String message, Throwable t) {
		handleException(message, t, (menuViewer == null) || (!menuViewer.isShowing()));
	}
	
	private void handleException(String message, Throwable t, boolean showMenu) {
		if (gameEngine != null) {
			gameEngine.close();
		}
		if (showMenu) {
			TadExceptionHandler.errorMessageAndMenu(message, t);
		} else {
			TadExceptionHandler.errorMessage(message, t);
		}
	}
	
	private void updateGameWithSettings(Game game) {
		if (game != null) {
			game.player(settings.nameOfPlayer());
			game.party().name(settings.nameOfParty());
		}
	}
	
	private void updateSettingsWithGame(Game game) {
		if (game != null) {			
			settings.nameOfPlayer(game.player());
			settings.nameOfParty(game.party().name());
		}
	}

}
