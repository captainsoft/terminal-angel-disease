/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.utils.sys;

import static com.captainsoft.spark.utils.Truth.is;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.captainsoft.spark.command.Command;

/**
 * Command line parameter support. Describes the valid commands for parameters.
 * By default, "-h" shows all available commands.
 *
 * @author mathias
 */
public final class CommandArgLine extends ArgLine {
	
	// fields
	
	private final Map<String, Arg> args = new HashMap<String, Arg>();
	private final Map<String, Command> commands = new HashMap<String, Command>();
	
	// constructors
	
	public CommandArgLine() {
		super();
		add("h", "display the help page", new HelpCommand());
	}
	
	// public
	
	public void add(String p, String helpText, Command command) {
		if (args.containsKey(p)) {
			throw new IllegalArgumentException("Parameter " + p + " is already defined!");
		}
		Arg a = new Arg(p, helpText);
		args.put(a.parameter, a);		
		commands.put(a.parameter, command);		
	}
	
	public void execute() {
		Set<String> keys = arguments.keySet();
		for(String k : keys) {
			Command c = commands.get(k);
			if (is(c)) {
				c.execute();
			}
		}
	}
	
	// nested classes
	
	private class HelpCommand implements Command {

		public void execute() {		
			System.out.println("Available commands:");
			System.out.println();
			
			for (Entry<String, Arg> entry : args.entrySet()) {
				Arg a = entry.getValue();
				System.out.print("  -" + a.parameter);
				System.out.print("   " + a.helpText);
				System.out.println();
			}
			System.out.println();
			System.exit(0);
		}
		
	}

}