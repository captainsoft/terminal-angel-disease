/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.utils.sys;

import java.util.HashMap;
import java.util.Map;

import com.captainsoft.spark.utils.Utils;

/**
 * Can parse argument lines like example
 * -g1 -d78 -lg -v
 * 
 * Parameters must have a (-) followed by a character, and then the parameter.
 *  
 * @author mathias fringes
 */
class ArgLine {

    // fields
		
	final Map<String, String> arguments = new HashMap<String, String>();

    // constructors

	ArgLine() {
		super();
	}

    // public
	
	public final void parse(String[] args) {
		arguments.clear();
		for(String a : args) {
			if (a.length() > 1 && a.startsWith("-")) {
				String key = a.substring(1, 2);
				String value = a.length() > 2 ? a.substring(2) : "";
				arguments.put(key, value);
			}
		}
	}
	
	public final int count() {
		return arguments.size();
	}
	
	public final Integer intAt(String parameter, Integer defaultValue) {
		return Utils.tryParse(arguments.get(parameter), defaultValue);
	}
	
	public final String stringAt(String parameter) {
		return arguments.get(parameter);
	}
	
	public final boolean hasParameter(String parameter) {
		return arguments.containsKey(parameter);
	}

    // overridden
	
	@Override
	public String toString() {
		return Utils.stringer("Args", arguments);
	}

}