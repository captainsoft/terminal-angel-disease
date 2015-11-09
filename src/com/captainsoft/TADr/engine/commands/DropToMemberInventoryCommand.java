/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.engine.commands;

import java.util.List;

import com.captainsoft.TADr.model.item.Item;
import com.captainsoft.TADr.model.party.ItemPosition;
import com.captainsoft.TADr.model.party.PartyMember;
import com.captainsoft.spark.collect.Clist;

import static com.captainsoft.spark.utils.Utils.stringer;

/**
 * Drop the item in hand in one of the members inventory.
 * 
 * @author mathias fringes
 */
public final class DropToMemberInventoryCommand extends GameEngineCommand {

	// fields
	
	private final int memberNr;
	
	// constructors

	public DropToMemberInventoryCommand(int memberNr) {
		this.memberNr = memberNr;		
	}

	// Command

	public void execute() {
		Item item = gameEngine.itemInHand();
		if (item == null) {
			return;
		}
		//
		List<PartyMember> member = new Clist<PartyMember>(gameEngine.party().member(memberNr));
		ItemPosition pos = gameEngine.itemEngine().findSuitablePos(member, item);
		//
		if (pos != null) {
			gameEngine.party().inventory().set(pos, item);			
			gameEngine.mainViewer().toInventory(pos, item);	
			gameEngine.takeItem(null);
		}
	}

    // overridden
	
	@Override
	public String toString() {	
		return stringer("DropToMemberInventoryCommand", memberNr);
	}

}