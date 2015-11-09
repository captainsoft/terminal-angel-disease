/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.fight.attack.impl;

import com.captainsoft.TADn.fight.*;
import com.captainsoft.TADn.fight.attack.*;
import com.captainsoft.spark.i18n.*;
import com.captainsoft.spark.utils.*;

/**
 * The critical attack, executed by the Detective Summerkamp!
 * 
 * Note: in the old version, the police officer could only arrest one monster at
 * a time!
 * 
 * @author mathias fringes
 */
public final class PoliceArrestAttack implements PartyAttack {

	//
	
	private final PartyFighter fighter;
	private final MonsterParty monsterParty;

	//
	
	public PoliceArrestAttack(PartyFighter fighter, MonsterParty monsterParty) {
		super();
		this.fighter = fighter;
		this.monsterParty = monsterParty;
	}

	//
	
	@Override
	public Attack attack() {
		Attack attack = new Attack();
		attack.sound = 9;

		int hitCount = 0;
		for (Monster monster : this.monsterParty) {
			AttackBash attackBash = new AttackBash();
			attackBash.monster = monster;
			int points = funlost(monster);
			attackBash.hit = points >= 1;
			attackBash.critical = attackBash.hit;
			attackBash.image = Utils.rndPlus(16, 2);

			if (attackBash.hit) {
				hitCount++;
			}

			attack.add(attackBash);
		}
		
		if (hitCount == 0) {
			attack.text = new TrKey("fight.attack.police.fail", fighter.name());
		} else {
			attack.text = new TrKey("fight.attack.police.hit", fighter.name(), hitCount);
		}

		// learning
		attack.hasLearned = PartyAttackUtils.learnSpecial3(fighter.member);
		if(attack.hasLearned) {
			attack.learnText = new TrKey("fight.learn.special", fighter.member.specialAttackSkill.value()).variant(fighter.member.nr());
		}
		
		// resting time
		int time_resting = (48 - fighter.member.getPtsFit()) * 5;
        if (time_resting < 1) {
            time_resting = 1;
        }
        if (fighter.member.isOverweight()) {
            time_resting *= 2;
        }	        	       		
        attack.restingTime(time_resting);        
		return attack;
	}

	// private methods

	private int funlost(Monster monster) {

		float malus = 1.1f;
		if (fighter.member.isOverweight()) {
			malus *= 2;
		}

		float funlost = (float)(Math.random() * fighter.member.getPtsFox() / 3);														
		funlost += (Math.random() * fighter.curFitPoints() / 3);		
		funlost += fighter.member.specialAttackSkill.value() / 5;
		
		if ((monster.resist & 128) == 128) {
			funlost = -100.0f;
		}
		if ((monster.nonresist & 128) == 128) {
			funlost *= 2;
		}
		
		double mMal = Math.random() * monster.speed * malus; 
		mMal += Math.random() * monster.speed * malus;
		if ((Math.random() * funlost) < mMal) {
			funlost = -1;
		}

		return (int)funlost;
	}

}