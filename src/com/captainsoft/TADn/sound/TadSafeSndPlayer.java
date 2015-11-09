/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.sound;

import com.captainsoft.spark.utils.Log;

/**
 * Decorator for the sound player. Logs exceptions if the
 * sound cannot be played.
 *
 * @author mathias fringes
 */
public final class TadSafeSndPlayer implements SndPlayer {
	
	// fields
	
	private SndPlayer sndPlayer;
	
	// constructors
	
	public TadSafeSndPlayer(SndPlayer sndPlayer) {
		super();
		this.sndPlayer = sndPlayer;
	}

	// SndPlayer
	
	@Override
	public void playSound(String type, int id) {
		try {
			sndPlayer.playSound(type, id);
		} catch(Exception e) {
			Log.error("Cannot play sound (" + type + "," + id + ")", e);			
		}		
	}

	@Override
	public void enabled(boolean enabled) {
		sndPlayer.enabled(enabled);
	}

}