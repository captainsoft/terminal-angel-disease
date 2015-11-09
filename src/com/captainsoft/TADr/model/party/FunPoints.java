/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.model.party;

import static com.captainsoft.spark.utils.Utils.stringer;

/** 
 * Fun points (=health) for one party member.
 *
 * @author mathias fringes
 */
public final class FunPoints {

	// static
	
	private static final int MAX_VALUE = 499;
	
	private int cur = 0;

	private int max = 0;
	
	// constructors
	
	public FunPoints() {
		super();
	}
	
	// accessors	
	
	public int cur() {
		return cur;	
	}
	
	public void cur(int value) {
		cur = value;	
	}
			
	public int max() {
		return max;
	}
	
	public void max(int value) {
		max = value;
	}	
	
	// public
	
	public void addCur(int value) {
		cur += value;
		if (cur > max) {
			cur = max;
		}
		if (cur < 0) {
			cur = 0;
		}
	}
	
	public void addMax(int value) {
		max += value;
		if (max > MAX_VALUE) {
			max = MAX_VALUE;
		}
		addCur(value);
	}
	
	public boolean isFull() {
		return cur >= max;
	}
	
	public boolean isSad() {
		return (cur <= 0);
	}
	
	// overridden
	
	@Override
	public String toString() {	
		return stringer("FunPoints", cur + "/" + max);
	}
	
}