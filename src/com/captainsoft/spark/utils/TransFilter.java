/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.utils;

import java.awt.Color;
import java.awt.image.RGBImageFilter;

/** 
 * Image filter for a single transparent color.
 *
 * @author mathias fringes
 */
public final class TransFilter extends RGBImageFilter {

    // fields
	
	public static final TransFilter BLACK = new TransFilter(0, 0, 0);
	public static final TransFilter WHITE = new TransFilter(255, 255, 255);
	
	private final Color color;
    private final int c_rgb;

    // constructors

	public TransFilter(int r, int g, int b) {
		super();
		color = new Color(r, g, b, 0);
		c_rgb = color.getRGB();
		canFilterIndexColorModel = true;
	}

    // RGBImageFilter

	@Override
	public int filterRGB(int x, int y, int rgb) {
		if (((rgb & 0xff0000) == color.getRed() << 16) && ((rgb & 0xff00) == color.getGreen() << 8) && ((rgb & 0xff) == color.getBlue())) {
			return c_rgb;		
		}
		return rgb;
	}
	
}