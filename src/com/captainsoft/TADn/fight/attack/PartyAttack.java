/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.fight.attack;

/**
 * An executed attack by the party to a monster
 *
 * @author mathias fringes
 */
public interface PartyAttack {
	
	/**
	 * Will execute the attack, and return the results.
	 * 
	 * @return an Attacks instance,
	 */
	public Attack attack();	

}
