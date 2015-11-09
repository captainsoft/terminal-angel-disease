/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.painting.replacer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.captainsoft.TADr.engine.debug.Debugger;
import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.TADr.loader.ImageLoader;
import com.captainsoft.TADr.model.Position;
import com.captainsoft.TADr.model.map.LevelMap;
import com.captainsoft.TADr.model.map.LevelMapPositionEnumeration;
import com.captainsoft.TADr.model.map.Tile;
import com.captainsoft.TADr.model.map.TileDanger;
import com.captainsoft.TADr.painting.TileSet;
import com.captainsoft.TADr.painting.animations.MapAnimationList;
import com.captainsoft.TADr.painting.animations.TileGroupAnimation;
import com.captainsoft.TADr.painting.animations.TileImageAnimation;
import com.captainsoft.spark.collect.Cset;
import com.captainsoft.spark.collect.MultiMap;
import com.captainsoft.spark.ui.drawing.Surface;
import com.captainsoft.spark.utils.Point;
import com.captainsoft.spark.utils.PointBox;
import com.captainsoft.spark.utils.Utils;

/** 
 * Sets up and holds a set of replacement, additional tiles, and animations for the current map.
 * 
 * @author mathias fringes
 */
public final class MapTileReplacer {
	
	// fields
	
	private static final List<TileGroupImage> emptyList = Collections.unmodifiableList(new ArrayList<TileGroupImage>(0));
	
	private final Cset<Integer> removeTiles;	
	private final MultiMap<Position, TileGroupImage> replaceImagesOnPosition;	
	private final TileSet tileSet;
	
	private Map<Integer, TileGroup> tileRemoveReplacements;	
	private ReplacerRepo repo;
	
	// constructors
	
	public MapTileReplacer(TileSet tileSet, ImageLoader imageLoader) {
		super();
		this.tileSet = tileSet;
		//
		removeTiles = new Cset<Integer>();	
		replaceImagesOnPosition = new MultiMap<Position, TileGroupImage>();		
		repo = new ReplacerRepo(imageLoader);
	}
	
	// accessors	
	
	public void setup(LevelMap levelMap, MapAnimationList animationList) {
		if (Debugger.Inst.loadTilesDirect) {
			repo = new ReplacerRepo(TadRepo.inst().ImageLoader());
		}	
		animationList.clear();
		replaceImagesOnPosition.clear();		
		removeTiles.clear();
		//
		// set up replace tiles		
		tileRemoveReplacements = new HashMap<Integer, TileGroup>(); 
		if (levelMap.nr() == 1 || levelMap.nr() == 2 || levelMap.nr() == 3) {
			replace(152, "tree3");
			replace(153, "tree1");
			replace(155, "tree2");
			replace(187, "lamp");	
			//
			remove(152,153,154,155,156,246,247,248,249,250);
			remove(187,236);
		}			
		if (levelMap.nr() == 3) {
			replace(166, "tree4");
			replace(162, "tree5");
			//
			remove(166,232);
			remove(162,163,243,240);
		}
		if (levelMap.nr() >= 6 && levelMap.nr() <= 9)  {
			replace(72, "tree1");
			replace(67, "tree2");
			replace(57, "tree3");
			replace(71, "tree4");
			replace(74, "tree5");			
			replace(58, "tree6");
			replace(60, "tree7");		
			replace(140, "tree6");
			//
			remove(57,58,59,60,61,69,61,67,68,71,72,73,74,75,140,141);
			remove(232,234,235,236,237,240,241,242,243,244,245,246,247,248,249,250);
		}
		if (levelMap.nr() == 9) {
			replace(104, "lamp");
			//
			remove(104, 233);			
		}
		if (levelMap.nr() == 10 || levelMap.nr() == 11) {
			replace(165, "tree1");
			replace(167, "tree6");
			replace(169, "tree5");
			//
			remove(165,166,167,168,169,170);
		}
		if (levelMap.nr() == 18) {
			replace(42, "tree3");
			replace(47, "tree6");
			replace(45, "tree1");
			replace(43, "tree2");
			replace(51, "tree4");
			replace(52, "tree5");
			replace(54, "tree7");
			//
			remove(42,43,44,45,46,47,48,51,52,53,54,55);
			remove(213,215,216,217,218,221,222,227,229,230,231,232,223,224,225,226);
		}
		
		//
		//
		// plant special animations....
		
		List<MapPosAnimation> replacers = new ArrayList<MapTileReplacer.MapPosAnimation>();
		switch(levelMap.tileset()) {
			case 1:
				replacers.add(new FackelAnimation(206));	
				replacers.add(new StreetLampAnimation(187));				
				break;
			case 2:
				replacers.add(new FackelAnimation(206));	
				replacers.add(new StreetLampAnimation(187));				
				replacers.add(new TeleporterAnimation(170, "teleport"));				
				break;
			case 3:
				replacers.add(new FackelAnimation(206));	
				replacers.add(new StreetLampAnimation(187));
				break;
			case 4:
				replacers.add(new FackelAnimation(246));
				replacers.add(new TestWandChancellorAnimation(55));						
				replacers.add(new TeleporterAnimation(31, "teleport_d"));
				break;
			case 6:
				replacers.add(new TeleporterAnimation(66, "teleport_w"));				
				replacers.add(new FackelAnimation(203));
				break;
			case 9:
				replacers.add(new FackelAnimation(203));
				replacers.add(new StreetLampAnimation(104));		
				break;
			case 10:		
				replacers.add(new WandTorchAnimation(109));
				replacers.add(new WandChancellorAnimation(35));
				replacers.add(new TestWandChancellorAnimation(105));				
				break;
			case 12:
				replacers.add(new WandTorchAnimation(109));
				replacers.add(new TestWandChancellorAnimation(105));
				replacers.add(new TeleporterAnimation(94, "teleport_d"));				
				break;
			case 13:
				replacers.add(new WandTorchAnimation(109));
				replacers.add(new TestWandChancellorAnimation(105));
				replacers.add(new TeleporterAnimation(18, "teleport_d"));				
				break;
			case 17:
				replacers.add(new WandTorchAnimation(109));
				replacers.add(new TestWandChancellorAnimation(105));
				break;
			case 18:				
				replacers.add(new TeleporterAnimation(64, "teleport_d"));
				break;
			case 19:
				break;
			case 20:
				break;
			default:
				break;
		}		
		//
		createReplacementsTilesOnMap(levelMap, animationList, replacers);
		//
		replaceImagesOnPosition.trimAllToSize();		
		tileRemoveReplacements.clear();
	}

