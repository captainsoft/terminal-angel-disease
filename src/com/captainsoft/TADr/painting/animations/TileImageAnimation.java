/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.painting.animations;

import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.TADr.model.Position;
import com.captainsoft.TADr.painting.replacer.TileGroupImage;
import com.captainsoft.TADr.painting.replacer.TileGroupImageProxy;
import com.captainsoft.spark.collect.Clist;
import com.captainsoft.spark.render.Animation;
import com.captainsoft.spark.ui.drawing.Surface;
import com.captainsoft.spark.utils.LoopCounter;

/**
 * Animation for a TileImage on a single position.
 *
 * @author mathias fringes
 */
public final class TileImageAnimation extends Animation {

	// fields 
	
	private final Clist<TileGroupImage> images;
	private final TileGroupImageProxy image;
	private final LoopCounter counter;
	
	public final Position position;
	
	private int[] speed;
	private LoopCounter speedCounter;
	
	// constructors
	
	public TileImageAnimation(Position position, TileGroupImage ... images) {
		super();
		this.position = position;
		this.images = new Clist<TileGroupImage>(images);
		//
		counter = new LoopCounter(0, images.length - 1);
		counter.randomize();
		image = new TileGroupImageProxy(images[0]);
		speed(150);
	}
	
	// public
	
	public TileGroupImage image() {
		return image;
	}

	public void speed(int ... speed) {
		this.speed = speed;
		speedCounter = new LoopCounter(0, speed.length - 1);
	}
	
	// overridden
	
	@Override
	public int play() {
		TileGroupImage tig = images.get(counter.current());
		Surface currentImage = tig == null ? null : tig.image();
		image.image(currentImage);
		
		TadRepo.inst().GameEngine().updateTile(position);
		
		counter.count();
		return speed[speedCounter.next()];
	}

}
