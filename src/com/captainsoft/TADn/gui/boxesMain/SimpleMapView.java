/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.gui.boxesMain;

import com.captainsoft.TADn.painting.SimpleMapDrawer;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.drawing.Surface;

/**
 *
 * @author mathias fringes
 */
public final class SimpleMapView extends UiBoxContainer {

	// fields
	
	private final SimpleMapDrawer mapDrawer;
	
	// constructors
	
	public SimpleMapView(SimpleMapDrawer mapDrawer) {
		super(0, 0, mapDrawer.PX_WIDTH, mapDrawer.PX_HEIGHT);
		this.mapDrawer = mapDrawer;
	}		
		
	// overridden methods
	
	@Override
	protected void draw(Surface s) {
		s.blit(mapDrawer.surface(), 0, 0);
	}
	
}
