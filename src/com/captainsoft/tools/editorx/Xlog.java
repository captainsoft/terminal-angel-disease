/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.tools.editorx;

/**
 * 
 * @author mathias fringes
 */
public final class Xlog {

	private Xlog() {
		super();
	}
	
	public static void l (Object ... args) {
		String s = "";
		for (Object a : args) {
			s += a.toString() + " ";
		}
		System.out.println(s);
	}
}
