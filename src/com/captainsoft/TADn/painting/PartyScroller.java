/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.painting;

import com.captainsoft.TADn.engine.GameEngine;
import com.captainsoft.TADn.model.Direction;
import com.captainsoft.TADn.model.Position;
import com.captainsoft.spark.ui.Updater;
import com.captainsoft.spark.utils.Sleeper;
import com.captainsoft.spark.utils.StopWatch;

/**
 * 
 * 
 * @author mathias fringes
 */
public final class PartyScroller {

	// fields

	private final GameEngine gameEngine;
	private final SimpleMapDrawer mapDrawer;
	private final Updater updater;
		
	// constructors

	public PartyScroller(GameEngine gameEngine, SimpleMapDrawer mapDrawer, Updater updater) {
		super();
		this.gameEngine = gameEngine;
		this.mapDrawer = mapDrawer;
		this.updater = updater;
	}

	// public

	public void scrollParty(Direction d, Position p) {
		//
		mapDrawer.setScrolling(true);
		scrollPartyInternal(d, p);
		mapDrawer.setScrolling(false);
	}
	
	// private

	private void scrollPartyInternal(Direction d, Position p) {
		boolean doScrollMap = gameEngine.settings().scrollMap;
		boolean doScrollParty = gameEngine.settings().scrollParty;
		Sleeper ps = new Sleeper(1);
		ps.factor(gameEngine.settings().scrollFactor);
		//
		if (d == Direction.Nowhere || d == Direction.Here && doScrollParty) {		
			if (d == Direction.Nowhere) {
				shakeHead(ps);
			}			
			else if (d == Direction.Here) {
				doJump(p, ps);
			}
			mapDrawer.retileParty();
			updateBox();
			return;
		}
		//
		// move to another tile.
		StopWatch sw = new StopWatch("SROLLING");
		mapDrawer.setFaceDirection(d);
		//
		// scrolling map
		if (doScrollMap) {
			scrollMap(p, ps);
		} else {
			mapDrawer.moveNoParty(p, 0);
			updateBox();
		}
		sw.print();
		//
		// scrolling party
		if (doScrollParty) {
			scrollParty(d, ps);
		}
		//

		// TODO clean up here
		mapDrawer.removeParty();
		mapDrawer.offset = null;
		mapDrawer.centerPartyPosition(p);
		mapDrawer.retileParty();
		updateBox();
	}
	
	

	private void scrollParty(Direction d, Sleeper ps) {
		ps.time(40);
		for (int i = 0; i < SimpleMapDrawer.scroll_steps; i++) {
			ps.reset();
			mapDrawer.scrollParty(d, (i + 1));
			updateBox();
			ps.sleep();
		}
	}

	private void scrollMap(Position p, Sleeper ps) {
		mapDrawer.retileParty();
		ps.time(40);
		for (int i = 0; i < SimpleMapDrawer.scroll_steps; i++) {
			ps.reset();
			mapDrawer.moveNoParty(p, i);
			updateBox();
			ps.sleep();
		}
	}

	private void doJump(Position p, Sleeper ps) {
		ps.time(50);
		for (int i = 0; i < 2; i++) {
			ps.reset();
			mapDrawer.scrollParty(Direction.North, (i + 1));
			updateBox();
			ps.sleep();
		}
		mapDrawer.retile(p.addY(-2));
	}

	private void shakeHead(Sleeper ps) {
		Direction fd = mapDrawer.getFaceDirection();
		ps.time(80);
		mapDrawer.setFaceDirection(fd == Direction.East ? Direction.West : Direction.East);
		mapDrawer.retileParty();
		updateBox();
		ps.sleep();
		mapDrawer.setFaceDirection(fd);
		System.out.println("shake head is over");
	}

	private void updateBox() {
		updater.update();
	}

}
