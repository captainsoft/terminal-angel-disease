/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.fight;

import java.util.*;

import com.captainsoft.TADn.*;
import com.captainsoft.TADn.loader.*;
import com.captainsoft.spark.utils.*;

/**
 * Represents a monster party.
 *
 * @author mathias fringes
 */
public final class MonsterParty implements Iterable<Monster> {
	
	// fields
	
	public final static int MAX_PLACED = 5;
	
	public final Collection<Monster> allMonsters;
	public final Monster[] monsterPlace;
	
	public int allChips;
	public int monsterItemId;	
		
	private List<Monster> liveMonsters;
		
	// constructors
	
	public MonsterParty(int ... monsterIds) {
		super();
		this.liveMonsters = new ArrayList<Monster>();
		this.monsterPlace = new Monster[MAX_PLACED];
		//
		List<Monster> monsters = new ArrayList<Monster>();
		MonsterLoader monsterLoader = TadRepo.inst().MonsterLoader();
		for (int i = 0; i < monsterIds.length; i++) {
			if (monsterIds[i] != 0) {
				Monster monster = monsterLoader.loadMonster(monsterIds[i]);
				monster.fightIndex = i;				
				monsterPlace[i] = monster;
				liveMonsters.add(monster);
				monsters.add(monster);
				
				allChips += monster.chips;
			}
		}		
		this.allMonsters = Collections.unmodifiableCollection(monsters);
		//	
		// setup
		allChips *= 3;
		//
		// monsterItem
		monsterItemId = rndNotDead().item;
		monsterItemId = (monsterItemId > 0) ? monsterItemId : 0;  
	}
	
	// public methods
	

	public Monster monster(int index) {
		return this.monsterPlace[index];
	}
	
	public boolean allDead() {
		return (liveMonsters.size() == 0);		
	}
	
	public Monster rndNotDead() {
		if (allDead()) {
			return null;
		}		
		return Utils.randomSelect(liveMonsters);		
	}
	
	public void remove(Monster monster) {	
		liveMonsters.remove(monster);
	}
		
	// overridden methods

	@Override
	public Iterator<Monster> iterator() {		
		return liveMonsters.iterator(); 
	}

}