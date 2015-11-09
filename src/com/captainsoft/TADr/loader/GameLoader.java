/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.loader;

import com.captainsoft.TADr.model.Game;

/**
 * Loads and saves Games.
 * 
 * @author mathias fringes
 */
public interface GameLoader {

	public Game load(int index);

	public Game loadNewGame();

	public void save(Game game);

}