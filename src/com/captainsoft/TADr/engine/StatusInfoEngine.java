/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.engine;

import com.captainsoft.TADr.model.party.InventoryPlace;
import com.captainsoft.TADr.model.party.Party;
import com.captainsoft.TADr.model.party.PartyMember;
import com.captainsoft.spark.utils.Utils;

/**
 * Status info engine.
 *
 * @author mathias fringes
 */
final class StatusInfoEngine {

    //fields

	private final GameEngine gameEngine;

    // constructors

	public StatusInfoEngine(GameEngine gameEngine) {
		super();
		this.gameEngine = gameEngine;
	}	

    // public

	public void statusInfor() {
		Party party = gameEngine.party();	
		switch (Utils.random(30)) {
			case 12:			
				PartyMember member = getRandomMember(party);
				if (member.isOverweight()) {
					gameEngine.say(member, "member.info.overweight");
				}
				break;
			case 13:
				member = getRandomMember(party);
				if (member.fun.cur() < 10) {
					gameEngine.say(member, "member.info.littleFun");
				}
				break;
			case 14:				
				member = getRandomMember(party);
				if (member != party.blob()) {
					if (member.inventory().item(InventoryPlace.Feet) == null) {
						if (member == party.ritter()) {
							member = party.blob();
						}
						gameEngine.say(member, "member.info.noHouse");	
					}				
				}					
				break;
			default:
				break;
		}
	}

    // private

	private PartyMember getRandomMember(Party party) {			
		return party.member(party.randomMemberNr());		
	}
	
}