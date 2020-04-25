/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.ui_swing;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * A simple panel that draws an image.
 *
 * @author mathias fringes.
 */
@SuppressWarnings("serial")
public class ImagePanel extends JPanel {

    // static members

    public static final int RENDER_CENTER = 1;
    public static final int RENDER_TOPLEFT = 2;
    public static final int RENDER_STRETCH = 4;

    // fields

    private Image image;
    private int renderStyle;

    // constructors

    public ImagePanel() {
        this(null, 100, 100);
    }

    public ImagePanel(Image image) {
        this(image, image.getWidth(null), image.getHeight(null), RENDER_TOPLEFT);
    }

    public ImagePanel(Image image, int width, int height) {
        this(image, width, height, RENDER_TOPLEFT);
    }

    public ImagePanel(Image image, int width, int height, int renderStyle) {
        super();
        this.image = image;
        this.renderStyle = renderStyle;
        this.setSize(width, height);
    }

    // public

    public void grow(int pixel) {
        this.setSize(this.getWidth() + pixel * 2, this.getHeight() + pixel * 2);
    }

    public void setImage(Image image) {
        this.image = image;
        this.repaint();
    }

    public void setRenderStyle(int renderStyle) {
        this.renderStyle = renderStyle;
        this.repaint();
    }

    public void sizeToImage(Image image) {
        this.setSize(image.getWidth(this), image.getHeight(this));
        this.setImage(image);
    }

    // overridden

    @Override
    public boolean isFocusable() {
        return false;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //
        // draw the image...
        if (this.image == null) {
            return;
        }
        //
        switch (this.renderStyle) {
            case RENDER_CENTER:
                int img_width = this.image.getWidth(this);
                int img_height = this.image.getHeight(this);
                g.drawImage(this.image, this.getWidth() / 2 - img_width / 2, this.getHeight() / 2 - img_height / 2, this);
                break;
            case RENDER_STRETCH:
                Image scaledImage = this.image.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_FAST);
                g.drawImage(scaledImage, 0, 0, this);
                break;
            case RENDER_TOPLEFT:
            default:
                g.drawImage(this.image, 0, 0, this);
                break;
        }
    }

}