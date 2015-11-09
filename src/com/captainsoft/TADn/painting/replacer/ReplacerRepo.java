/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.painting.replacer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import com.captainsoft.spark.collect.Clist;
import com.captainsoft.spark.ui.drawing.ImageSurface;
import com.captainsoft.spark.ui.drawing.Surface;
import com.captainsoft.spark.ui.drawing.SurfaceSlicer;
import com.captainsoft.spark.utils.TransFilter;

/**
 * Creates tile groups for trees and other stuff.
 * 
 * @author mathias fringes
 */
final class ReplacerRepo {
	
	// fields
	 
	private final Map<String, TileGroup> replaces = new HashMap<String, TileGroup>();	
	private List<TileGroup> smokeList;
	
	// constructors
	
	public ReplacerRepo() {
		super();	
		init();
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
	
	public List<TileGroup> createFackelTileGroupList(Surface originalFackelImage) {
		TileGroup tg = new TileGroup(originalFackelImage);
		tg.setOverlayTiles(TransFilter.BLACK, 1);
		return new Clist<TileGroup>(tg,
				get("fackel1"),
				get("fackel2"));		
	}
		
	// private
	
	private void init() {		
		BufferedImage bigReplacementImage;
		try {
			bigReplacementImage = ImageIO.read(new File("files/res/java_fake_plastic_trees.png"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}	
	    SurfaceSlicer slicer = new SurfaceSlicer(ImageSurface.fromImage(bigReplacementImage), 40);
	    //
	    Surface s1 = slicer.slice(1, 1, 2, 4);
	    replaces.put("tree1", createTreeTypeAReplacement(s1));
	    Surface s2 = slicer.slice(3, 1, 2, 4);
	    replaces.put("tree2", createTreeTypeAReplacement(s2));
	    //
	    // small tree (1 tile ground)
	    Surface s3 = slicer.slice(5, 1, 3, 4);
	    replaces.put("tree3", createTreeTypeBReplacement(s3));
	    Surface s4 = slicer.slice(8, 2, 1, 3);
	    replaces.put("tree4", createTreeTypeCReplacement(s4));
	    //
	    // big trees
	    Surface s5 = slicer.slice(1, 5, 3, 5);
	    replaces.put("tree5", createTreeTypeDReplacement(s5));
	    //
	    Surface s6 = slicer.slice(4, 9, 2, 4);
	    replaces.put("tree6", createTreeTypeAReplacement(s6));
	    //
	    Surface s7 = slicer.slice(8, 5, 2, 5);
	    replaces.put("tree7", createTreeTypeFReplacement(s7));
	    //
	    // streetlamp
	    Surface s8 = slicer.slice(9,2,1,3);
	    replaces.put("lamp", createTreeTypeCReplacement(s8));
	    //
	    // smoke: one two three
	    Surface smoke1 = slicer.slice(4, 5, 1, 3);
	    replaces.put("smoke1", createSmokeReplacement(smoke1));		    
	    Surface smoke2 = slicer.slice(5, 5, 1, 3);
	    replaces.put("smoke2", createSmokeReplacement(smoke2));		    
	    Surface smoke3 = slicer.slice(6, 5, 1, 3);
	    replaces.put("smoke3", createSmokeReplacement(smoke3));
	    //
	    // torch
	    Surface fackel1 = slicer.slice(7, 11, 1, 1);
	    Surface fackel2 = slicer.slice(8, 11, 1, 1);	    
	    replaces.put("fackel1", createFackelReplacement(fackel1));
	    replaces.put("fackel2", createFackelReplacement(fackel2));	    
	    //
	    // empty
	    Surface empty = slicer.slice(11, 2, 1, 1);
	    replaces.put("empty", createEmptyReplacement(empty));
	}		

	private TileGroup createEmptyReplacement(Surface s) {
		TileGroup t = new TileGroup(s, 1, 1);
		// t.setSecondOverlayTiles(1);
		t.setDontCares(1); // TODO doch richtig?
		return t;
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
		t.setDontCares(7,10,13);
		return t;
	}
	
	private TileGroup createTreeTypeFReplacement(Surface s) {
		TileGroup t = new TileGroup(s, 1, 5);		
		t.setGroundTiles(9, 10);		
		return t;
	}
	
	private TileGroup createSmokeReplacement(Surface s) {
		TileGroup t = new TileGroup(s, 1, 3);
		t.setSecondOverlayTiles(1, 2);
		t.setOverlayTiles(3);
		return t;
	}	
	
	private TileGroup createFackelReplacement(Surface s) {
		TileGroup t = new TileGroup(s, 1, 1);		
		t.setOverlayTiles(1);
		return t;
	}	
		
}