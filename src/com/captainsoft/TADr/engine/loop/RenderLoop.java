/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.engine.loop;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.captainsoft.TADr.engine.GameEngine;
import com.captainsoft.TADr.model.Position;
import com.captainsoft.spark.command.Command;
import com.captainsoft.spark.render.Animation;
import com.captainsoft.spark.utils.Log;

/**
 * The render loop. Plays the animations. User interaction has to go through
 * here via commands.
 * 
 * @author mathias fringes
 */
public final class RenderLoop {

    // fields

    private final GameEngine gameEngine;
	
	private boolean accept = true;
	private Collection<Animation> mapAnimations = new ArrayList<Animation>();	
	private Command command;	
	private Command nextCommand;	
	private List<Animation> animations = new ArrayList<Animation>();
	private long animationTimeCount;
	private WalkAnimationStack walkAnimationsStack;
	
	// constructors
	
	public RenderLoop(GameEngine gameEngine) {
		super();
        this.gameEngine = gameEngine;
		walkAnimationsStack = new WalkAnimationStack(gameEngine);
	}
		
	// public
	
	public synchronized boolean accept() {
		return accept;
	}
	
	public synchronized void accept(boolean accept) {
		this.accept = accept;
		
		if (!accept) {
			command = null;
			nextCommand = null;
		}
	}

    /**
     * Sets the command that need to be executed immediately (mainly input by user
     * as a key stroke or mouse click).
     *
     * @param command
     */
	public synchronized void command(Command command) {
		if (!accept) {
			return;
		}
		this.command = command;
		nextCommand = null;	
	}

    /**
     * Sets the command that needs to be executed *after* the party has finished her walk
     * to the next tile.
     *
     * @param nextCommand
     */
	public synchronized void nextCommand(Command nextCommand) {			
		if (!accept) {
			return;
		}

		Log.info("ADDING next command " + nextCommand);
		if (walkAnimationsStack.over()) {
			command = nextCommand;
		} else {			
			this.nextCommand = nextCommand;
			command = null;
		}
		walkAnimationsStack.stop();
		
	}
	
	public void playPartyAnimation(Animation animation) {
		walkAnimationsStack.setAnimation(animation);
	}
	
	public void walkTo(List<Position> positions) {
		walkAnimationsStack.stop();
		walkAnimationsStack.goTo(positions);	
	}

    public void walkToAndThen(List<Position> positions, Command destinationCommand) {
        walkTo(positions);
        walkAnimationsStack.setDestinationCommand(destinationCommand);
    }

	public void setMapAnimations(Collection<Animation> mapAnimations) {
		this.mapAnimations = mapAnimations;
	}
	
	public void addAnimation(Animation a) {
		animations.add(a);
	}
	
	public void clear() {
		command = null;
		nextCommand = null;
		walkAnimationsStack.stop();
		animationTimeCount = 0;
		animations.clear();
		mapAnimations.clear();
	}
	
	// animate!!
	
	public void update(long elapsedMillis) {
				
		boolean executedCommand = false;		
		
		// -----------------------------------------------------
		// *** COMMANDS (input by user) ***
		// -----------------------------------------------------
		
		if (command != null) {
			Command alias = command;
			Log.force("Executing: " + command);
			command = null;
			alias.execute();
			executedCommand = true;
		}
		
		// -----------------------------------------------------
		// *** ANIMATIONS ****
		// -----------------------------------------------------
		 		
		if (elapsedMillis <= 0) {
			return;
		}
		
		// -----------------------------------------------------
		// walking 
		
		if (!walkAnimationsStack.over()) {

			walkAnimationsStack.count(elapsedMillis);
			if (walkAnimationsStack.over()) {		
				Log.info("Walking is over!");
				if (nextCommand == null) {
                    command = walkAnimationsStack.pullDestinationCommand();
                } else {
					if (!executedCommand) {
						nextCommand.execute();
						nextCommand = null;
					}
				}				
			}
		} else {
            gameEngine.pollMoveKeys();
        }
			
		// -----------------------------------------------------
		// others
		
		animationTimeCount += elapsedMillis;

		if (animationTimeCount > 10) {	
			
			if (mapAnimations.size() > 0) {
				for (Animation a : mapAnimations) {
					a.count(animationTimeCount);						
				}								
			}


            playOtherAnimations();

            animationTimeCount = 0;
        }

    }

    private void playOtherAnimations() {
        if (animations.size() > 0) {
            boolean checkForOver = false;

            for (int i = 0; i < animations.size(); i++) {
                Animation a = animations.get(i);
                if (!a.over()) {
                    a.count(animationTimeCount);
                } else {
                    checkForOver = true;
                }
            }
            if (checkForOver) {
                Iterator<Animation> i = animations.iterator();
                while (i.hasNext()) {
                    if (i.next().over()) {
                        i.remove();
                    }
                }
            }
        }
    }


}