/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.painting;

import static com.captainsoft.spark.utils.Utils.stringer;

import java.awt.Image;
import java.awt.image.RGBImageFilter;

import com.captainsoft.TADr.engine.debug.Debugger;
import com.captainsoft.TADr.loader.ImageLoader;
import com.captainsoft.TADr.model.Direction;
import com.captainsoft.spark.image.ImageTool;
import com.captainsoft.spark.ui.drawing.Surface;
import com.captainsoft.spark.ui.drawing.SurfaceSlicer;
import com.captainsoft.spark.utils.*;

/**
 * Handles the big images with lots of tiles for a map on it.
 * Both loader and repository.
 *
 * @author mathias fringes
 */
public final class TileSet {

    // fields

    private static final int OVERLAY_START = 200;
    private static final int MAX_TILES = 251;

    private final ImageLoader imageLoader;
    private final Surface party[];
    private final Surface tiles[];

    private int number;

    // constructors

    /**
     * Default constructor.
     * Does not load anything yet except for the party.
     *
     * @param imageLoader
     */
    public TileSet(ImageLoader imageLoader) {
        super();
        this.imageLoader = imageLoader;
        this.number = -1;
        this.tiles = new Surface[MAX_TILES];
        this.party = new Surface[16];
        loadParty();
    }

    // public

    /**
     * Loads the tileset with the specified number.
     *
     * @param number the number of the tileset. Starts with one (1).
     */
    public void load(int number) {
        // 
        // don't load new tileset if it is the same.
        if (this.number == number && !Debugger.Inst.on) {
            Log.info("same tileset, nothing to load anew: " + number);
            return;
        }
        Log.info("loading tileset number: " + number);
        this.number = number;
        //
        // load the image...        
        Surface tileSetSurface = imageLoader.load("tile", number);
        //
        // ... and tile it up.
        SurfaceSlicer ss = new SurfaceSlicer(tileSetSurface, 40, 1);
        int tileIdx = 0;
        for (Point p : PointBox.create(19, 14)) {
            Surface tile = ss.tile(p.x, p.y);

            if (tileIdx > 0 && tileIdx < MAX_TILES) {
                if (tileIdx < OVERLAY_START) {
                    tiles[tileIdx] = tile;
                } else {
                    tiles[tileIdx] = trans(tile);
                }
            }
            tileIdx++;
        }
    }

    /**
     * Gets the image with the specified index. Indexes start with one.
     *
     * @param nr
     * @return
     */
    public Surface getTileImage(int nr) {
        return tiles[nr];
    }

    /**
     * Gets the tile image for the part (head or body) of the specified party member.
     *
     * @param partyMemberNr the party member number (1-4).
     * @param dir           the face direction.
     * @param top           use 0 for bottom, 1 for top (head).
     * @return the image.
     */
    public Surface getPartyImage(int partyMemberNr, Direction dir, int top) {
        if ((partyMemberNr == 4) && (dir == Direction.West) && (top == 1)) {
            return party[0];
        }
        return party[1 + (partyMemberNr - 1) * 4 + (dir == Direction.East ? 0 : 1) * 2 + top];
    }

    // private

    private void loadParty() {
        Surface partySurface = imageLoader.load("tile", 22);
        SurfaceSlicer ss = new SurfaceSlicer(partySurface, 40, 1);
        //
        for (int i = 0; i < 16; i++) {
            party[i] = trans(ss.tile(i + 1, 1), TileRosaTransparency.Instance);
        }
    }

    private Surface trans(Surface origin) {
        return trans(origin, TransFilter.WHITE);
    }

    private Surface trans(Surface origin, RGBImageFilter filter) {
        Image image = ImageTool.createFiltered(origin.image(), filter);
        return new Surface(image, true);
    }

    // overridden

    @Override
    public String toString() {
        return stringer("TileSet", number);
    }

}