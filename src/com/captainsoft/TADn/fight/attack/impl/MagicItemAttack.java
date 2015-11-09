/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.fight.attack.impl;

import java.util.*;

import com.captainsoft.TADn.fight.*;
import com.captainsoft.TADn.fight.attack.*;
import com.captainsoft.TADn.party.*;
import com.captainsoft.spark.i18n.*;
import com.captainsoft.spark.utils.*;
import static com.captainsoft.spark.utils.Truth.*;

/**
 * A party attack with a magic item.
 *
 * @author mathias fringes
 */
public final class MagicItemAttack implements PartyAttack {

	// fields
			
	private final Item item;
	private final MonsterParty monsterParty;
	private final PartyFighter fighter;
	private final SkillPoints skills;
	
	// constructors
	
	public MagicItemAttack(PartyFighter fighter, MonsterParty monsterParty, Item item) {
		super();
		this.fighter = fighter;
		this.monsterParty = monsterParty;
		this.item = item;
		this.skills = skills();
	}	
	
	// overridden methods
	
	@Override
	public Attack attack() {
		Attack attack = new Attack();
		attack.sound = soundNr();
				
		ArrayList<Monster> targets = gatherTargets();			
		for (Monster monster : targets) {
			AttackBash ar = attackMonster(monster);				
			attack.add(ar);
		}
						
		int funlost = (attack.first() != null) ? attack.first().points : 0;		
		attack.text = attackKey(item.type().isSingleMonsterMagic(), attack.hitCount(), funlost);		
		attack.hasLearned = PartyAttackUtils.learnSpecial(fighter.member, skills);
		if(attack.hasLearned) {
			attack.learnText = new TrKey("fight.learn.magicItem", skills.value()).variant(fighter.member.nr());
		}		
		int restingTime = PartyAttackUtils.calcRestingTime(fighter, 30, 2);
		attack.restingTime(restingTime);
		
		return attack;
	}
	
	// private methods

	private ArrayList<Monster> gatherTargets() {
		ArrayList<Monster> targets = new ArrayList<Monster>();		
		if (item.type().isSingleMonsterMagic()) {
			targets.add(fighter.targetMonster);					
		} else {
			for (Monster monster : monsterParty) {
				targets.add(monster);				
			}
		}
		return targets;
	}
	
	private AttackBash attackMonster(Monster monster) {
		AttackBash ar = new AttackBash(monster);	
		
		int itemValue = item.value();		
		double funlost = itemValue + Math.random() * itemValue / 2; 
		if (is(skills)) {
			funlost += Math.random() * skills.value() / 5;
		}
        
        int resist = resistNr();        
        if ((monster.nonresist & resist) == resist) {
            funlost *= 3;
        } 
        if ((monster.resist & resist) == resist) {
            funlost /= 4;
        }        
        funlost -= Math.random() * monster.speed;
        
		ar.hit = funlost >= 0;
		ar.points = (int)funlost;		
		ar.image = imageNr();		
		return ar;
	}	
	
	private int imageNr() {
		 switch (item.type()) {
		 	case MagicSingle:
		 	case MagicMultiple:
		 		return Utils.rndPlus(10, 2);
		 	case MagicFireSingle:
		 	case MagicFireMultiple:
		 		return Utils.rndPlus(6, 2);
		 	case MagicWaterSingle:
		 	case MagicWaterMultiple:
		 		return Utils.rndPlus(8, 2);
		 	default:
		 		throw new RuntimeException("Unknown item type: " + item.type());
		 }
	}
	
	private int soundNr() {
		switch (item.type()) {
	 	case MagicSingle:
		 	case MagicMultiple:
		 		return 6;
		 	case MagicFireSingle:
		 	case MagicFireMultiple:
		 		return 4;
		 	case MagicWaterSingle:
		 	case MagicWaterMultiple:
		 		return 5;
		 	default:
		 		throw new RuntimeException("Unknown item type: " + item.type());
		 }	
	}
	
	private int resistNr() {
		switch (item.type()) {
			case MagicSingle:
			case MagicMultiple:
		 		return 4;
			case MagicFireSingle:
			case MagicFireMultiple:
		 		return 8;
			case MagicWaterSingle:
			case MagicWaterMultiple:
		 		return 16;
		 	default:
		 		throw new RuntimeException("Unknown item type: " + item.type());
		}
	}
	
	private SkillPoints skills() {		
		if (item.isMagicScroll()) {
			return fighter.member.specialAttackSkill;
		} else if (item.isChemicalMagic()) {
			return fighter.member.special4;
		} else {
			return null;
        }	
	}
	
	private TrKey attackKey(boolean single, int count, int funlost) {		
		int val = single ? funlost : count; 
		String sKey = single ? "single" : "multi";
		String mKey = (val > 0 ) ? "hit" : "fail";
		String key = "fight.attack." + verbKey() + "." + sKey +  "." + mKey;
		//
		return new TrKey(key, fighter.name(), item.name(), val);			
	}
	
	private String verbKey() {		
		if (item.isMagicScroll()) {
            return "magic";
		} else if (item.isChemicalMagic()) {
            return "chem";
		} else if (item.isBomb()) {
           return "bomb";
		} else {
			throw new RuntimeException("Unknown item type: " + item.type());
		}
	}

}
