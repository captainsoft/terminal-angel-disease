/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.painting.replacer;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.captainsoft.TADr.loader.ImageLoader;
import com.captainsoft.spark.collect.Clist;
import com.captainsoft.spark.ui.drawing.Surface;
import com.captainsoft.spark.ui.drawing.SurfaceSlicer;

/**
 * Creates tile groups for trees and other stuff by reading the 'special tree png'.
 * This holds the single instances for the TileGroups.
 *
 * @author mathias fringes
 */
final class ReplacerRepo {

    // fields

    private final Map<String, TileGroup> replaces = new HashMap<String, TileGroup>();

    private List<TileGroup> smokeList;

    // constructors

    public ReplacerRepo(ImageLoader imageLoader) {
        super();
        init(imageLoader);
    }

    // public

    public TileGroup get(String name) {
        return replaces.get(name);
    }

    public List<TileGroup> getSmokeTileGroups() {
        if (smokeList == null) {
            smokeList = Collections.unmodifiableList(new Clist<TileGroup>(
                    get("smoke1"),
                    get("smoke2"),
                    get("smoke3")));
        }
        return smokeList;
    }

    // private

    private void init(ImageLoader imageLoader) {
        Surface bigReplacementImage = imageLoader.load("tile", 21);
        SurfaceSlicer slicer = new SurfaceSlicer(bigReplacementImage, 40);
        //
        //
        // two regular trees
        Surface s1 = slicer.tile(1, 1, 2, 4);
        store("tree1", createTreeTypeAReplacement(s1));
        Surface s2 = slicer.tile(3, 1, 2, 4);
        store("tree2", createTreeTypeAReplacement(s2));
        //
        // small tree (1 tile ground)
        Surface s3 = slicer.tile(5, 1, 3, 4);
        store("tree3", createTreeTypeBReplacement(s3));
        Surface s4 = slicer.tile(8, 2, 1, 3);
        store("tree4", createTreeTypeCReplacement(s4));
        //
        // big trees
        Surface s5 = slicer.tile(1, 5, 3, 5);
        store("tree5", createTreeTypeDReplacement(s5));
        //
        Surface s7 = slicer.tile(8, 5, 2, 5);
        store("tree7", createTreeTypeFReplacement(s7));
        //
        // smaller red/purple tree
        Surface s6 = slicer.tile(4, 9, 2, 4);
        store("tree6", createTreeTypeAReplacement(s6));
        //
        //
        // streetlamp
        Surface s8 = slicer.tile(9, 2, 1, 3);
        store("lamp", createStreetlampReplacement(s8));
        // ... light
        Surface s8_2 = slicer.tile(8, 1, 1, 1);
        store("lamp_a", create1hOverlayReplacement(s8_2));
        //
        // smoke: one two three
        Surface smoke1 = slicer.tile(4, 5, 1, 3);
        store("smoke1", createSmokeReplacement(smoke1));
        Surface smoke2 = slicer.tile(5, 5, 1, 3);
        store("smoke2", createSmokeReplacement(smoke2));
        Surface smoke3 = slicer.tile(6, 5, 1, 3);
        store("smoke3", createSmokeReplacement(smoke3));
        //
        // single overlay torch	    	   
        Surface fackel1 = slicer.tile(7, 11, 2, 1);
        store("fackel", create2hOverlayReplacement(fackel1));
        //	  	  
        // teleporter (bright)
        Surface t1 = slicer.tile(6, 10, 3, 1);
        store("teleport", create3hOverlayReplacement(t1));
        // teleporter (dark)
        Surface t2 = slicer.tile(6, 12, 3, 1);
        store("teleport_d", create3hOverlayReplacement(t2));
        // ... (wooden)
        Surface tw1 = slicer.tile(4, 8, 3, 1);
        store("teleport_w", create3hOverlayReplacement(tw1));
        //
        // torches and wand lights
        Surface cancellier = slicer.tile(1, 12, 2, 1);
        store("cancellier", create2hOverlayReplacement(cancellier));
        Surface tb = slicer.tile(1, 10, 2, 1);
        store("torch_wall", create2hOverlayReplacement(tb));
        Surface wc = slicer.tile(1, 11, 2, 1);
        store("wall_chand", create2hOverlayReplacement(wc));
    }

    private TileGroup createTreeTypeAReplacement(Surface s) {
        TileGroup t = new TileGroup(s, 1, 4);
        t.setSecondOverlayTiles(1, 2, 3, 4, 5, 6);
        return t;
    }

    private TileGroup createTreeTypeBReplacement(Surface s) {
        TileGroup t = new TileGroup(s, 2, 4);
        t.setDontCares(3, 7, 9, 10, 12);
        t.setSecondOverlayTiles(1, 2, 4, 5, 6, 8);
        return t;
    }

    private TileGroup createTreeTypeCReplacement(Surface s) {
        TileGroup t = new TileGroup(s, 1, 3);
        t.setSecondOverlayTiles(1, 2);
        return t;
    }

    private TileGroup createTreeTypeDReplacement(Surface s) {
        TileGroup t = new TileGroup(s, 2, 5);
        t.setGroundTiles(14, 15);
        t.setDontCares(7, 10, 13);
        return t;
    }

    private TileGroup createTreeTypeFReplacement(Surface s) {
        TileGroup t = new TileGroup(s, 1, 5);
        t.setGroundTiles(9, 10);
        return t;
    }

    private TileGroup createStreetlampReplacement(Surface s) {
        TileGroup t = new TileGroup(s, 1, 3);
        t.setSecondOverlayTiles(1, 2);
        return t;
    }

    private TileGroup createSmokeReplacement(Surface s) {
        TileGroup t = new TileGroup(s, 1, 3);
        t.setSecondOverlayTiles(1, 2);
        t.setOverlayTiles(3);
        return t;
    }

    private TileGroup create1hOverlayReplacement(Surface s) {
        TileGroup t = new TileGroup(s, 1, 1);
        t.setOverlayTiles(1);
        return t;
    }

    private TileGroup create2hOverlayReplacement(Surface t1) {
        TileGroup t = new TileGroup(t1, 1, 1);
        t.setOverlayTiles(1, 2);
        return t;
    }

    private TileGroup create3hOverlayReplacement(Surface t1) {
        TileGroup t = new TileGroup(t1, 1, 1);
        t.setOverlayTiles(1, 2, 3);
        return t;
    }

    private void store(String name, TileGroup replacement) {
        replacement.name = name;
        replaces.put(name, replacement);
    }

}