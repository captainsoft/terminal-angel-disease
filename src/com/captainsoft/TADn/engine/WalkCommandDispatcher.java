/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.engine;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import com.captainsoft.TADn.model.Position;
import com.captainsoft.spark.control.Command;
import com.captainsoft.spark.utils.Log;

/**
 * 
 * 
 * @author mathias fringes
 */
public final class WalkCommandDispatcher {

	// fields
	
	private final static int lv = 51;
	
	private final LinkedBlockingQueue<Command> events;
	private final GameEngine gameEngine;

	// constructors
	
	public WalkCommandDispatcher(GameEngine gameEngine) {
		super();
		this.gameEngine = gameEngine;
		events = new LinkedBlockingQueue<Command>();
		//
		startListening();
	}
	
	// public
		
	public synchronized void addNext(Command command) {
		Log.log(lv, "adding important event");
		stop();
		add(command);
	}
	
	public synchronized void go(List<Position> positions) {
		stop();
		if (positions == null) {
			return;
		}
		for(Position p : positions) {
			goToPosition(p);
		}
	}
	
	public synchronized void goToPosition(final Position p) {
		Log.log(lv, "...goto " + p);
		Command gocmd = new Command() {			
			public void execute() {
				boolean action = gameEngine.scrollParty(p);
				if (action) {
					stop();
				}
			}
		};	
		add(gocmd);
	}
	
	public synchronized void stop() {
		Log.log(lv, "stopping!");
		events.clear();
	}

	// private
	
	private void add(Command c) {
		events.add(c);
	}
	
	private void startListening() {
		Log.log(lv, "Walker starts listening.");
		Thread t = new Thread(new ListeningThread());
		t.start();
	}

	//
	// nested classes

	private class ListeningThread implements Runnable {
		
		private void doEventAction() {				
			Command c = null;
			try {
				c = events.take();					
			} catch (InterruptedException e) {
				throw new RuntimeException(e);			
			}
			//
			if (c != null) {
				Log.log(lv, "...exe event of overall (" + events.size() + ")");				
				c.execute();				
			}
		}
		
		@Override
		public void run() {
			try {
				while (true) {
					doEventAction();
				}
			} catch(Exception e) {				
				gameEngine.handleException(e);
			}
		}
		
	}

}
