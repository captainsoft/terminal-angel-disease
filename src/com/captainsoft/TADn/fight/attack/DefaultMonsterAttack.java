/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.fight.attack;

import com.captainsoft.TADn.TadRepo;
import com.captainsoft.TADn.fight.Fight;
import com.captainsoft.TADn.fight.Monster;
import com.captainsoft.TADn.fight.PartyFighter;
import com.captainsoft.TADn.fight.PartyFighter.Mode;
import com.captainsoft.TADn.fight.attack.impl.PartyAttackUtils;
import com.captainsoft.TADn.party.PartyMember;
import com.captainsoft.spark.i18n.Translator;
import com.captainsoft.spark.utils.Utils;

/** 
 * An attack executed by a monster upon an party member.
 * 
 * @author mathias fringes
 */
public final class DefaultMonsterAttack {
	
	// fields
	
	private static Translator translatorInstance;
	
	private final Monster monster;
	private final PartyFighter fighter;
	private final PartyMember member;
	
	// constructors
	
	public DefaultMonsterAttack(Monster monster, Fight fight) {
		super();
		this.monster = monster;
		this.fighter = fight.fighters[Utils.random(4)];
		this.member = fighter.member;
	}
	
	// public methods
	
	public MonsterAttackResult attack() {
		MonsterAttackResult ar = new MonsterAttackResult();
		
		StringBuilder attackString = new StringBuilder();
		attackString.append(monster.name);
		attackString.append(" ");
		if (monster.attackString.endsWith("<X>")) {
			attackString.append(monster.attackString.replaceAll("<X>", "   " + member.name() + ",   "));
		} else {
			attackString.append(monster.attackString.replaceAll("<X>", "   " + member.name() + "   "));
			attackString.append(", ");			 	          
		}		
		
		ar.hit = hitByMonster();
		if (ar.hit) {
			ar.funLost = calcFunLostPoints();
			if (ar.funLost > 0) {
				if (fighter.mode == Mode.Defense) {
					attackString.append(trans().word("fight.defaultAttack.hitsWithDefense", ar.funLost));
				} else {
					attackString.append(trans().word("fight.defaultAttack.hits", ar.funLost));
				}
			} else {
				if (fighter.mode == Mode.Defense) {
					attackString.append(trans().word("fight.defaultAttack.canDefense"));
				} else {
					attackString.append(trans().word("fight.defaultAttack.notHitCharm"));
				}
			}
		} else {			
			attackString.append("aber haut daneben!");			
		}				
		
		// learn
		if (fighter.mode == Mode.Defense) {			
			PartyAttackUtils.learnDefense(fighter.member);
		}
					
		ar.partyVictim = fighter;
		ar.attackText = attackString.toString();		
		return ar;
	}
		
	// private methods
	
	private static Translator trans() {
		if (translatorInstance == null) {
			translatorInstance = TadRepo.inst().Trans();
		}
		return translatorInstance;
	}
	
	private boolean hitByMonster() {				
		double hitVictim = 0;
	    hitVictim = Math.random() * member.getPtsFox() / 3;
	    hitVictim += Math.random() * fighter.curFitPoints();
	    hitVictim += Math.random() * member.defenseSkill.value() / 2;
	    hitVictim -= Math.random() * monster.speed;
	    hitVictim -= monster.agressive;
	    if (member.isOverweight()) {
	    	hitVictim -= 10;
	    }	    
	    return (hitVictim < 0);		
	}
		
	private int calcFunLostPoints() {
		double funLost = 0;
		int defenseSkill = member.defenseSkill.value();
		funLost = Math.random() * monster.strength + Math.random() * monster.strength;
		funLost += monster.strength * 2;
		funLost -= member.protect();
		funLost -= Math.random() * member.protect() / 2;
		funLost -= Math.random() * fighter.curFitPoints() / 3;
		funLost -= Math.random() * defenseSkill / 5 - Math.random() * member.getPtsFox() / 3;
		if (fighter.mode == Mode.Defense) {
			funLost -= Math.random() * defenseSkill / 5 - Math.random() * defenseSkill / 5;
			funLost -= Math.random() * fighter.curFitPoints() - Math.random() * fighter.curFitPoints();
		} 
		return (int)funLost;		
	}

}
