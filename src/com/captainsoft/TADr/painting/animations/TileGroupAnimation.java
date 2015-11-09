/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.painting.animations;

import java.util.ArrayList;
import java.util.List;

import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.TADr.model.Position;
import com.captainsoft.TADr.painting.replacer.TileGroup;
import com.captainsoft.TADr.painting.replacer.TileGroupImage;
import com.captainsoft.TADr.painting.replacer.TileGroupImageProxy;
import com.captainsoft.spark.render.Animation;
import com.captainsoft.spark.utils.LoopCounter;

/**
 * Generic Animation for TileGroup. Animations consists of
 * several TileGroup instances. An animation replaces every TileGroupImage
 * with the next image.
 *
 * @author mathias fringes
 */
public final class TileGroupAnimation extends Animation {
	
	// fields
	
	private final ArrayList<TileGroupImage> images;
	private final LoopCounter counter;	
	private final List<TileGroup> groups;
	
	private Position[] positions;	
	
	public int speed = 150;

	// constructors	
	
	public TileGroupAnimation(List<TileGroup> groups, String name) {
		super();		
		this.name = name;
		//
		if (groups.size() == 0) {
			throw new IllegalArgumentException("The list must not be empty!");
		}
		//
		this.groups = groups;
		//
		images = new ArrayList<TileGroupImage>(groups.get(0).imageCount());
		TileGroup r = groups.get(0);
		for (TileGroupImage tgi : r.imageList()) {
			images.add(new TileGroupImageProxy(tgi));	
		}
		images.trimToSize();
		//
		counter = new LoopCounter(0, groups.size() - 1);
		counter.randomize();
	}
	
	// accessors
	
	public TileGroupImage image(int index) {
		return images.get(index); 
	}
	
	// public
		
	public Position[] positions() {
		return positions;
	}
	
	public void setPosition(Position p) {
		TileGroup r = groups.get(0);
		positions = new Position[r.x_span * r.y_span];
		int index = 0;
		for (int x = 1; x <= r.x_span; x++) {
			for (int y = 1; y <= r.y_span; y++) {
				Position realPosition = p.add(x - r.anchor_x, y - r.anchor_y);
				positions[index++] = realPosition;
			}
		}
	}
	
	// Animation 
	
	@Override
	public int play() {
		int i = 1;
		for (TileGroupImage tileGroupImage : images) {
			TileGroupImage tgi = groups.get(counter.current()).infoAt(i);			
			tileGroupImage.image(((tgi == null) ? null : tgi.image()));			
			i++;
		}
	    TadRepo.inst().GameEngine().updateTile(positions);
	
		counter.count();	
		return speed;
	}
	
}