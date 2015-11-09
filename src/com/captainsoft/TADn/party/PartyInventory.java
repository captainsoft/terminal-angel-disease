/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.party;

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
	
	// public methods
	
	public ItemPosition get(int itemNr) {	
		for (PartyMember member : party) {
			int index = member.inventory().findIndex(itemNr);
			if (index != Inventory.NO_INDEX) {
				return new ItemPosition(member, index); 
			}
		}		
		return null;				
	}

	public void set(ItemPosition pos, Item item) {
		pos.member.inventory().item(pos.index, item);
	}	
	
	public boolean hasItem(int itemNr) {
		return (get(itemNr) != null);
	}

	public void delete(ItemPosition itemPosition) {
		party.member(itemPosition.member.nr()).setInventoryItem(itemPosition.index, null);		
	}
}