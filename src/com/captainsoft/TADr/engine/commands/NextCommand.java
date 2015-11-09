package com.captainsoft.TADr.engine.commands;

import com.captainsoft.spark.command.Command;

import static com.captainsoft.spark.utils.Utils.stringer;

/**
 * A command that holds another, regular command that should get executed after the party 
 * has moved completely to another tile.
 * 
 * @author mathias fringes
 */
public final class NextCommand extends GameEngineCommand {

    // fields

	private final Command command;

    // constructors

	public NextCommand(Command command) {
		super();
		this.command = command;
	}

    // Command

	public void execute() {
		gameEngine.nextCommand(command);
	}

    // overridden
	
	@Override
	public String toString() {
		return stringer("NextCommand", command);
	}

}
