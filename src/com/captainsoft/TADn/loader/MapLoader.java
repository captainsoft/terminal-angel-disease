/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.loader;

import com.captainsoft.TADn.party.*;

/**
 * Responsible for loading and saving single {@link LevelMap}s.
 *
 * @author mathias fringes
 */
public interface MapLoader {

	public LevelMap load(int number);

	public void save(LevelMap map);

}