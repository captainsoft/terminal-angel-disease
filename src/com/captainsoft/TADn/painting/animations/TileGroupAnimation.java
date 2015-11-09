/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.painting.animations;

import java.util.ArrayList;
import java.util.List;

import com.captainsoft.TADn.TadRepo;
import com.captainsoft.TADn.model.Position;
import com.captainsoft.TADn.painting.replacer.TileGroup;
import com.captainsoft.TADn.painting.replacer.TileGroupImage;
import com.captainsoft.TADn.painting.replacer.TileGroupImageProxy;
import com.captainsoft.spark.utils.LoopCounter;

/**
 * Generic Animation for TileGroup. Animations consists of
 * several TileGroup instances. An animation replaces every TileGroupImage
 * with the next image.
 *
 * @author mathias fringes
 */
public final class TileGroupAnimation implements Animation {
	
	// fields
	
	private final ArrayList<TileGroupImage> images;
	private final LoopCounter counter;	
	private final List<Position> positions = new ArrayList<Position>();	
	private final List<TileGroup> groups;	

	// constructors
	
	// TODO umbauen einfach die Position, die liste der TileGroups übergeben,
	// - ?? TileGroupProxy kann weg, in der animation werden direlt die mappings ??
	// position -> tilegroupimage neu gesetzt (?)
	
	public TileGroupAnimation(List<TileGroup> groups) {
		super();		
		//
		if (groups.size() == 0) {
			throw new IllegalArgumentException("The list must not be empty!");
		}
		//
		this.groups = groups;
		//
		this.images = new ArrayList<TileGroupImage>(groups.get(0).imageCount());
		TileGroup r = groups.get(0);
		for (TileGroupImage tgi : r.imageList()) {
			images.add(new TileGroupImageProxy(tgi));	
		}
		images.trimToSize();
		this.counter = new LoopCounter(0, groups.size() - 1);
		counter.randomize();
	}
	
	// accessors
	
	public TileGroupImage image(int index) {
		return images.get(index); 
	}
	
	// public
		
	public List<Position> positions() {
		return positions;
	}
	
	public void setPosition(Position p) {
		TileGroup r = groups.get(0);
		for (int x = 1; x <= r.x_span; x++) {
			for (int y = 1; y <= r.y_span; y++) {
				Position realPosition = p.add(x - r.anchor_x, y - r.anchor_y);
				positions.add(realPosition);
			}
		}
	}
	
	// MapAnimation 
	
	@Override
	public void play() {
		int i = 1;
		for (TileGroupImage tileGroupImage : images) {
			TileGroupImage tgi = groups.get(counter.current()).infoAt(i);			
			tileGroupImage.image(((tgi == null) ? null : tgi.image()));			
			i++;
		}		
		for(Position p : positions) {
			// TODO check a lot of drawing!?
			TadRepo.inst().GameEngine().updateTile(p);
		}	
		counter.count();	
	}
	
}