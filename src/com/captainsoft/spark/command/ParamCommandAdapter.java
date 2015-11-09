/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.command;

/**
 * Adapter for the ParamCommand / Command connection.
 *
 * @author mathias fringes
 */
public final class ParamCommandAdapter<T> implements Command {

	// fields
	
	private final ParamCommand<T> paramCommand;
	
	private final T object;
	
	// constructors
	
	public ParamCommandAdapter(ParamCommand<T> paramCommand, T object) {
		super();
		this.paramCommand = paramCommand;		
		this.object = object;
	}
	
	// overridden

	public void execute() {
		paramCommand.execute(object);
	}

    public String toString() {
        return paramCommand.toString();
    }

}