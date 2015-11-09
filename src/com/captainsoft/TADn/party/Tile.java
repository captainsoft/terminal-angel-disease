/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.party;

import static com.captainsoft.spark.utils.Utils.*;

/**
 * A tile.
 * 
 * @author Mathias Fringes
 */
public final class Tile {
	
	// fields
		
	private int value1 = 0;
	private int value2 = 0;
	private int value3 = 0;
	private int value4 = 0;
	
	// constructors
	
	public Tile() {
		this(0, 0, 0, 0);
	}	
	
	public Tile(int val1, int val2, int val3, int val4) {
		super();
		this.value1 = val1;
		this.value2 = val2;
		this.value3 = val3;
		this.value4 = val4;
	}
	
	// accessors
	
	public void set(int number, int value) {
		switch (number) {
			case 1:
				value1 = value;
				break;
			case 2:
				value2 = value;
				break;
			case 3:
				value3 = value;
				break;
			case 4:
				value4 = value;
				break;
			default:
				throw new RuntimeException("Cannot set index (1-4) from " + number);					
		}		
	}	
		
	public int value(int number) {
		switch (number) {
			case 1:
				return value1;				
			case 2:
				return value2;				
			case 3:
				return value3;				
			case 4:
				return value4;				
			default:
				throw new RuntimeException("Cannot set index (1-4) from " + number);					
		}
	}

	public int ground() {
		return value1;
	}	

	public int item() {
		return value2;
	}
	
	public void item(int id) {
		value2 = id;
	}
	
	public boolean hasItem() {
		return (value2 > 0);
	}
	
	public void removeItem() {		
		value2 = 0;
	}
	
	public int passivePuzzle() {        
        return (value3 >= 1 && value3 <= 139) ? value3 : 0;        
    }

    public int activePuzzle() {        
        return (value3 >= 140 && value3 <= 199) ? value3 : 0;            
    }
	
	public int overlay() {
		return (value3 >= 200) ? value3 : 0;
	}
	
	public boolean dropable() {		
		return (value4 != 1 && value4 != 101 && value4 != 201 && value4 != 251);		
	}
	
	public boolean walkable() {
		return (value4 != 250 && value4 != 251);
	}	
	
	public int speech() {
		return (value4 >= 2 && value4 <= 99) ? value4 : 0;		
	}
	
	// public methods
	
	public TileDanger danger() {		
		if (value4 == 200 || value4 == 201) {
			return TileDanger.High;
		} else if (value4 == 100 || value4 == 101) {
			return TileDanger.Low;
		}
		return TileDanger.None;
	}
	
	public void redanger() {
		if (value4 == 200 || value4 == 201) {
			value4 -= 100;
		}
	}

	// overridden methods
	
	@Override
	public String toString() {
		return stringer("Tile", value1, value2, value3, value4);			
	}
	
}