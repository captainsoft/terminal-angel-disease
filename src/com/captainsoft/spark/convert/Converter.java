/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.convert;

/**
 * Converter utility classes for Java data types.
 *
 * @author mathias fringes
 */
public final class Converter {

    // constructors
	
	private Converter() {
		super();
	}
	
	// public
	
	public static boolean toBoolean(String string) {
		return Boolean.parseBoolean(string);
	}
	
	public static String fromBoolean(boolean value) {
		return Boolean.toString(value);
	}
	
	public static float toFloat(String string, float defaultValue) {
		if ((string == null) || (string.length() == 0)) {
			return defaultValue;
		}
		try {
			return Float.parseFloat(string);
		} catch(NumberFormatException e) {
			return defaultValue;
		}
	}
	
	public static String fromFloat(float value) {
		return Float.toString(value);
	}
	
	public static int toInt(String value) {
		return Integer.parseInt(value);
	}
	
	public static String fromInt(int value) {
		return Integer.toString(value);
	}
	
	public static boolean validate(String value, int min, int max) {
		if (value == null) {
			return false;
		}
		String v = value.trim();		
		return (v.length() >= min) && (v.length() <= max);		
	}
	
	public static boolean minLength(String value, int minLength) {
		return (value != null) && (value.trim().length() >= minLength);
	}
	
	public static boolean blackList(String value, String ... notAllowed) {
		if (value == null) {
			return false;
		}
		String v = value.trim();
        for (String s : notAllowed) {
            if (v.equalsIgnoreCase(s)) {
                return false;
            }
        }
		return true;				
	}
	
	public static String quetsch(String value, int max) {
		if (value == null) {
			return "";
		}
		String v = value.trim();		
		if (v.length() > max) {
			v = v.substring(0, max);
		}					
		return v;
	}

}