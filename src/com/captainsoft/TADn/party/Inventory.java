/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.party;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The inventory for one party member.
 *
 * @author mathias fringes
 */
public final class Inventory {

    // static members

    public static final int CAPACITY = 29;   
    static final int NO_INDEX = -1;
 
    public static List<Integer> bagPositions = new ArrayList<Integer>();
    
    static {
    	for (int i = 0; i < 20; i++) {
			bagPositions.add(i);			
		}
		bagPositions = Collections.unmodifiableList(bagPositions);
    }
    
    // fields
    
    private int weight;
    private Item[] items;    

    // constructors

    public Inventory() {
    	super();
        this.items = new Item[CAPACITY];
        this.weight = 0;
    }     

    // accessors
    
    public Item item(int index) {
        return items[index];
    }
    
    public Item item(InventoryPlace pos) {
    	return item(pos.index());
    }
        
    public void item(InventoryPlace pos, Item item) {
    	item(pos.index(), item);
    }

    public void item(int index, Item item) {
    	remove(index);
        items[index] = item;  
        if (item != null) {
        	weight += item.weight();
        }
    }       
    
    public int weight() {
    	return weight;
    }
    
    // public methods  
    
    public boolean empty(int index) {
		return (items[index] == null);
	}
    
    public int findIndex(int item) {
    	for (int index = 0; index < items.length; index++) {
            if (items[index] != null && items[index].id() == item) {
                return index;
            }
        }
        return NO_INDEX;
    }               
           
	public void remove(int index) {
		 if (items[index] != null) {
			 weight -= items[index].weight();
			 items[index] = null;
		 }
	}
	
}