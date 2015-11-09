/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.collect.cache;

/**
 * A default cache key, consisting of a type and an id.
 * 
 * @author mathias fringes
 */
public final class CacheKey implements Comparable<CacheKey> {
	
	// fields
	
	public final int id;
	public final String combined;
	public final String type;

	// constructors
	
	public CacheKey(String type, int id) {
		super();
		this.type = type;
		this.id = id;
		this.combined = type + id;
	}
	
	// Comparable

	public int compareTo(CacheKey cacheKey) {
		return combined.compareTo(cacheKey.combined);
	}

	// overridden
	
	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		if (object == this) {
			return true;
		}
		if (object instanceof CacheKey) {
			CacheKey cacheKey = (CacheKey)object;
			return combined.equals(cacheKey.combined);
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return combined.hashCode();
	}

	@Override
	public String toString() {
		return combined;
	}
	
}
