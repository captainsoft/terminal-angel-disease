/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.ui.mouse;

import com.captainsoft.spark.control.MouseButton;
import com.captainsoft.spark.ui.box.UiBox;

/**
 * A click command indicates the "mouse clicking" on a box.
 *
 * @author mathias fringes
 */
public interface ClickCommand {

	public void click(UiBox box, int x, int y, MouseButton button);	
	
}