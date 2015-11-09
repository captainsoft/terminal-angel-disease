/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.fight;

/**
 * A monster.
 *
 * @author mathias fringes
 */
public final class Monster {	
		
	// fields
	
    public final int id;
    
    public int agressive;
    public int hitPoints;
    public int maxHitPoints;   
    public int strength;
    public int fightIndex;
    public int speed;
    public int resist;
    public int nonresist;
    public int item;
    public int chips;
    public int speech;
    public int image;
    public int ifaceTime;
    public int hitTime;
    public int lastHitPoints; 
    public int effectImage;
    public State state;    
    public String attackString;
    public String name;
    
    // constructors
    
    public Monster(int id) {
        this.id = id;
        state = State.Ready;    
    }
    
    // public methods 

    public void halfSpeed() {
        speed /= 2;
    }
    
    public boolean isDead() {
    	return (hitPoints <= 0);
    }
    
    public void kill() {		    
    	this.hitPoints = 0;
    }
    
    public void toAttackState() {
    	state = State.Attack;
    	ifaceTime = -2;
    }
    
	public void hit(int points, int attackWaitFactor) {
		lastHitPoints = points;
		state = State.Hit;
		hitTime = -10 * attackWaitFactor;
		hitPoints -= points;
		if (hitPoints <= 0) {			
			kill();
		}		
	}
    
    public boolean tick() {
    	State s = state; 
    	boolean changed = false;
    	if (ifaceTime < 0) {
    		ifaceTime++;    		
    		changed = true;
    	}
    	if (hitTime < 0) {
    		hitTime++;
    		changed = true;
    	}
    	if (changed == true) {
    		if (ifaceTime == 0 && hitTime == 0) {
    			state = State.Ready;
    		}
    	}
    	return s != state;    	
    }
    
    // overridden
    
    @Override
    public String toString() {
    	return id + " " + name;
    }
    
    // nested classes
    
    static enum State {
    	Ready,
    	Attack,
    	Hit
    }
    
}