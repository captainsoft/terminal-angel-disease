/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.tools.editorx.model;

import com.captainsoft.TADr.model.Position;
import com.captainsoft.TADr.model.map.LevelMap;
import com.captainsoft.TADr.model.map.MapTileView;

/**
 * 
 * @author mathias fringes
 */
public final class EditorModel {
	
	public LevelMap currentMap;
	public Position selPosition = new Position();
	
	public final MapTileView tileView = new MapTileView(10, 10);

}
