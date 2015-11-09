/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.fight.attack.impl;

import com.captainsoft.TADn.fight.Monster;
import com.captainsoft.TADn.fight.MonsterParty;
import com.captainsoft.TADn.fight.PartyFighter;
import com.captainsoft.TADn.fight.attack.Attack;
import com.captainsoft.TADn.fight.attack.AttackBash;
import com.captainsoft.TADn.fight.attack.PartyAttack;
import com.captainsoft.spark.i18n.TrKey;
import com.captainsoft.spark.utils.Utils;

/**
 * A party attack with a "slime bomb". That is a type of item.
 *
 * @author mathias fringes
 */
public final class SlimeBombAttack implements PartyAttack {
	
	// fields
	
	private final MonsterParty monsterParty;
	private final PartyFighter fighter;
	
	// constructors
	
	public SlimeBombAttack(PartyFighter fighter, MonsterParty monsterParty) {
		this.fighter = fighter;
		this.monsterParty = monsterParty;
	}
	
	// overridden methods
	
	@Override
	public Attack attack() {
		Attack attack = new Attack();
		attack.sound = 7;
		
		// attacks every monster
		for (Monster monster : monsterParty) {
			AttackBash ar = new AttackBash(monster);
			if ((monster.resist & 32) != 32) {
                monster.halfSpeed();              
            }
            if ((monster.nonresist & 32) == 32) {
                monster.speed = 1;                 
            }
            ar.hit = true;  
            ar.image = Utils.rndPlus(12, 2);
            attack.add(ar);            
		}		
		
		// attack text			
		if (attack.hitCount() > 0) {
			attack.text = new TrKey("fight.attack.slime.hit", fighter.name(), attack.hitCount());
		} else {
			attack.text = new TrKey("fight.attack.slime.fail", fighter.name());
		}
		
		// resting time
		int restingTime = PartyAttackUtils.calcRestingTime(fighter, 30, 5);
		attack.restingTime(restingTime);
				
		return attack;
	}	

}
