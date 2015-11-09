/*
 * Copyright Captainsoft 2010-2012. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.engine;

import static com.captainsoft.TADn.party.ItemType.BodyArmor;
import static com.captainsoft.TADn.party.ItemType.CurrentFunPoints;
import static com.captainsoft.TADn.party.ItemType.FeetArmor;
import static com.captainsoft.TADn.party.ItemType.HeadArmor;
import static com.captainsoft.TADn.party.ItemType.Protection;
import static com.captainsoft.TADn.party.ItemType.Weapon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.captainsoft.TADn.TadRepo;
import com.captainsoft.TADn.model.TadTranslator;
import com.captainsoft.TADn.party.Inventory;
import com.captainsoft.TADn.party.InventoryPlace;
import com.captainsoft.TADn.party.Item;
import com.captainsoft.TADn.party.ItemPosition;
import com.captainsoft.TADn.party.ItemType;
import com.captainsoft.TADn.party.PartyMember;
import com.captainsoft.spark.i18n.TrKey;

/**
 *
 * @author mathias fringes
 */
public final class ItemEngine {
	
	// fields	
	
	private final TadTranslator translator; 
	private final GameEngine gameEngine;
	
	// constructors
	
	public ItemEngine(GameEngine gameEngine) {
		this.gameEngine = gameEngine;		
		translator = TadRepo.inst().Trans();
	}
	
	// public methods
	
	public void sayItemTakeString(Item item) {		
		ItemType type = item.type();
		String key = "item.info.type." + type.id();
		String text = "..";
		if (translator.contains(key)) {
			Map<String, String> data = new HashMap<String, String>();
			data.put("$itm", item.name());
			data.put("$p", item.value() + "");
			if (type.isArmor() || type == ItemType.Weapon) {
				data.put("$cp", (item.coins() / 2) + "");
				data.put("$suit", item.suitString());
			}
			text = translator.word(key, data);			
		} else {
			text = "." + translator.word("ifc.itemTaken", item.name());
		}		
		gameEngine.mainViewer().scrollCurrent(text);		
	}	
			
	public TrKey tryDropItem(ItemPosition position, Item item) {	
		if (position.place == InventoryPlace.Bag) {
			return null;
		}
		int nr = position.member.nr();
		boolean canDrop = canDropHere(position, item);
		if (!canDrop) {
			return new TrKey("item.take.wrongPlace", item.name()).variant(nr);
		}
		canDrop &= item.canWear(position.member.nr());		
		return (canDrop ? (null) : new TrKey("item.take.cannotUseIt", item.name()).variant(nr));
	}	
	
	public ItemPosition findSuitablePos(List<PartyMember> members, Item item) {
		//
		for(PartyMember member : members) {
			Inventory inventory = member.inventory();		
			for (InventoryPlace ip : InventoryPlace.allSpecialPlaces) {
				if (inventory.empty(ip.index())) {
					ItemPosition p = new ItemPosition(member, ip.index());
					if (tryDropItem(p, item) == null) {					
						return p;
					}				
				}
			}
		}
		//
		for(PartyMember member : members) {
			Inventory inventory = member.inventory();
			for (Integer pos : Inventory.bagPositions) {
				if (inventory.empty(pos)) {
					return new ItemPosition(member, pos);
				}
			}
		}
		//
		return null;
	}
	
	// private
		
	private boolean canDropHere(ItemPosition pos, Item item) {		
		InventoryPlace place = pos.place;
		if (place == InventoryPlace.Bag) {
			return true;
		}
		ItemType type = item.type();
		if (place.isBelt() && !canDropForBelt(type)) { return false; }
		if (place == InventoryPlace.Head && type != HeadArmor) { return false; }
		if (place == InventoryPlace.Body && type != BodyArmor) { return false; }
		if (place == InventoryPlace.Feet && type != FeetArmor) { return false; }
		if (place == InventoryPlace.Weapon && type != Weapon) { return false; }
		if (place == InventoryPlace.Protection && type != Protection) { return false; }		
		return true;
	}
	
	private boolean canDropForBelt(ItemType type) {
		return (type == CurrentFunPoints || type.isFightItem());
	}

}
