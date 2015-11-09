/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.sound;

import com.captainsoft.TADn.*;

/**
 * TAD specific sound facade. Uses the TAD-Repo sound player.
 * 
 * @author mathias fringes
 */
public final class SndFacade {
	
	// static
	
	private final static String ifc = "sifc";

	// constructors
	
	private SndFacade() {
		super();
	}	
	
	// public
	
	public static void chestSound() {
		play(ifc, 15);		 		
	}
	
	public static void coinsSound() {		
		play(ifc, 16);
	}
	
	public static void bookSound() {
		play(ifc, 14);				
	}

	public static void switchSound() {
		play(ifc, 19);		 		
	}
		
	public static void teleprismaSound() {
		play(ifc, 20);		
	}

	public static void keySound() {
		play(ifc, 18);		
	}	
	
	// private 
		
	private static void play(String type, int id) {
		TadRepo.inst().SndPlayer().playSound(type, id);
	}

}
