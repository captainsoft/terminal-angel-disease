/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.engine;

import com.captainsoft.TADn.Debugger;
import com.captainsoft.TADn.model.*;
import com.captainsoft.TADn.party.*;
import com.captainsoft.spark.utils.*;

/**
 * 
 *
 * @author mathias fringes
 */
final class FightEngine {
	
	/**
	 * Checks if the party should do a random fight. 
	 * Returns null if it shouldn't.
	 * 
	 * @param levelMap
	 * @param position
	 * @return
	 */
	public Position shouldFight(LevelMap levelMap, Position position) {
		if (Debugger.inst.noMonsters) {
			return null;
		}
		int x = position.x;
		int y = position.y;
		Position fightPosition = null;
		boolean doFight = false;
		for (int mx = x - 1; mx <= x + 1; mx++) {
			for (int my = y - 1; my <= y + 1; my++) {				
				TileDanger danger = levelMap.tile(mx, my).danger();				
				switch (danger) {
					case High:
						doFight = (Utils.random(6) > 1);
						break;
					case Low:
						doFight = (Utils.random(150) == 1);
						break; 
					default:
						break;
				}				
				if (doFight) {
					fightPosition = new Position(mx, my);
					break;
				}
			}
			if (doFight) {
				break;
			}
		}		
		if (!doFight && levelMap.tile(position).danger() == TileDanger.High) {
			doFight = (Utils.random(10) > 0);
			if (doFight) {
				fightPosition = position;
			}
		}
		return fightPosition;
	}
		
}