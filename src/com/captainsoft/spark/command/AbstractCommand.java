/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.command;

/**
 * Base implementation for a command.
 *
 * @author mathias
 */
public abstract class AbstractCommand implements Command {

    // fields

	private final String name;

    // constructors

	public AbstractCommand() {
		this("");
	}
	
	public AbstractCommand(String name) {
		super();
		this.name = name;
	}

    // overridden
	
	@Override
	public String toString() {
		return "(Command) '" + name + "'";
	}
	
}