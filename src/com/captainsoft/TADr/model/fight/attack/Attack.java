/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.model.fight.attack;

import java.util.*;

import com.captainsoft.spark.i18n.*;

/**
 * The list of results of an attack executed by a party member on one 
 * or more monsters.
 *
 * @author mathias fringes
 */
public final class Attack {
	
	// fields
	
	public boolean hasLearned;
	public int monsterWaitFactor;
	public int sound;
	public List<AttackBash> results;
	public TrKey text;
	public TrKey learnText;

	private int restingTime;
	
	// constructors
	
	public Attack() {
		this("", 0);
	}
	
	public Attack(String key, int restingTime) {
		this(new TrKey(key), restingTime);
	}
	
	public Attack(TrKey key, int restingTime) {
		super();
		this.text = key;
		this.restingTime = restingTime;
		monsterWaitFactor = 1;
		results = new ArrayList<AttackBash>();
	}
	
	// public
	
	public void add(AttackBash result) {
		results.add(result);
	}	
	
	public AttackBash first() {
		return results.get(0);
	}
	
	public int hitCount() {
		int count = 0;
		for(AttackBash ar : results) {
			count += ar.hit ? 1 : 0;
		}
		return count;
	}
	
	public List<AttackBash> hitList() {
		List<AttackBash> l = new ArrayList<AttackBash>();
		for(AttackBash ar : results) {
			if (ar.hit) {
				l.add(ar);
			}
		}
		return l;
	}	

	public int restingTime() {
		return restingTime;
	}
	
	public void restingTime(int restingTime) {		
		this.restingTime = Math.max(restingTime + 2, 3);		
	}

	public boolean hasHit() {
		return (hitCount() > 0); 
	}	

}