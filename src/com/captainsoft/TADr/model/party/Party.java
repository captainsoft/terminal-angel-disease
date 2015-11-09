/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.model.party;

import static com.captainsoft.spark.utils.ExcUtils.argIn;
import static com.captainsoft.spark.utils.Utils.stringer;

import java.util.ArrayList;
import java.util.List;

import com.captainsoft.TADr.model.Position;
import com.captainsoft.spark.utils.LoopCounter;
import com.captainsoft.spark.utils.Utils;

/**
 * The party. 
 *
 * @author mathias fringes
 */
public final class Party {
  
    // fields

    public final PartyMember[] members;
        
    private final PartyInventory partyInventory;

    private int mapNumber = 0;
    private long coins = 0;    
    private Position position = new Position();
    private String name = "";    

    // constructors

    public Party() {
    	super();
    	PartyMemberFactory pmFactory = new PartyMemberFactory();
    	members = pmFactory.createAllMembers();       
        partyInventory = new PartyInventory(this);     
    }

    // public 

    public void addCoins(int coins) {
        this.coins += coins;
    }

    public long coins() {
        return coins;
    }
   
    public PartyMember member(int nr) {
        return members[nr - 1];
    }

    public PartyMember[] members() {
        return members;
    }          

    public String name() {
    	return name;
    }  

    public void name(String name) {
        this.name = name;
    }

    public PartyInventory inventory() {
    	return partyInventory;
    }

    public boolean isAffordable(int coins) {
        return (this.coins >= coins);
    }
        
    public void coins(long coins) {
        this.coins = coins;
    }
    
	public void position(Position position) {
		this.position = position;
	}

	public Position position() {
		return position;
	}

	public void mapNumber(int mapNumber) {
		this.mapNumber = mapNumber;
	}

	public int mapNumber() {
		return mapNumber;
	}

	public PartyMember detective() {
		return members[0];
	}
	
	public PartyMember blob() {
		return members[1];
	}
	
	public PartyMember ritter() {
		return members[2];
	}
	
	public PartyMember professor() {
		return members[3];
	}
	
	public int randomMemberNr() {
		return Utils.random(1, 5);		
	}		
	
	public List<PartyMember> allFrom(int start) {
		argIn("start", start, 1, 4);
		//
		List<PartyMember> members = new ArrayList<PartyMember>();
		LoopCounter counter = new LoopCounter(1, 4);
		counter.current(start);
		while(counter.loopCount() == 0) {	
			members.add(member(counter.current()));
			counter.count();			
		}			
		return members;		
	}

    // overridden

    @Override
    public String toString() {
       return stringer("Party", name, mapNumber, position);
    }
 
}