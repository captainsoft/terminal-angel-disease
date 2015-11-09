/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.i18n;

import java.util.Map;

/**
 * A translator decorator that will add a prefix-string for each key.
 *
 * @author mathias fringes
 */
public final class PrefixDecorator implements Translator {
	
	// fields
	
	private final Translator decorated;
	private String prefix;
	
	// constructors
	
	public PrefixDecorator(Translator decorated, String prefix) {
		this.decorated = decorated;
		this.prefix = prefix;
	}
	
	// private
	
	private String dkey(String key) {
		return (prefix + key);
	}
	
	// Translator

	public boolean contains(String key) {
		return decorated.contains(dkey(key));
	}

	public String word(String key) {
		return decorated.word(dkey(key));
	}

	public String word(String key, Object ... data) {
		return decorated.word(dkey(key), data);
	}

	public String word(String key, Map<String, String> data) {
		return decorated.word(dkey(key), data);
	}

	public String word(TrKey key) {
		throw new UnsupportedOperationException("This is now to complicated to implement!");
	}

}
