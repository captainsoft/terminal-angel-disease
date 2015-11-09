/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.utils.sys;

/**
 * Descriptor for a command line argument.
 * Consists of parameter key, and a help text.
 *
 * @author mathias fringes
 */
final class Arg {

    // fields
	
	public final String parameter;	
	
	public final String helpText;

    // constructors
	
	public Arg(String parameter, String helpText) {
		super();
		this.parameter = parameter;		
		this.helpText = helpText;
	}

}
