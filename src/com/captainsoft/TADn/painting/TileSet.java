/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.painting;

import java.awt.Image;

import com.captainsoft.TADn.Debugger;
import com.captainsoft.TADn.loader.ImageLoader;
import com.captainsoft.TADn.model.Direction;
import com.captainsoft.spark.image.ImageTool;
import com.captainsoft.spark.ui.drawing.ImageSurface;
import com.captainsoft.spark.ui.drawing.Surface;
import com.captainsoft.spark.utils.Log;
import com.captainsoft.spark.utils.TransFilter;

/**
 * Handles the big images with lots of tiles for a map on it. 
 * Both loader and repository.
 *
 * @author mathias fringes
 */
public final class TileSet {
    
    // fields
    
	private static final int MAX_IMAGES = 266;
	
	private final Surface tiles[];
	
	private ImageLoader imageLoader;
    private int number;  

    // constructors
    
    /**
     * Default constructor. 
     * Does not load anything yet.
     *     
     * @param imageLoader
     */
    public TileSet(ImageLoader imageLoader) {
    	super();
    	this.imageLoader = imageLoader;
        this.number = -1;
        this.tiles = new Surface[MAX_IMAGES];
    }

    // public methods

    /**
     * Loads the tileset with the specified number.
     * 
     * @param number the number of the tileset. Starts with one (1).
     */
    public void load(int number) {
        // 
    	// dont load new tileset if it is the same.
        if (!Debugger.inst.on && this.number == number) {
        	Log.info("same tileset, nothing to load anew: " + number);
            return;
        }
        Log.info("loading tileset number: " + number);
        this.number = number;
        //
        // load the image...        
        Surface tileSetSurface =  imageLoader.load("tile", number);        
        //
        // ... and slice it up.        
        int tileIdx = 0;
        for (int y = 0; y < 14; y++) {
            for (int x = 0; x < 19; x++) {
                if ((tileIdx > 0) && (tileIdx < 200)) {
                    tiles[tileIdx] = tileSetSurface.stamp(x * 41, y * 41, 40, 40);
                } else {
                	Image image = ImageTool.createFiltered(tileSetSurface.stamp(x * 41, y * 41, 40, 40).image(), TransFilter.WHITE);                	                
                    tiles[tileIdx] = new ImageSurface(image, true);
                }
                tileIdx++;
            }
        }      
    }

    /**
     * Gets the image with the specified index. Indexes start with zero.
     * 
     * @param nr
     * @return
     */
    public Surface getTileImage(int nr) {
        return tiles[nr];
    }
        
    /**
     * Gets the tile image for the part(head or body) of the specified party member.
     * 
     * @param partyMemberNr the party member number (1-4).
     * @param dir the face direction.
     * @param top use 0 for bottom, 1 for top (head).
     * @return the image.
     */
    public Surface getPartyImage(int partyMemberNr, Direction dir, int top) {   
        if ((partyMemberNr == 4) && (dir == Direction.West) && (top == 1)) {        	
            return tiles[0];
        }
        return tiles[247 + partyMemberNr * 4 + (dir == Direction.East ? 0 : 1) * 2 + top];
    }
    
}
