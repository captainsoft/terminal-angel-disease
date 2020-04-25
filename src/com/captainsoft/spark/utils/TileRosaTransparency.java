/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.utils;

import java.awt.Color;
import java.awt.image.RGBImageFilter;

/**
 * Transparency for the tiled, two rosa background colors.
 *
 * @author mathias fringes
 */
public final class TileRosaTransparency extends RGBImageFilter {

    // static

    public static final TileRosaTransparency Instance = new TileRosaTransparency();

    // fields

    private final Color color1;
    private final Color color2;
    private final int c_rgb1;
    private final int c_rgb2;

    // constructors

    public TileRosaTransparency() {
        super();
        color1 = new Color(255, 60, 239, 0);
        c_rgb1 = color1.getRGB();
        color2 = new Color(255, 120, 255, 0);
        c_rgb2 = color2.getRGB();
        canFilterIndexColorModel = true;
    }

    // RGBImageFilter

    @Override
    public int filterRGB(int x, int y, int rgb) {
        if (((rgb & 0xff0000) == color1.getRed() * 256 * 256) && ((rgb & 0xff00) == color1.getGreen() * 256) && ((rgb & 0xff) == color1.getBlue())) {
            return c_rgb1;
        }
        if (((rgb & 0xff0000) == color2.getRed() * 256 * 256) && ((rgb & 0xff00) == color2.getGreen() * 256) && ((rgb & 0xff) == color2.getBlue())) {
            return c_rgb2;
        }
        return rgb;
    }

}