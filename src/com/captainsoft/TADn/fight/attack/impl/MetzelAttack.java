/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.fight.attack.impl;

import com.captainsoft.TADn.fight.*;
import com.captainsoft.TADn.fight.attack.*;
import com.captainsoft.TADn.party.*;
import com.captainsoft.spark.i18n.*;
import com.captainsoft.spark.utils.*;

/**
 * A party attack with the current weapon, that affects more monsters.
 * Used only by "King Ole Ozelot".
 *
 * @author mathias fringes
 */
public final class MetzelAttack implements PartyAttack {

	// fields
	
	private final MonsterParty monsterParty;
	private final PartyFighter fighter;
	
	// constructors
	
	public MetzelAttack(PartyFighter fighter, MonsterParty monsterParty) {
		super();
		this.fighter = fighter;
		this.monsterParty = monsterParty;
	}

	// overridden methods
	
	@Override
	public Attack attack() {
		Attack attack = new Attack();
		attack.sound = 8;
		
		if (fighter.weapon == null) {
			attack.text = new TrKey("fight.attack.metzel.noWeapon", fighter.name());
			return attack;
		}	    
				
		for (Monster monster : this.monsterParty) {
			AttackBash attackBash = new AttackBash();
			int points = funlost(monster);
			attackBash.monster = monster;
			attackBash.hit = (points >= 0);
			attackBash.points = points;
			attackBash.image = Utils.rndPlus(14, 2);			
			attack.add(attackBash);			
		}	    	   
				
		if (attack.hitCount() == 0) {
			attack.text = new TrKey("fight.attack.metzel.fail", fighter.name());
		} else {
			attack.text = new TrKey("fight.attack.metzel.hit", fighter.name(), attack.hitCount());
		}
		
		// resting time
		int restingTime = PartyAttackUtils.calcRestingTime(fighter, 48, 5);		
        attack.restingTime(restingTime);              
		
		// learning
        attack.hasLearned = PartyAttackUtils.learnSpecial3(fighter.member);
        if(attack.hasLearned) {
			attack.learnText = new TrKey("fight.learn.special", fighter.member.specialAttackSkill.value()).variant(fighter.member.nr());
		}
	       
        return attack;
	}
	
	// private methods
	
	private int funlost(Monster monster) {
		
		  float malus = 1.2f;		  
		  if (fighter.member.isOverweight()) {
	            malus *= 2;
		  }		 
		  Item weapon = fighter.weapon;
		
		  double funlost = (float) (Math.random() * fighter.member.getPtsFox() / 4 + Math.random() * fighter.curFitPoints() / 2);
          funlost += fighter.member.specialAttackSkill.value() / 5;
          double mMal = Math.random() * monster.speed * malus;
          if ((Math.random() * funlost) < mMal) {
              funlost = -1;
          } else {
              int itmVal = weapon.value();
              funlost += itmVal + Math.random() * itmVal / 3;
              if ((monster.nonresist & 1) == 1) {
                  funlost /= 6;
              }
          }         
          
          if (funlost >= 0 && funlost < 1) {
        	  return 0;
          } else {
        	  return (int) funlost;
          }
          
	}
	
}