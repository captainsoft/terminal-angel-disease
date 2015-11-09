/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.i18n;

import java.util.*;

/**
 * A simple "give me the key, i'll give you the word" translation component.
 *
 * @author mathias fringes
 */
public interface Translator {

	boolean contains(String key);
	
	String word(String key);	
	
	String word(String key, Object ... data);

	String word(String key, Map<String, String> data);
	
	String word(TrKey key);
	
}
