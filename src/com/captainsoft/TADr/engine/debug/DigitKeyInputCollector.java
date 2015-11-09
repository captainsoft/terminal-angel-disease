/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.engine.debug;

import java.util.regex.Pattern;

import com.captainsoft.spark.utils.Utils;

/**
 * Records key strokes by the pattern [control-key][digits]. Returns the digits.
 * Used by debugger aka cheat codes. Example : "m03"
 * 
 * @author mathias fringes
 */
final class DigitKeyInputCollector {
	
	// fields

    private final String controlKey;
    private final int length;

    private boolean recording = false;
	private String recorded = "";
	
	// constructors
	
	public DigitKeyInputCollector(String controlKey, int length) {
		super();
		this.controlKey = controlKey;
        this.length = length;
	}
	
	// public

    public Integer collect(int next) {
        return collect((char)next);
    }

    public Integer collect(char next) {
        return collect(next + "");
    }
	
	public Integer collect(String next) {
		next = next.toLowerCase();
		if (next.equals(controlKey)) {
			recording = true;
			recorded = "";			
			return null;
		}
		
		if (!recording) {
			return null;
		}
		
		if (fits(next)){
			recorded += next;
			if (recorded.length() == length) {
				recording = false;				
				return Integer.parseInt(recorded);
			}
		} 
		else {
			recording = false;
			recorded = "";		
		}
		return null;
	}
	
	// private

	private boolean fits(String next) {
		return (recorded.length() < length && Pattern.matches("\\d", next));
	}

    // overridden

	@Override
	public String toString() {
		return Utils.stringer("DigitKeyInputCollector", recording, recorded);
	}

}