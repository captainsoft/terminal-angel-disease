/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.sound;

/**
 * Plays sounds.
 *
 * @author mathias fringes
 */
public interface SndPlayer {

	public void enabled(boolean enabled);

	public void playSound(String type, int id);	

}