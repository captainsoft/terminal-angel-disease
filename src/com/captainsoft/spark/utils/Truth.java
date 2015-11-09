/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.utils;

import java.util.Collection;
import java.util.Map;

/**
 * Convenience static methods with object truth predicates.
 *
 * @author mathias fringes
 */
public final class Truth {

    // constructors

	private Truth() {
		super();
	}

    // public

    public static boolean isEqual(Object o, Object value) {
        if (o == null && value == null) {
            return true;
        }
        if (o == null) {
            return false;
        }
        return o.equals(value);
    }
	
	public static boolean is(Object o) {
		return (o != null);
	}
	
	public static boolean not(Object o) {
		return (!Truth.is(o));
	}
	
	public static boolean is(Object ... os) {
		for(Object o : os) {
			if (o == null) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean is(String s) {
		return ((s != null) && (s.length() > 0));
	}
	
	public static boolean isLenient(String s) {
		return (is(s) && (s.trim().length() > 0));
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean is(Collection c) {
		return ((c != null) && (c.size() > 0));
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean is(Map m) {
		return ((m != null) && (m.size() > 0));
	}

}
