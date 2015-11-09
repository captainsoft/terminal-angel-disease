package com.captainsoft.TADn._render;

import com.captainsoft.spark.control.Command;

/**
 * 
 * @author mathias
 */
public final class AnimationCommand {
	
	private final Command command;
	
	private long time;
	

	public AnimationCommand(Command command, long time) {
		super();
		this.command = command;
		this.time = time;
	}
	
	
	public void deduct(long delta) {
		time -= delta;
	}
	
	public boolean over() {
		return (time <= 0);
	}
	
	public void execute() {
		command.execute();
	}
		
}
