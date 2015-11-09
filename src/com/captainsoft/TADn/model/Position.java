/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.model;

/**
 * A 2D-position, and it is immutable! New Objects are created when calling
 * value changing methods!
 *
 * @author mathias fringes
 */
public final class Position {
	
	// fields
	
	public final int x;
	public final int y;
	
	// constructors
	
	public Position() {
		this(0, 0);
	}
	
	public Position(Position position) {
		this(position.x, position.y);		
	}
	
	public Position(int x, int y) {
		super();
		this.x = x;
		this.y = y;			
	}	
	
	// public methods		
	
	public Position add(Position p) {
		return add(p.x, p.y);
	}
	
	public Position add(int x, int y) {
		return new Position(this.x + x, this.y + y);
	}
	
	public Position addX(int x) {
		return add(x, 0);
	}
	
	public Position addY(int y) {
		return add(0, y);
	}	
	
	public Position apply(Direction d) {
		switch (d) {
			case East:
				return addX(1);				
			case North:
				return addY(-1);				
			case South:
				return addY(1);				
			case West:
				return addX(-1);
			default:
				return this;					
		}		
    }	

	public Position subst(Position p) {
		return add(-p.x, -p.y);		
	}
			 
    public boolean isNextTo(Position position) {    	
    	int x1 = position.x;
    	int y1 = position.y;    	       	    	
    	if ((x == x1) && (y == y1)) {
    		return false;    	    
    	} 
    	return (x >= x1-1) && (x <= x1+1) && (y >= y1-1) && (y <= y1+1);    	       
	}
    
    public Direction findDir(Position position) {
    	int px = position.x;
    	int py = position.y;
    	if (x == px && y == py) {
    		return Direction.Here;
    	}
    	if ((x == px) && (y - 1 == py)) {
    		return Direction.North;
    	}
    	if ((x == px) && (y + 1 == py)) {
    		return Direction.South;
    	}
    	if ((x - 1 == px) && (y == py)) {
    		return Direction.West;
    	}
    	if ((x + 1 == px) && (y == py)) {
    		return Direction.East;
    	}
    	return Direction.Nowhere;
    }
    
    public boolean inside(int x1, int y1, int x2, int y2) {
    	return ((x >= x1) && (x <= x2) && (y >= y1) && (y <= y2)); 
    }		
    
    public boolean equals(int x, int y) {
    	return ((this.x == x) && (this.y == y));
    }
  
    public boolean equals(Position position) {
    	if (position == null) {
    		return false;
    	}
    	if (position == this) {
    		return true;
    	}
    	return equals(position.x , position.y);
    }
	
	// overridden methods  
    
	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		if (object == this) {
			return true;
		}
		if (object instanceof Position == false) {
			return false;
		}
		Position p = (Position)object;		
		return (equals(p));
	}
	
	@Override
	public int hashCode() {
		return x ^ y;
	}
		
	@Override
	public String toString() {		
		return "(" + x + "/" + y + ")";
	}

}
