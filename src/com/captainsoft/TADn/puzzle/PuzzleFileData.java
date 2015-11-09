/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.puzzle;

import static com.captainsoft.spark.utils.Utils.stringer;

/**
 * Raw puzzle data loaded from the puzzle data file.
 *
 * @author mathias fringes
 */
public final class PuzzleFileData {
	
	// fields
	
	private int[] data = new int[10];
	
	// constructors
	
	public PuzzleFileData() {
		super();
	}
	
	// public methods
	
	public int id() {
		return data[0];
	}
		
	public int value(int index) {
		return data[index];
	}
	
	public void value(int index, int value) {
		data[index] = value;		
	}		
	
	// overridden methods
	
	@Override
	public String toString() {
		return stringer("PuzzleFileData", data);
	}
	                            	                            	                           
}