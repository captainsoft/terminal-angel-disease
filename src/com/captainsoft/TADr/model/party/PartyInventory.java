/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.model.party;

import com.captainsoft.TADr.model.item.Item;

/**
 * The party inventory, a gateway to each of the party members
 * inventory.
 *
 * @author mathias fringes
 */
public final class PartyInventory {
	
	// fields
	
	private final Party party;
	
	// constructors
	
	public PartyInventory(Party party) {
		super();
		this.party = party;
	}
	
	// public
	
	public ItemPosition get(int itemNr) {	
		for (PartyMember member : party.members) {
			int index = member.inventory().findIndex(itemNr);
			if (index != Inventory.NO_INDEX) {
				return new ItemPosition(member, index); 
			}
		}		
		return null;				
	}

	public void set(ItemPosition itemPosition, Item item) {
        itemPosition.member.inventory().item(itemPosition.index, item);
	}	
	
	public boolean hasItem(int itemNr) {
		return (get(itemNr) != null);
	}

	public void delete(ItemPosition itemPosition) {
        itemPosition.member.setInventoryItem(itemPosition.index, null);
	}

}