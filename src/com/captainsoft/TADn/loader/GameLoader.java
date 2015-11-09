/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.loader;

import com.captainsoft.TADn.party.Game;

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