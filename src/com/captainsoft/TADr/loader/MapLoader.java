/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.loader;

import com.captainsoft.TADr.model.map.LevelMap;

/**
 * Responsible for loading and saving single LevelMap.
 *
 * @author mathias fringes
 */
public interface MapLoader {

	public LevelMap load(int number);

	public void save(LevelMap map);

}