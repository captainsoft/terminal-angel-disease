/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.sound;

/**
 * Plays sounds.
 *
 * @author mathias fringes
 */
public interface SndPlayer {

	public void enabled(boolean enabled);

	public void playSound(String type, int id);	

}