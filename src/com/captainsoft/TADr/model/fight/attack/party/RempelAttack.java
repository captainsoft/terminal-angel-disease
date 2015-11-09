/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.model.fight.attack.party;

import com.captainsoft.TADr.model.fight.*;
import com.captainsoft.TADr.model.fight.attack.*;
import com.captainsoft.spark.i18n.*;
import com.captainsoft.spark.utils.*;

/**
 * Blobs special "Rempel" attack!
 *
 * @author mathias fringes
 */
public final class RempelAttack implements PartyAttack {
	
	// fields
	
	private final PartyFighter fighter;
	private final MonsterParty monsterParty;

	// constructors
	
	public RempelAttack(PartyFighter fighter, MonsterParty monsterParty) {
		super();
		this.fighter = fighter;
		this.monsterParty = monsterParty;
	}
	
	// PartyAttack

	public Attack attack() {		
		Attack attack = new Attack();
		attack.sound = 10;
		
		int hitCount = 0;
		for (Monster monster : this.monsterParty) {
			AttackBash attackBash = new AttackBash();
			int points = funlost(monster);
			attackBash.monster = monster;
			attackBash.hit = points >= 0;
			attackBash.points = points;
			attackBash.image = Utils.rndPlus(18, 2); 
			if (attackBash.hit) {
				hitCount++; 
			}
			attack.add(attackBash);
		}	    	   
		
		
		if (hitCount == 0) {
			attack.text = new TrKey("fight.attack.rempel.fail", fighter.name());
		} else {
			attack.text = new TrKey("fight.attack.rempel.hit", fighter.name(), hitCount);
		}
		
		// resting time		
		int restingTime = PartyAttackUtils.calcRestingTime(fighter, 48, 5);					     
		attack.restingTime(restingTime); 
		attack.monsterWaitFactor = 5;
		
		// learning
		attack.hasLearned = PartyAttackUtils.learnSpecial3(fighter.member);
		if(attack.hasLearned) {
			attack.learnText = new TrKey("fight.learn.special", fighter.member.specialAttackSkill.value()).variant(fighter.member.nr());
		}
		
		return attack;
	}

	// private methods
	
	private int funlost(Monster monster) {
		 float funlost = (float)(Math.random() * fighter.member.getPtsFox() / 4 + Math .random() * fighter.curFitPoints() / 2);
         funlost += fighter.member.specialAttackSkill.value() / 8;
         
         double mMal = 0.0f;
         float malus = 1.0f;
         
         if (fighter.member.isOverweight()) {
             malus *= 2;
         }
         
         if ((monster.resist & 64) == 64) {
             funlost = -100.0f;
         }
         if ((monster.nonresist & 64) == 64) {
             funlost *= 4;
         }
         
         mMal = Math.random() * monster.speed * malus;
         if ((Math.random() * funlost) < mMal) {
             funlost = -1;
         } else {
             funlost -= Math.random() * monster.speed * malus;
         }
         
         if (funlost >= 0 && funlost < 1) {
       	  	return 0;
         } else {
	       	return (int) funlost;
	     }
	}
		
}
