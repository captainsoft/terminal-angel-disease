/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.spark._sparkmuell.anim;

public enum PlayState {

	Finished,
	Yield,
	Action;
	
	public boolean isUpdateState() {
		return (this == Finished || this == Action);
	}
	
}
