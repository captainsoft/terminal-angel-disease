/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.painting.replacer;

/**
 * 
 *
 * @author mathias fringes
 */
public class TileGroupImageProxy extends TileGroupImage {

	private TileGroupImage timage;
	
	public TileGroupImageProxy(TileGroupImage groupimage) {
		super(groupimage.image());
		this.timage = groupimage;
		this.image(this.timage.image());
		//
		// TODO sehr unschön !?
		if(groupimage.ground()) {
			asGround();
		}
		if (groupimage.overlay()) {
			asOverlay();
		}
		if (groupimage.secondOverlay()) {
			asSecondOverlay();
		}
	}
		
}