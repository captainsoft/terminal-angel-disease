/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.party;

/**
 * Represents the level of danger for a tile.
 *
 * @author mathias fringes
 */
public enum TileDanger {
	
	/**
	 * No danger.
	 */
	None,
	
	/**
	 * Low, but present danger.
	 */
	Low,
	
	/**
	 * High danger. Most likely to be attacked on this tile.
	 */
	High;

}