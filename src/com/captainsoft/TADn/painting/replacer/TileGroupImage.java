/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.painting.replacer;

import com.captainsoft.spark.ui.drawing.ImageSurface;

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
	private ImageSurface image;	
		
	// constructors
	
	public TileGroupImage(ImageSurface image) {
		super();		
		this.image = image;
		//
		asGround();		
	}	
	
	// accessors
		
	public ImageSurface image() {
		return image;
	}
	
	public void image(ImageSurface image) {
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

}