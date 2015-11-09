/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.ui.mouse;

import com.captainsoft.spark.ui.box.UiBox;

/**
 * Mouse move listener for boxes.
 *
 * @author mathias fringes
 */
public interface BoxMouseMoveListener {
	
	public void mouseEntered(UiBox box);
	
	public void mouseExited(UiBox box);
	
	public void mouseMoved(UiBox box, int x, int y);

}