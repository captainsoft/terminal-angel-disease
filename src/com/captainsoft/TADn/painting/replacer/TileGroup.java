/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.painting.replacer;

import java.awt.Image;
import java.awt.image.ImageFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.captainsoft.spark.image.ImageTool;
import com.captainsoft.spark.ui.drawing.ImageSurface;
import com.captainsoft.spark.ui.drawing.Surface;
import com.captainsoft.spark.utils.TileRosaTransparency;

/**
 * A group of combined tile images. This usually exists once per replace tile,
 * and is then referenced on the map via its images.
 *
 * @author mathias fringes
 */
public final class TileGroup {

	// fields
	
	private static final ImageFilter colorFilter = new TileRosaTransparency();	
	private static final int c = 40;
	
	private final ArrayList<TileGroupImage> imageListZickZackOrder; 	
	private final TileGroupImage[][] tiles;
	
	public final int anchor_x;
	public final int anchor_y;
	public final int x_span;
	public final int y_span;		
		
	// constructors
	
	public TileGroup(Surface surface) {
		this(surface, 1, 1);
	}
	
	public TileGroup(Surface surface, int anchor_x, int anchor_y) {
		super();	
		//
		this.anchor_x = anchor_x;
		this.anchor_y = anchor_y;
		//
		this.x_span = surface.width() / c;
		this.y_span = surface.height() / c;		
		//
		imageListZickZackOrder = new ArrayList<TileGroupImage>();
		this.tiles = new TileGroupImage[x_span][y_span];
		for (int y = 0; y < y_span; y++) {
			for (int x = 0; x < x_span; x++) {
				Image tileImage = surface.stamp(x * c, y * c, c, c).image();				
				TileGroupImage tgi = new TileGroupImage(new ImageSurface(tileImage));
				tiles[x][y] = tgi;
				imageListZickZackOrder.add(tgi);
			}
		}		
		imageListZickZackOrder.trimToSize();
	}
	
	// accessors
	
	public int imageCount() {
		return (x_span * y_span);
	}
	
	public List<TileGroupImage> imageList() {
		return new ArrayList<TileGroupImage>(imageListZickZackOrder);
	}
		
	// public
	
	public TileGroupImage infoAt(int number) {
		return imageListZickZackOrder.get(number-1);
	}
	
	public TileGroupImage infoAt(int x, int y) {
		return tiles[x-1][y-1];
	}
		
	public void setGroundTiles(Integer ... grounds) {
		List<Integer> overlays = new ArrayList<Integer>();
		List<Integer> g = Arrays.asList(grounds);
		//
		for (int i = 1; i <= tileCount(); i++) {
			if (!g.contains(i)) {
				overlays.add(i);
			}
		}
		setSecondOverlayTiles(overlays.toArray(new Integer[0]));
	}
	
	public void setOverlayTiles(Integer ... overlays) {
		setOverlayTiles(colorFilter, overlays);
	}
	
	public void setOverlayTiles(ImageFilter filter, Integer ... overlays) {
		for (int o : overlays) {
			TileGroupImage tip = infoAt(o);
			tip.asOverlay();
			tip.image(new ImageSurface(ImageTool.createFiltered(tip.image().image(), filter), true));			
		}
	}
	
	public void setSecondOverlayTiles(Integer ... secondOverlays) {
		for (int o : secondOverlays) {
			TileGroupImage tip = infoAt(o);			
			tip.asSecondOverlay();
			tip.image(new ImageSurface(ImageTool.createFiltered(tip.image().image(), colorFilter), true));
		}
	}
		
	public void setDontCares(int ... dontCares) {
		for (int i : dontCares) {
			setInfoAt(i, null);
		}
	}	
	
	// private
	
	private int tileCount() {
		return imageListZickZackOrder.size();
	}
	
	private void setInfoAt(int number, TileGroupImage info) {
		int x = ((number-1) % x_span);
		int y = (((int)((number-1) / x_span)) % y_span);
		tiles[x][y] = info;
		imageListZickZackOrder.set(number-1, info);
	}
	
}