/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.model.fight.attack.party;

import com.captainsoft.TADr.model.fight.*;
import com.captainsoft.TADr.model.fight.attack.*;
import com.captainsoft.TADr.model.party.PartyMember;
import com.captainsoft.spark.collect.*;
import com.captainsoft.spark.i18n.*;

/**
 * A party attack with the current weapon. Affects only one monster.
 *
 * @author mathias fringes
 */
public final class WeaponAttack implements PartyAttack {
	
	// fields
	
	private final Monster monster;
	private final PartyFighter fighter; 
	
	// constructors
	
	public WeaponAttack(PartyFighter fighter, Monster monster) {
		super();
		this.fighter = fighter;
		this.monster = monster;
	}

	// PartyAttack

	public Attack attack() {
		
		Attack attack = new Attack();
		attack.sound = 3;
		
	    AttackBash attackBash = new AttackBash();
	    attackBash.monster = monster;
	    
	    if (fighter.weapon == null) {
	    	attack.text = new TrKey("fight.weapon.attack.noWeapon", fighter.name());
	    } else {	    
	    	
		    attackBash.hit = hasWeaponHitMonster(monster);
		    if (attackBash.hit) {
		    	attackBash.points = calcWeaponAttackPoints(monster);
		    }
		    int restingTime = PartyAttackUtils.calcRestingTime(fighter, 30, 4);
		    attack.restingTime(restingTime);
		    		    
		    if (attackBash.hit) {
				int points = attackBash.points;
				if (points > 0) {
					attack.text = new TrKey("fight.weapon.attack.hit", fighter.name(), fighter.weapon.name(), points);
				} else {
					attack.text = new TrKey("fight.weapon.attack.fail", fighter.name(), fighter.weapon.name());
				}
			} else {
				attack.text = new TrKey("fight.weapon.attack.miss", fighter.name(), fighter.weapon.name());
			}
		    
		    attack.hasLearned = learnWeaponAttack(fighter.member);
		    if (attack.hasLearned) {
		    	attack.learnText = new TrKey("fight.learn.weapon", fighter.member.weaponSkill.value()).variant(fighter.member.nr());
		    }
		    
	    }
	    
	    attack.results = new Clist<AttackBash>(attackBash); 
		return attack;
	}

	// private

	 private boolean hasWeaponHitMonster(Monster monster) {
		 PartyMember member = fighter.member;
    	 int hitMonster = (int) (Math.random() * (member.getPtsFox() / 3));
         hitMonster += Math.random() * fighter.curFitPoints();
         hitMonster += Math.random() * member.weaponSkill.value() / 5;
         hitMonster -= Math.random() * monster.speed;
         if (member.isOverweight()) {
             hitMonster -= 10;
         }
         return (hitMonster > 0);
    }
   
    private int calcWeaponAttackPoints(Monster monster) {
    	int funLost = 0;    	
    	PartyMember member = fighter.member;
    	
    	int itmVal = fighter.weapon.value();
    	double dfunLost = itmVal + (Math.random() * itmVal / 4);
    	dfunLost += Math.random() * member.weaponSkill.value() / 5;
		dfunLost += Math.random() * (member.getPtsFox() / 3); 
		dfunLost += Math.random() * fighter.curFitPoints();
		dfunLost -= Math.random() * monster.speed - Math.random() * monster.speed;
		if ((monster.resist & 1) == 1) {					
		     dfunLost /= 6;
		}
		funLost = (int) dfunLost;
		return funLost;
	}       

	private boolean learnWeaponAttack(PartyMember member) {
		return PartyAttackUtils.learn(member, member.weaponSkill, 88);	
	}
	
}
