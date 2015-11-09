/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.cuts;


/**
 * Position within a animation (Frame Index).
 *
 * @author mathias fringes
 */
public final class FrameMx implements Comparable<FrameMx> {
	
	// fields
	
	public static final FrameMx Start = new FrameMx(1f);
	
	private static final float DESC = 1000f;
	
	private final long value;	
	
	// constructors
		
	public FrameMx(float value) {			
		this((long)(value * DESC));
	}
	
	public FrameMx(FrameMx frame) {		
		this(frame.value);
	}
	
	private FrameMx(long value) {
		super();		
		this.value = value;		
	}	
	
	// accessors
		
	public long value() {
		return value;
    }
	
	public float asFloat() {
		return (value / DESC);		
	}
	
	// methods	
	
	public FrameMx add(float frames) {
		return new FrameMx((int)(value + frames * DESC));
	}
	
	public FrameMx add(FrameMx frame) {
		return new FrameMx(value + frame.value);
	}	
		
	public FrameMx subtr(FrameMx frame) {
		return new FrameMx(value - frame.value);		
	}		
	
	public boolean isEqualTo(FrameMx frame) {	
		return (compareTo(frame) == 0);
	}	
	
	public boolean isGreaterThanOrEqualTo(FrameMx frame) {	
		return compareTo(frame) <= 0;
	}
	
	public boolean isLessThanOrEqualTo(FrameMx frame) {	
		return compareTo(frame) >= 0;
	}
	
	public boolean isLessThan(FrameMx frame) {
		return compareTo(frame) > 0;
	}
		
	public FrameMx copy() {
		return new FrameMx(this);		
	}	
	
	public boolean equals(FrameMx f) {
		return ((f != null) && (f.value == value));				
	}
	
	// Comparable	
	
	@Override
	public int compareTo(FrameMx o) {
		if (o == null) {
			return -1;
		}		
		if (this.value == o.value) {
			return 0;
		}
		if (this.value < o.value) {
			return 1;
		}
		return -1;
	}
		
	
	// overridden
	
	@Override
	public String toString() {
		return "FrameMx [value=" + asFloat() + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (this.value ^ (this.value >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		FrameMx other = (FrameMx) obj;
		return equals(other);
	}
	
}