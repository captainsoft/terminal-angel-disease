/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.collect;

import java.util.HashSet;

/**
 * Convenience Set.
 *
 * @author mathias fringes
 */
public final class Cset<T> extends HashSet<T> {
	
	private static final long serialVersionUID = -7432354752716722385L;

	public void add(T ... data) {
		for (T d : data) {
			add(d);
		}
	}
	
}