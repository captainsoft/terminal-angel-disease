/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.command;

import java.util.*;

/**
 * A container command structure. Will execute all contained commands.
 *
 * @author mathias fringes
 */
@SuppressWarnings("serial")
public final class CommandList extends ArrayList<Command> implements Command {

	// constructors
	
	public CommandList() {
		super();		
	}

	public CommandList(Collection<? extends Command> c) {
		super(c);		
	}

	public CommandList(int initialCapacity) {
		super(initialCapacity);
	}
	
	// Command
	
	public void execute() {
		for (Command c : this) {
			c.execute();
		}		
	}

}