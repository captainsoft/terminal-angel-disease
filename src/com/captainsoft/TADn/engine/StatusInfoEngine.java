/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.engine;

import com.captainsoft.TADn.party.InventoryPlace;
import com.captainsoft.TADn.party.Party;
import com.captainsoft.TADn.party.PartyMember;
import com.captainsoft.spark.utils.Utils;

/**
 * 
 *
 * @author mathias fringes
 */
final class StatusInfoEngine {

	private final GameEngine gameEngine;
	
	public StatusInfoEngine(GameEngine gameEngine) {
		super();
		this.gameEngine = gameEngine;
	}	

	public void statusInfor() {
		Party party = gameEngine.party();
		switch (Utils.random(30)) {
			case 12:			
				PartyMember member = (party.member(Utils.random(1, 5)));
				if (member.isOverweight()) {
					gameEngine.say(member, "member.info.overweight");
				}
				break;
			case 13:
				member = (party.member(Utils.random(1, 5)));
				if (member.fun.cur() < 10) {
					gameEngine.say(member, "member.info.littleFun");
				}
				break;
			case 14:				
				member = (party.member(Utils.random(1, 5)));
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
	
}
