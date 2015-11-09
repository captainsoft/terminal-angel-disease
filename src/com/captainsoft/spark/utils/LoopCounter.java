/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.utils;

/**
 * Counter that loops an int value from start to max, and then
 * repeats from start again.
 *
 * @author mathias fringes
 */
public final class LoopCounter {

	// fields
	
	private final int max;	
	private final int start;
	
	private int current;	
	private int overallCount;
	
	// constructors
	
	public LoopCounter(int start, int max) {		
		if (start > max) {
			throw new IllegalArgumentException("start must not bigger than end");
		}
		if (start < 0) {
			throw new IllegalArgumentException("start must not be below zero!");
		}
		this.start = start;
		this.max = max;
		this.current = start;
	}
	
	// public

    /**
     * Checks if we are at the beginning of the loop.
     *
     * @return
     */
	public boolean beginning() {
		return (current == start);
	}

    /**
     * Sets the current value. Must in between the loop range.
     *
     * @param current
     */
	public void current(int current) {
		ExcUtils.argIn("current", current, start, max);
		this.current = current;
	}

    /**
     * Gets the current loop value.
     *
     * @return
     */
	public int current() {
		return current;
	}

    /**
     * Counts to the next value in the loop (by incrementing by one)!
     */
	public void count() {
		if (finish()) {
			current = start;
		} else {
			current++;			
		}
		overallCount++;
	}

    /**
     * Checks if we are at the end of the loop.
     *
     * @return
     */
	public boolean finish() {
		return (current == max);
	}

    /**
     * Gets the times the loop has already been repeated.
     *
     * @return
     */
	public int loopCount() {
		return (overallCount / max);
	}

    /**
     * Counts to the next value in the loop (same as count()), but returns
     * the current value.
     *
     * @return
     */
	public int next() {	
		count();
		return current();
	}

    /**
     * Sets the current value by a random value inside the loop range.
     */
	public void randomize() {
		current(Utils.random(start, max + 1));
	}
	
	// overridden
	
	@Override
	public String toString() {
		return "[" + start + "->(" + current + ")->" + max + "]";
	}

}