	private void remove(Integer ... numbers) {
		removeTiles.add(numbers);
	}
	
	private void replace(int number, String replace) {
		tileRemoveReplacements.put(number, repo.get(replace));
	}
		
	// public
		
	public List<TileGroupImage> getReplaceImages(Position position) {		
		List<TileGroupImage> r = replaceImagesOnPosition.get(position);	
		return (r == null ) ? emptyList : r;				 
	}
	
	public void deleteDanger(Position p) {
			 
		for (int y = 0; y < 3; y++) {
			List<TileGroupImage> l = replaceImagesOnPosition.get(p.addY(-y));
			if (l == null) {
				return ;
			}
			Iterator<TileGroupImage> iter = l.iterator();
			while(iter.hasNext()) {
				TileGroupImage i = iter.next();
				if (i instanceof TileGroupImageProxy) {
					iter.remove();
				}
			}
		}
	}
	
	public boolean shouldDraw(int tileNr) {					
		return !removeTiles.contains(tileNr);
	}	
	
	public String whatIsOn(Position position) {	
		String s = "";
		for (TileGroupImage img : getReplaceImages(position) ) {
			s += " " + img;
		}			
		return s;
	}

	// private		
	
	private void createReplacementsTilesOnMap(LevelMap levelMap, MapAnimationList animations, List<MapPosAnimation> replacerList) {
		
		for (Position p : new LevelMapPositionEnumeration()) {				
			Tile t = levelMap.tile(p);			
			int ground = t.ground();
			//
			// replace tiles
			TileGroup g = tileRemoveReplacements.get(ground);
			if (g != null) {
				createReplacement(p, g);
			}
			//
			// additional tiles 
			// danger
			if (t.danger() == TileDanger.High) {
				createAnimationSmokeReplacement(animations, p);
			}
			//
			// plant animations
			for(MapPosAnimation mr : replacerList) {
				if (mr.doit(t)) {
					TileImageAnimation animation = mr.createAnimation(p);
					replaceImagesOnPosition.putl(animation.position, animation.image());					
					animations.add(animation.position, animation);	
				}				
			}
		}		
	}
		
	private void createAnimationSmokeReplacement(MapAnimationList animations, Position p) {
		
	    int i = 0;
	    TileGroupAnimation tga = new TileGroupAnimation(repo.getSmokeTileGroups(), "smoke");
	    tga.setPosition(p);
	    tga.speed = 170;
	    
	    for(Position realPosition : tga.positions()) {
	    	replaceImagesOnPosition.putl(realPosition, tga.image(i));
			animations.add(realPosition, tga);
			i++;
	    }
	}

