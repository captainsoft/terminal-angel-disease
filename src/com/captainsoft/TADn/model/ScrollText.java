/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.model;

/**
 * The scroll text for user output.
 *
 * @author mathias fringes
 */
public final class ScrollText {
	
	// fields

	private static final int LEN = 4;
	
	private int[] types = new int[LEN];
	private String[] texts = new String[LEN];

	// constructors

	public ScrollText() {
		super();
		for (int i = 0; i < texts.length; i++) {
			texts[i] = new String();
			types[i] = 0;
		}
	}

	// public methods

	public void clear() {
		for (int i = 0; i < texts.length; i++) {
			texts[i] = "";
			types[i] = 0;
		}
	}	

	public int length() {
		return texts.length;
	}
	
	public void scroll(String t) {
		scroll(0, t);
	}
	
	public void scroll(int ty, String t) {
		if (t.startsWith(".")) {
			ty = 0;
			t = t.substring(1);
		}
		for (int i = 0; i < texts.length - 1; i++) {
			texts[i] = texts[i + 1];
			types[i] = types[i + 1];
		}
		texts[texts.length - 1] = t;
		types[types.length - 1] = ty;
	}
	
	public String text(int index) {
		return texts[index];
	}
	
	public int type(int index) {
		return types[index];
	}	

}