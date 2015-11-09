/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.party;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.captainsoft.TADn.model.Position;
import com.captainsoft.spark.collect.Clist;
import com.captainsoft.spark.utils.ExcUtils;
import com.captainsoft.spark.utils.LoopCounter;
import com.captainsoft.spark.utils.Utils;

/**
 * The party. 
 *
 * @author mathias fringes
 */
public final class Party implements Iterable<PartyMember> {
  
    // fields

    public final PartyMember[] members;
    
    private final List<PartyMember> memberList;

    private int mapNumber = 0;
    private long coins = 0;
    private PartyInventory partyInventory;
    private Position position = new Position();
    private String name = "";    

    // constructors

    public Party() {
    	super();
    	PartyMemberFactory pmFactory = new PartyMemberFactory();
    	this.members = pmFactory.createAllMembers();       
        this.partyInventory = new PartyInventory(this);       
        memberList = new Clist<PartyMember>(members);        
    }

    // public methods 

    public void addCoins(int c) {
        this.coins += c;
    }

    public long coins() {
        return this.coins;
    }
   
    public PartyMember member(int nr) {
        return this.members[nr - 1];
    }

    public PartyMember[] members() {
        return this.members;
    }          

    public String name() {
    	return this.name;
    }  

    public void name(String name) {
        this.name = name;
    }

    public PartyInventory inventory() {
    	return partyInventory;
    }

    public boolean isAffordable(int c) {
        return (this.coins >= c);
    }
        
    public void coins(long l) {
        this.coins = l;
    }
    
	public void position(Position position) {
		this.position = position;
	}

	public Position position() {
		return position;
	}

	public void mapNumber(int mapIndex) {
		this.mapNumber = mapIndex;
	}

	public int mapNumber() {
		return mapNumber;
	}

	public PartyMember detective() {
		return this.members[0]; 
	}
	
	public PartyMember blob() {
		return this.members[1]; 
	}
	
	public PartyMember ritter() {
		return this.members[2]; 
	}
	
	public PartyMember professor() {
		return this.members[3]; 
	}
	
	// public
	
	public int randomMemberNr() {
		return Utils.random(1, 5);		
	}		
	
	public List<PartyMember> allFrom(final int start) {
		ExcUtils.argIn("start", start, 1, 4);
		//
		List<PartyMember> members = new ArrayList<PartyMember>();
		LoopCounter counter = new LoopCounter(start, 4);
		while(counter.loopCount() == 0) {
			members.add(member(counter.current()));
			counter.count();
		}			
		return members;		
	}

	
	// implementation of Iterable
	
	@Override
	public Iterator<PartyMember> iterator() {		
		return (memberList.iterator());
	}
 
}