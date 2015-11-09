/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.spark._sparkmuell.anim.anims;

import com.captainsoft.spark._sparkmuell.anim.*;

/**
 *
 * @author mathias fringes
 */
public final class Pause implements Animation {

	//
	
	private int len = 0;
	private int played = 0; 
	
	//
	
	public Pause(int len) {
		super();
		if (len < 1) {
			throw new IllegalArgumentException("Len must be equal or greater than one (1)!");
		}
		this.len = len;
	}
	
	//
	
	private boolean finished() {
		return played >= len;
	}
	
	//
	
	@Override
	public PlayState play(int frame) {
		played++;
		return finished() ? PlayState.Finished : PlayState.Yield;
	}

	@Override
	public void reset() {
		played = 0;	
	}

}
