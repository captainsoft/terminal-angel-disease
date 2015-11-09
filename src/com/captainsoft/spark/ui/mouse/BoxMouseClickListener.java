/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.ui.mouse;

import com.captainsoft.spark.control.MouseButton;
import com.captainsoft.spark.ui.box.UiBox;

/**
 * Mouse click listener for boxes.
 *
 * @author mathias fringes
 */
public interface BoxMouseClickListener {

	public void mouseClick(UiBox box, int x, int y, MouseButton button);
	
}
