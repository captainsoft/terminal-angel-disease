/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.model.party;

import java.util.*;

import static com.captainsoft.spark.utils.Utils.stringer;

/**
 * Specific inventory index positions. Does not include the
 * default Bag position.
 * 
 * @author mathias fringes
 */
public enum InventoryPlace {
	
	// members
	
	Head(20),
	Body(21),
	Feet(22),
	Weapon(23),
	Protection(25),
	Belt1(24),
	Belt2(26),
	Belt3(27),
	Belt4(28),	
	Bag(-1);
	
	// statics
		
	public static List<InventoryPlace> equiqPositions = new ArrayList<InventoryPlace>();
	public static List<InventoryPlace> beltPositions = new ArrayList<InventoryPlace>();	
	public static List<InventoryPlace> armorPositions = new ArrayList<InventoryPlace>();	
		
	public static List<InventoryPlace> allSpecialPlaces = new ArrayList<InventoryPlace>();
	
	private static HashMap<Integer, InventoryPlace> allIndexes = new HashMap<Integer, InventoryPlace>();
	
	static {							
		Collections.addAll(equiqPositions, Head, Body, Feet, Weapon, Protection);
		equiqPositions = Collections.unmodifiableList(equiqPositions);	
		
		Collections.addAll(armorPositions, Head, Body, Feet, Protection);
		armorPositions = Collections.unmodifiableList(armorPositions);	
		
		Collections.addAll(beltPositions, Belt1, Belt2, Belt3, Belt4);
		beltPositions = Collections.unmodifiableList(beltPositions);
	
		
		Collections.addAll(allSpecialPlaces, InventoryPlace.values());
		allSpecialPlaces.remove(Bag);
		
		for (InventoryPlace ip : InventoryPlace.values()) {
            allIndexes.put(ip.index(), ip);
        }
	}
	
	public static InventoryPlace byIndex(int index) {
		InventoryPlace pos = allIndexes.get(index);
		return ((pos != null) ? pos : InventoryPlace.Bag);
	}
	
	// fields
	
	private final int index;
	
	// constructors
	
	InventoryPlace(int index) {
		this.index = index;		
	}
	
	// public
	
	public int index() {
		return index;
	}
	
	public boolean isBelt() {
		return beltPositions.contains(this);
	}
	
	// overridden
	
	@Override
	public String toString() {
        return stringer("InventoryPos", name(), index);
	}

}