	private void createReplacement(Position p, TileGroup r) {		

		for (Point pt : PointBox.create(Point.one, r.x_span, r.y_span)) {

			TileGroupImage img = r.infoAt(pt.x, pt.y);
			if (img != null) {
				Position pl = new Position(pt.x - r.anchor_x, pt.y - r.anchor_y);
				replaceImagesOnPosition.putl(p.add(pl), img);
			}	
		}					
	}

	//
	// nested classes

	private abstract class MapPosAnimation {
		int tileNr = 0;
		MapPosAnimation(int tileNr) {
			this.tileNr = tileNr;
		}
		public abstract boolean doit(Tile tile);
		public abstract TileImageAnimation createAnimation(Position p);
	}
	
	private class WandChancellorAnimation extends MapPosAnimation {
		final TileGroup g = repo.get("wall_chand");
		WandChancellorAnimation(int tileNr) {
			super(tileNr);			
		}
		public boolean doit(Tile tile) {
			return (tile.ground() == tileNr); 
		}
		public TileImageAnimation createAnimation(Position p) {
			TileImageAnimation animation = new TileImageAnimation(p, g.infoAt(1), g.infoAt(2), null);
			animation.speed(150);							
			return animation;
		}
	}
	
	private class WandTorchAnimation extends MapPosAnimation {
		final TileGroup g = repo.get("torch_wall");
		WandTorchAnimation(int tileNr) {
			super(tileNr);
		}
		public boolean doit(Tile tile) {
			return (tile.ground() == tileNr);
		}
		public TileImageAnimation createAnimation(Position p) {
			TileImageAnimation animation = new TileImageAnimation(p, g.infoAt(1), null, g.infoAt(2));
			animation.speed(200);
			return animation;
		}
	}
	
	private class TestWandChancellorAnimation extends MapPosAnimation {
		TileGroup g = repo.get("cancellier");
		TestWandChancellorAnimation(int tileNr) {
			super(tileNr);
		}		
		public boolean doit(Tile tile) {
			return (tile.ground() == tileNr);
		}
		public TileImageAnimation createAnimation(Position p) {
			TileImageAnimation animation = new TileImageAnimation(p, g.infoAt(1), g.infoAt(2), g.infoAt(1), null);
			animation.speed(270);
			return animation;
		}		
	}	
		
	private class StreetLampAnimation extends MapPosAnimation {
		TileGroup g = repo.get("lamp");
		TileGroup ga = repo.get("lamp_a");
		StreetLampAnimation(int tileNr) {
			super(tileNr);
		}		
		public boolean doit(Tile tile) {		
			return (tile.ground() == tileNr);
		}
		public TileImageAnimation createAnimation(Position p) {
			TileImageAnimation animation = new TileImageAnimation(p.addY(-2), g.infoAt(1), ga.infoAt(1));
			animation.speed(Utils.random(50, 100), Utils.random(150, 200), Utils.random(50, 100),
					Utils.random(150, 400), Utils.random(50, 100),
					Utils.random(500, 800), Utils.random(50, 100),
					Utils.random(500, 800), Utils.random(50, 100));
			return animation;			
		}		
	}	
	
	private class FackelAnimation extends MapPosAnimation {
		TileGroup g = repo.get("fackel");
		TileGroupImage exists;
		FackelAnimation(int tileNr) {
			super(tileNr);			
			remove(tileNr);		
			Surface fackelSurface = tileSet.getTileImage(tileNr);
			exists = new TileGroupImage(fackelSurface);
			exists.asOverlay();
		}	
		public boolean doit(Tile tile) {		
			return (tile.overlay() == tileNr);
		}	
		public TileImageAnimation createAnimation(Position p) {
			TileImageAnimation animation = new TileImageAnimation(p, g.infoAt(2), g.infoAt(1), g.infoAt(2), exists);
			animation.speed(200);
			return animation;
		}		
	}		
	
	private class TeleporterAnimation extends MapPosAnimation {
		TileGroup g;
		TeleporterAnimation(int tileNr, String name) {
			super(tileNr);
			g = repo.get(name);
		}
		public boolean doit(Tile tile) {
			return (tile.ground() == tileNr);
		}
		public TileImageAnimation createAnimation(Position p) {
			TileImageAnimation animation = new TileImageAnimation(p, g.infoAt(1), g.infoAt(2), g.infoAt(3), null);
			animation.speed(270);
			return animation;
		}		
	}	
	
}

