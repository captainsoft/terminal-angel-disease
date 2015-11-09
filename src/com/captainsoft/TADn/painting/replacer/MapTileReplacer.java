/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.painting.replacer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.captainsoft.TADn.Debugger;
import com.captainsoft.TADn.model.Position;
import com.captainsoft.TADn.painting.TileSet;
import com.captainsoft.TADn.painting.animations.AnimationList;
import com.captainsoft.TADn.painting.animations.TileGroupAnimation;
import com.captainsoft.TADn.party.LevelMap;
import com.captainsoft.TADn.party.LevelMapPositionEnumeration;
import com.captainsoft.TADn.party.Tile;
import com.captainsoft.TADn.party.TileDanger;
import com.captainsoft.spark.collect.Cset;
import com.captainsoft.spark.collect.MultiMap;
import com.captainsoft.spark.ui.drawing.Surface;

/** 
 * Holds a set of replacement and additional tiles for the current map.
 * 
 * @author mathias fringes
 */
public final class MapTileReplacer {
	
	// fields
	
	private final Cset<Integer> removeTiles;	
	private final List<TileGroupImage> emptyList = Collections.unmodifiableList(new ArrayList<TileGroupImage>(0));
	private final MultiMap<Position, TileGroupImage> replaceInfos;
	private final TileSet tileSet;
	
	private LevelMap levelMap;
	private ReplacerRepo repo;
	
	// constructors
	
	public MapTileReplacer(TileSet tileSet) {
		super();
		this.tileSet = tileSet;
		//
		this.removeTiles = new Cset<Integer>();	
		this.replaceInfos = new MultiMap<Position, TileGroupImage>();
		this.repo = new ReplacerRepo();
	}
	
	// accessors	
	
	public void LevelMap(LevelMap levelMap, AnimationList animationList) {
		if (Debugger.inst.on) {
			this.repo = new ReplacerRepo();
		}
		this.levelMap = levelMap;		
		animationList.clear();
		replaceInfos.clear();
		//
		// set up replace tiles
		removeTiles.clear();
		Map<Integer, TileGroup> replaceMap = new HashMap<Integer, TileGroup>(); 
		if (levelMap.nr() == 1 || levelMap.nr() == 2 || levelMap.nr() == 3) {
			replaceMap.put(152, repo.get("tree3"));
			replaceMap.put(153, repo.get("tree1"));
			replaceMap.put(155, repo.get("tree2"));
			replaceMap.put(187, repo.get("lamp"));	
			//
			removeTiles.add(152,153,154,155,156,246,247,248,249,250);
			removeTiles.add(187,236);
		}			
		if (levelMap.nr() == 3) {
			replaceMap.put(166, repo.get("tree4"));
			replaceMap.put(162, repo.get("tree5"));
			//
			removeTiles.add(166,232);
			removeTiles.add(162,163,243,240);
		}
		if (levelMap.nr() >= 6 && levelMap.nr() <= 9)  {
			replaceMap.put(72, repo.get("tree1"));
			replaceMap.put(67, repo.get("tree2"));
			replaceMap.put(57, repo.get("tree3"));
			replaceMap.put(71, repo.get("tree4"));
			replaceMap.put(74, repo.get("tree5"));			
			replaceMap.put(58, repo.get("tree6"));
			replaceMap.put(60, repo.get("tree7"));		
			replaceMap.put(140, repo.get("tree6"));
			//
			removeTiles.add(57,58,59,60,61,69,61,67,68,71,72,73,74,75,140,141);
			removeTiles.add(232,234,235,236,237,240,241,242,243,244,245,246,247,248,249,250);
		}
		if (levelMap.nr() == 9) {
			replaceMap.put(104, repo.get("lamp"));
			//
			removeTiles.add(104);
			removeTiles.add(233);
		}
		if (levelMap.nr() == 10 || levelMap.nr() == 11) {
			replaceMap.put(165, repo.get("tree1"));
			replaceMap.put(167, repo.get("tree6"));
			replaceMap.put(169, repo.get("tree5"));
			//
			removeTiles.add(165,166,167,168,169,170);
		}
		if (levelMap.nr() == 18) {
			replaceMap.put(42, repo.get("tree3"));
			replaceMap.put(47, repo.get("tree6"));
			replaceMap.put(45, repo.get("tree1"));
			replaceMap.put(43, repo.get("tree2"));
			replaceMap.put(51, repo.get("tree4"));
			replaceMap.put(52, repo.get("tree5"));
			replaceMap.put(54, repo.get("tree7"));
			//
			removeTiles.add(42,43,44,45,46,47,48,51,52,53,54,55);
			removeTiles.add(213,215,216,217,218,221,222,227,229,230,231,232,223,224,225,226);
		}
		createReplacementsTilesOnMap(replaceMap, animationList);
		//
		//
		// special animations....
		// fire
		// TODO  code duplication !? 
		if (levelMap.nr() == 1) {	
			int fackelNr = 206;
			Surface fackelSurface = tileSet.getTileImage(fackelNr);
			List<TileGroup> fackelList = repo.createFackelTileGroupList(fackelSurface);			
			removeTiles.add(fackelNr);
			Enumeration<Position> lme = new LevelMapPositionEnumeration();
			while (lme.hasMoreElements()) {
				Position p = lme.nextElement();
				Tile t = levelMap.tile(p);
				if (t.overlay() == fackelNr) {												 										 		
					TileGroupAnimation tg = new TileGroupAnimation(fackelList);					
					replaceInfos.putl(p, tg.image(0));
					tg.setPosition(p);
					animationList.set(p, tg);
				}
			}
		}
		//
		//
		replaceInfos.trimAllToSize();
	}
	
	// public
		
	public List<TileGroupImage> getReplaceImages(Position position) {		
		List<TileGroupImage> r = replaceInfos.get(position);
		return (r == null) ? emptyList : r;		 
	}
	
	public boolean shouldDraw(int tileNr) {
		boolean shouldDraw = true;
		boolean containedInRemoveTiles = removeTiles.contains(tileNr);
		shouldDraw = !containedInRemoveTiles;			
		return (shouldDraw);
	}	

	// private

	private void createReplacementsTilesOnMap(Map<Integer, TileGroup> replacements, AnimationList animations) {			
		Enumeration<Position> lme = new LevelMapPositionEnumeration();
		while (lme.hasMoreElements()) {
			Position p = lme.nextElement();			
			Tile t = levelMap.tile(p);			
			int ground = t.ground();
			//
			// replace tiles
			TileGroup g = replacements.get(ground);
			if (g != null) {
				createReplacement(p, g);
			}
			//
			// additional tiles 
			// danger
			if (t.danger() == TileDanger.High) {
				createAnimationSmokeReplacement(animations, p);				
			}
		}		
	}
		
	private void createAnimationSmokeReplacement(AnimationList animations, Position p) {		
	    int i = 0;
	    TileGroupAnimation tga = new TileGroupAnimation(repo.getSmokeTileGroups());
	    tga.setPosition(p);
	    for(Position realPosition : tga.positions()) {
	    	replaceInfos.putl(realPosition, tga.image(i));
			animations.set(realPosition, tga);
			i++;
	    }
	}

	private void createReplacement(Position p, TileGroup r) {
		for (int x = 1; x <= r.x_span; x++) {
			for (int y = 1; y <= r.y_span; y++) {
				TileGroupImage img = r.infoAt(x, y);
				if (img != null) {
					Position pl = new Position(x - r.anchor_x, y - r.anchor_y);
					replaceInfos.putl(p.add(pl), img);
				}
			}
		}
	}

}
