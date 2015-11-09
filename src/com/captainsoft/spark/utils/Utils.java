/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.utils;

import java.awt.Color;
import java.util.*;

/**
 * Handy cute little class with cosy randomly assembled utility methods.
 *
 * @author Mathias Fringes
 */
public final class Utils {

    // constructors

	private Utils() {
		super();
	}

    // public

    public static boolean ranbool() {
        return random(2) == 0;
    }
	
	/**
	 * from zero to the exclusive given upper bound. for instance (to = 6 -> 0-5)
	 * 
	 * @param to the exclusive upper bound
	 * @return
	 */
    public static int random(int to) {
        return Utils.random(0, to);
    }

    /**
     * Example: from 1 - to 5 - results: 1,2,3,4
     * 
     * @param from
     * @param to
     * @return
     */
    public static int random(int from, int to) {
        return (int)(Math.random() * (to - from)) + from;
    }
    
    public static float random(float from, float to) {
    	return (float)(Math.random() * (to - from)) + from;
    }
    
    public static int rndPlus(int from, int plus) {
    	return random(from, from + plus);
    }
      
    public static Color rndColor() {
    	return new Color(random(210) + 20, random(210) + 20, random(210) + 20);
    }
    
    public static List<Integer> rndList(int max) {
    	List<Integer> l = new ArrayList<Integer>(max);
    	for (int i = 0; i < max; i++) {
    		l.add(i);
    	}
    	Collections.shuffle(l);
    	return l;
    }
    
    /**
     * Sleeps for the given count of milli seconds (1000ms = 1sec). InterruptedException
     * are all ignored.
     * 
     * @param millis the count of milli seconds
     */
    public static void sleepyMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {         
        }
    }
    
    public static void sleepySecs(int seconds) {
    	sleepyMillis(seconds * 1000);
    }
    
    public static Integer tryParse(String s, Integer defaultValue) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return defaultValue;
        }
    }
    
	public static Integer tryParse(String text, int maxLow, int maxHigh) {
		Integer i = tryParse(text, null);
		if (i == null || i < maxLow || i > maxHigh) {
			i = null;
		}
		return i;
	}
    
    public static int truce(int value, int min, int max) {
    	if (value < min) {
    		return min;
    	}
    	if (value > max) {
    		return max;
    	}
    	return value;
    }
    
    public static <T> T randomSelect(List<T> list) {
    	int index = randomSelectIndex(list);
    	return (index >= 0) ? list.get(index) : null;
    }
    
    public static <T> int randomSelectIndex(List<T> list) {
    	if (isEmpty(list)) {
    		return -1;
    	}
    	int index = random(list.size());    	
    	while((list.get(index)) == null) {    		
    		index++;
    		if (index == list.size()) {
    			index = 0;
    		}
    	}
    	return index;    	 
    }
    
    public static <T> boolean isEmpty(Collection<T> c) {    	
    	for(T t : c) {
    		if (t != null) {
    			return false;
    		}
    	}
    	return true;
    }

    /**
     * Checks if an int array contains a certain value.
     *
     * @param array
     * @param value
     * @return
     */
    public static boolean contains(int[] array, int value) {
        for (int element : array) {
            if (element == value) {
                return true;
            }
        }
    	return false;
    }
        
    public static String stringer(String name, Object ... values) {
    	StringBuilder sb = new StringBuilder();    	
    	sb.append(name);
    	if (values.length > 0) {
	    	sb.append(" (");
	    	for(Object value : values) {
	    		sb.append(value == null ? "null" : value.toString());
	    		sb.append(",");
	    	}
	    	sb.deleteCharAt(sb.length() - 1);
	    	sb.append(")");
    	}
    	return sb.toString();
    }
           
}