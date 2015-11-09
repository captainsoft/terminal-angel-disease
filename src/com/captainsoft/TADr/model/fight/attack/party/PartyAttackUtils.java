/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.model.fight.attack.party;

import com.captainsoft.TADr.model.fight.*;
import com.captainsoft.TADr.model.party.PartyMember;
import com.captainsoft.TADr.model.party.SkillPoints;

/**
 * Utils for Attack classes.
 *
 * @author mathias fringes
 */
public final class PartyAttackUtils {
	
	// constructors
	
	private PartyAttackUtils() {
		super();
	}
	
	// public
	
	static int calcRestingTime(PartyFighter fighter, int base, int multipler) {
		int restingTime = (base - fighter.curFitPoints()) * multipler;
		restingTime = adjustRestingTime(fighter, restingTime);
		return restingTime;
	}	
	
	public static boolean learnDefense(PartyMember member) {
		return learn(member, member.defenseSkill, 34);
	}
	
	static boolean learnSpecial3(PartyMember member) {
		return learn(member, member.specialAttackSkill, 44);
	}		
	
	static boolean learnSpecial(PartyMember member, SkillPoints pts) {
		return learn(member, pts, 44);
	}	
		
	static boolean learn(PartyMember member, SkillPoints pts, int tresh) {
		if (pts == null) {
			return false;
		}
		if (pts.canLearn()) {
			if (Math.random() * tresh < member.getPtsFox()) {
				boolean learn = pts.learn();
				learn = learn && (pts.value() % 5 == 0);
			}
		}
		return false;
	}
	
	// private
	
	private static int adjustRestingTime(PartyFighter fighter, int restingTime) {
		if (restingTime < 1) {
			restingTime = 1;
		}
		if (fighter.member.isOverweight()) {
			restingTime *= 2;
		}
		return restingTime;
	}

}