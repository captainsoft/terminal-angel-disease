/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.loader;

import com.captainsoft.spark.ui.drawing.Surface;

/**
 * Loading image service.
 *
 * @author mathias fringes
 */
public interface ImageLoader {
	
	 public Surface load(String type, int index);
	 
	 public Surface load(String type, int index, int width, int height);
	
}