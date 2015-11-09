/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark;

import java.util.ArrayList;
import java.util.List;

/**
 * A interpretable string that replaces placeholders with values.
 *
 * @author mathias fringes
 */
public final class CString {

	// fields

    public static final String PL = "$";

	private final List<String> parameters = new ArrayList<String>();
	private final String value;
	
	// constructors
	
	public CString(String value) {
		super();
		this.value = value;
	}
	
	public CString(String value, Object ... params) {
		this(value);
		for (Object s : params) {
			parameters.add(s.toString());
		}
	}

	// public
	
	public void add(int parameter) {	
		add(parameter + "");
	}
	
	public void add(String parameter) {
		parameters.add(parameter);
	}
	
	public void clear() {
		parameters.clear();
	}
	
	public String eval() {
		String t = value;
		int index = 1;
		for (String p : parameters) {
			t = t.replace(PL + index, p);
			index++;
		}		
		return t;
	}
	
	// overridden
	
	@Override
	public String toString() {
		return eval();
	}
	
}
