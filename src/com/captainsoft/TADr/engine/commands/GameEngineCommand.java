package com.captainsoft.TADr.engine.commands;

import com.captainsoft.TADr.engine.GameEngine;
import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.spark.command.AbstractCommand;

/**
 * Base class for commands with the game engine as a fields.
 * 
 * @author mathias fringes
 */
public abstract class GameEngineCommand extends AbstractCommand {

    // fields

	protected final GameEngine gameEngine;

    // constructors

	GameEngineCommand() {
		this("");
	}
	
	protected GameEngineCommand(String name) {
		super(name);
		this.gameEngine = TadRepo.inst().GameEngine();
	}
	
}