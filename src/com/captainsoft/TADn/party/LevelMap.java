/*
 * Copyright Captainsoft 2011. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.party;

import com.captainsoft.TADn.model.Direction;
import com.captainsoft.TADn.model.Position;
import com.captainsoft.spark.utils.Utils;

/**
 * A level map.
 *
 * @author mathias fringes
 */
public final class LevelMap {
	
	// static
	
	public static final int X_LEN = 99;
	public static final int Y_LEN = 99;
      
    // fields

	private final int nr;   
	private final Tile[][] tiles = new Tile[X_LEN+1][Y_LEN+1];  // 0 rows, 0 columns are not used [1-99][1-99]
	
    private int secondOverlayStart = 0;
	private int tilesetNr = 0;
	  
    // constructors

    public LevelMap(int nr) {
    	super();
    	this.nr = nr;       
    }
    
    // accessors
    
    public int nr() {
    	return this.nr;
    }

	public int getSecondOverlayStart() {
		return this.secondOverlayStart;
    }

    public void setSecondOverlayStart(int secondOverlayStart) {
    	this.secondOverlayStart = secondOverlayStart;
    }	    
    
    public Tile tile(int x, int y) {
		return tiles[x][y];
	}	
    
    public Tile tile(Position pos) {
		return tiles[pos.x][pos.y];
	}		
	
	public void setTileAt(int x, int y, Tile tile) {
		this.tiles[x][y] = tile;		
	}

    public int getTilesetNr() {
        return tilesetNr;
    }
    
    public void setTilesetNr(int tilesetNr) {
		this.tilesetNr = tilesetNr;
	}
    
    public int fightBackgroundNr() {
    	return tile(11, 1).value(4);
    }    

    // public methods     

    public Position isPuzzleAround(Position position) {        
        int x = position.x;
        int y = position.y;
        Direction d = null;
        if (tile(x, y - 1).passivePuzzle() > 0) {
            d = Direction.North;
        }
        else if (tile(x + 1, y).passivePuzzle() > 0) {
        	d = Direction.East;        
        }
        else if (tile(x, y + 1).passivePuzzle() > 0) {
        	d = Direction.South;
        }
        else if (tile(x - 1, y).passivePuzzle() > 0) {
        	d = Direction.West;
        }
        return d == null ? null : position.apply(d);        
    }
  
    public boolean isCoffeeAutomaton(Position position) {
    	return (tile(position).value(1) == tile(3,1).value(4));        
    }

    public boolean isAutomatonLimo(Position position) {
    	return (tile(position).value(1) == tile(4,1).value(4));        
    }

    public boolean isAutomatonSweets(Position position) {
    	return (tile(position).value(1) == tile(5,1).value(4));        
    }

    public boolean isAutomatonInstant(Position position) {
    	return (tile(position).value(1) == tile(6,1).value(4));        
    }

	public int getSecondOverlay(Position position) {
		int ov = tile(position).overlay(); 
		return (ov >= secondOverlayStart) ? ov : 0;
	}            
	
	public int rndMonsterPartyId() {
		int index = Utils.random(39);		
		return (tiles[13 + index][1].value(4));
	}	

}
