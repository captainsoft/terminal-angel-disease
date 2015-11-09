/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.model.puzzle;

import static com.captainsoft.spark.utils.ExcUtils.argIn;
import static com.captainsoft.spark.utils.Utils.stringer;

import com.captainsoft.TADr.model.map.Tile;

/**
 * Represents Tile change values.
 *
 * @author mathias fringes
 */
public final class TileValues {
	
	// fields
	
	/**
	 * Indicates the "don't care" value.
	 */
	public final static int NOV = 255;	
	
	private final int[] values = new int[4];
	
	// constructors 
	
	public TileValues() {
		this(NOV, NOV, NOV, NOV);
	}
	
	public TileValues(int value1, int value2, int value3, int value4) {
		super();		
		set(1, value1);
		set(2, value2);
		set(3, value3);
		set(4, value4);		
	}
	
	public TileValues(int number, int value) {
		this();		
		set(number, value);
	}
	
	public static TileValues fromTile(Tile tile) {
		return new TileValues(tile.value(1), tile.value(2), tile.value(3), tile.value(4));
	}
	
	// public
	
	public boolean active() {
		return (active(values[0]) || active(values[1]) || active(values[2]) || active(values[3])); 		 
	}
		
	public boolean applyTo(Tile tile) {		
		for(int i = 0; i < values.length; i++) {
			if (active(values[i])) {
				tile.set(i+1, values[i]);
			}
		}			
		return active();
	}	
	
	public void set(int number, int value) {
		argIn("number", number, 1, 4);
		values[number-1] = value;
	}
	
	// private
	
	/**
	 * Checks whether the value is active and valid in sense of the puzzle,
	 * and should be taken into account.
	 * 
	 * @param value
	 * @return
	 */
	private boolean active(int value) {
		return (value != NOV);
	}
	
	// overridden
	
	@Override	
	public String toString() {
		return stringer("TileValues", values[0], values[1], values[2], values[3]);		
	}

}
