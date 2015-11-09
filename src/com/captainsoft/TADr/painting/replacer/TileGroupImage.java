/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.painting.replacer;

import com.captainsoft.spark.ui.drawing.Surface;
import com.captainsoft.spark.utils.Utils;

/** 
 * One tile image of an tile group.
 * 
 * @author mathias fringes
 */
public class TileGroupImage {
	
	// fields
		
	private boolean ground;
	private boolean overlay;
	private boolean secondOverlay;
	private Surface image;
	
	public String name;
		
	// constructors
	
	public TileGroupImage(Surface image) {
		super();		
		this.image = image;
		//
		asGround();		
	}	
	
	// accessors
		
	public Surface image() {
		return image;
	}
	
	public void image(Surface image) {
		this.image = image;
	}			
	
	public boolean ground() {
		return ground;
	}
	
	public void asGround() {
		toDefault();
		ground = true;
	}
	
	public boolean secondOverlay() {
		return secondOverlay;
	}
	
	public void asSecondOverlay() {
		toDefault();
		secondOverlay = true;
	}
	
	public boolean overlay() {
		return overlay;
	}

	public void asOverlay() {
		toDefault();
		overlay = true;
	}
	
	// private 
	
	private void toDefault() {
		ground = false;
		overlay = false;
		secondOverlay = false;
	}
	
	// overridden
	
	public String toString() {
		return Utils.stringer("TileGroupImage", name);
	}

}