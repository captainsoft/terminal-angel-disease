/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.collect;

import java.util.ArrayList;
import java.util.HashMap;

/** 
 * A multi map. Note that the add method is NOT overridden, use putl method to add multiple items!
 * 
 * @author mathias fringes
 */
public final class MultiMap<K, V> extends HashMap<K, ArrayList<V>> {

	// static
	
	private static final long serialVersionUID = -3476201371090799660L;

	// constructors
	
	public MultiMap() {
		super();
	}
	
	// public
	
	public void putl(K key, V value) {
		ArrayList<V> list = get(key);
		//
		if (list == null) {
			list = new ArrayList<V>();
			put(key, list);
		}
		list.add(value);
	}
	
	public void trimAllToSize() {
		for (ArrayList<V> l : this.values()) {
			l.trimToSize();
		}
	}

}
