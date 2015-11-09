/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.ui.box;

import java.awt.Color;

import com.captainsoft.spark.ui.drawing.Surface;

/**
 * Box that draws a image (represented by a Surface).
 *
 * @author mathias fringes
 */
public class ImageBox extends UiBoxContainer {
	
	// fields	
	
	private float scale = 1f;
	private Surface originalImage = null;		
	private Surface paintImage = null;	
	
	public boolean debugLine = false;
	
	// constructors
	
	public ImageBox() {
		super();
	}	
	
	public ImageBox(Surface surface) {
		this(surface, 0, 0, surface.width(), surface.height());
	}
	
	public ImageBox(Surface surface, int x, int y) {
		this(surface, x, y, surface.width(), surface.height());
	}
	
	public ImageBox(Surface bitimage, int x, int y, int width, int height) {
		super();	
		this.originalImage = bitimage;
		this.paintImage = originalImage;
		size(width, height);
		pos(x, y);
	}
	
	// public	
	
	public void scale(float scale) {
		this.scale = scale;
		//
		if (paintImage == null) {			
			size(Math.round(width * scale), Math.round(height * scale));
		} else {
			if (scale == 1) {
				paintImage = originalImage;
			} else {
				paintImage = originalImage.createScaled(scale);
			}	
			size(paintImage.width(), paintImage.height());
		}
	}

	public float scale() {
		return scale;
	}
	
	public Surface imageSurface() {
		return this.paintImage;
	}
	
	public void imageSurface(Surface surface) {
		this.originalImage = surface;
		this.paintImage = this.originalImage;	
		if (scale != 1) {
			scale(scale);
		}
	}
		
	// overridden
	
	@Override
	protected void draw(Surface s) {		
		if (paintImage != null) {
			s.blit(paintImage);							
		}
		if (debugLine) {
			s.color(Color.RED);
			s.rect(0, 0, paintImage.width(), paintImage.height());
		}
	}

}
