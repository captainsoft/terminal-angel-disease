/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.spark._sparkmuell.anim;

/**
 *
 * @author mathias fringes
 */
public final class Frame implements Comparable<Frame> {
	
	//
	
	public final static Frame FIRST = new Frame(1);
	
	private final int no;
	
	//
	
	public Frame(int no) {
		super();
		if (no < 1) {
			throw new IllegalArgumentException("Must be equal or greater than 0!");
		}
		this.no = no;
	}

	//

	public int no() {
		return no;
	}
	
	//

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		return prime * result + no;		
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (getClass() != object.getClass()) {
			return false;
		}
		Frame f = (Frame)object;
		if (this.no != f.no) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {	
		return "" + this.no;
	}

	// 
	
	@Override
	public int compareTo(Frame o) {	
		return this.no - o.no;
	}	

}
