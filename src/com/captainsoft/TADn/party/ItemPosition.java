/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.party;

import static com.captainsoft.spark.utils.ExcUtils.*;
import static com.captainsoft.spark.utils.Utils.*;

/** 
 * Describes a concrete place of an item in a party member's inventory.
 *  
 * @author mathias fringes
 */
public final class ItemPosition {
	
	// fields
	
	public final int index;
	public final InventoryPlace place;
	public final PartyMember member;

	// constructors
	
	public ItemPosition(PartyMember member, int index) {
		super();
		argNotNull("member", member);
		argZeroPositive("index", index);		
		//
		this.member = member;
		this.index = index;
		this.place = InventoryPlace.byIndex(index);
	}

	// public methods
	
	public boolean equals(ItemPosition itemPosition) {
		if (itemPosition == null) {
			return false;
		}
		if (itemPosition == this) {
			return true;
		}
		return ((index == itemPosition.index) && (member.nr() == itemPosition.member.nr()));
	}
	
	// overridden methods
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + index;
		result = prime * result + ((member == null) ? 0 : member.nr());
		return result;
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof ItemPosition) {
			return equals((ItemPosition)object);
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return stringer("ItemPosition", member.nr(), index);		
	}
		
}
