/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.collect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.captainsoft.spark.convert.Converter;

/**
 * Utilities for collections.
 * 
 * @author mathias fringes
 */
public final class CollectUtils {
	
	// constructors
	
	private CollectUtils() {
		super();
	}
	
	// public
	
	/**
	 * Creates a new list with a number of instances already filled.
	 * 
	 * @param max
	 * @param instance
	 * @return
	 */
	public static <T> List<T> createList(int max, T instance) {
		List<T> list = new ArrayList<T>();
		for (int i = 0; i < max; i++) {
			list.add(instance);
		}
		return list;
	}
	
	/**
	 * Read a list, splits the string content into ":", and stores
	 * the two parts in a new map.
	 * 
	 * @param list
	 * @return
	 */
	public static Map<String, String> iniFileList(List<String> list) {
		Map<String, String> cp = new HashMap<String, String>();
		for (String s : list) {
			if (s.contains(":")) {
				String[] k = s.split(":");
				String key = k[0];
				String value = k[1];
				cp.put(key.trim(), value.trim());
			} else {
				cp.put(s.trim(), "false");
			}
		}			
		return cp;
	}
	
	/**
	 * Converts the values of a map into boolean values. Returns a new
	 * map instance.
	 * 
	 * @param map
	 * @return
	 */
	public static Map<String, Boolean> toBooleanMap(Map<String, String> map) {
		Map<String, Boolean> cp = new HashMap<String, Boolean>();
		//
	    for(Map.Entry<String, String> e : map.entrySet()) {
	    	cp.put(e.getKey(), Converter.toBoolean(e.getValue()));
	    }
		//
		return cp;
	}	

}