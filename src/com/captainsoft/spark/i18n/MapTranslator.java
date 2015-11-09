/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.i18n;

import static com.captainsoft.spark.utils.ExcUtils.argNotNull;
import static com.captainsoft.spark.utils.Truth.is;

import java.util.HashMap;
import java.util.Map;

/** 
 * A translator, implemented with a map.
 *
 * @author mathias fringes
 */
public class MapTranslator implements Translator {
	
	/// constants
	
	public final static String DataKey = "$";
	
	// fields
	
	private final Map<String, String> keys;
	
	// constructors
	
	public MapTranslator() {
		super();
		keys = new HashMap<String, String>(100);		
	}
	
	// public
	
	public final void put(String key, String value) {
		if (keys.containsKey(key)) {
			throw new IllegalArgumentException("Key \"" + key + "\" already contained.");
		}
		argNotNull("value", value);
		keys.put(key, value);
	}
	
	public final void clear() {
		keys.clear();
	}

	// Translator
	
	public boolean contains(String key) {
		argNotNull("key", key);
		return keys.containsKey(key);
	}

	public String word(String key) {
		String value = keys.get(key);
		if (value == null) {
			throw new IllegalArgumentException("Key \"" + key + "\" not found.");
		}
		return value;
	}

	public String word(String key, Object ... data) {
		String word = word(key);
		for(int i = 0; i < data.length; i++) {
			if (data[i] != null) {
				word = word.replace("$" + (i+1), data[i].toString());
			}
		}
		return word;		
	}

	public String word(String key, Map<String, String> data) {
		String value = word(key);
		if (is(data)) {
			for (Map.Entry<String, String> d : data.entrySet()) {
			  	value = value.replace(d.getKey(), d.getValue());
			}	
		}				
		return value;
	}

	public String word(TrKey key) {		
		return word(key.key(), (Object[])(key.data()));
	}

}