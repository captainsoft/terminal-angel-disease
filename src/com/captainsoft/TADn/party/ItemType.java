/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.party;

import java.util.*;

/**
 * Describes special types of items.
 * 
 * @author mathias fringes
 */
public enum ItemType {
	
	// members
	
	Default(0),
	CurrentFunPoints(1),
	Weapon(7),
	Protection(13),
	HeadArmor(10),
	BodyArmor(11),
	FeetArmor(12),
	MagicSingle(20),
	MagicMultiple(21),
	MagicFireSingle(22), 
	MagicFireMultiple(23),
	MagicWaterSingle(24),
	MagicWaterMultiple(25),
	Shaolin(26),
	SlimeBomb(27),
	Book(40),
	Key(51),
	Puzzle(50),
	Teleporter(60),
	Chili(70),
	FunPointsMax(80),
	FitPointsMax(81),
	FoxPointsMax(82),
	;
	
	// statics
	private static Map<Integer, ItemType> lookup;
	
	static {
		lookup = new HashMap<Integer, ItemType>(250);
		for(ItemType it : ItemType.values()) {
			lookup.put(it.id(), it);
		}
	}
	
	public static ItemType byId(int id) {
		ItemType it = lookup.get(id);
		return (it != null) ? it : ItemType.Default; 
	}
	
	// fields
	
	private int id;	
	
	ItemType(int id) {
		this.id = id;		
	}
	
	// public methods
	
	public int id() {
		return this.id;
	}	
	
	public boolean isArmor() {
		return (this == Protection || this ==  HeadArmor || this == BodyArmor || this == FeetArmor);
	}
	
	public boolean isFightItem() {
		return (id >= 20 && id <= 27);
	}

	public boolean isPureMagic() {
		return (this == MagicSingle || this == MagicMultiple);
	}
	
	public boolean isFireMagic() {
		return (this == MagicFireSingle || this == MagicFireMultiple);
	}
	
	public boolean isWaterMagic() {
		return (this == MagicWaterSingle || this == MagicWaterMultiple);
	}
		
	public boolean isSingleMonsterMagic() {
		return (id == 20 || id == 22 || id == 24);
	}

	public boolean equals(Item item) {
		return ((item != null) && (item.typeId() == id));
	}

}
