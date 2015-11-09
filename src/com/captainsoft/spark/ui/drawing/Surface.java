/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.ui.drawing;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.util.*;

/**
 * @author mathias fringes
 */
public final class Surface implements Bitimage {

	// fields

	private static final Color defaultColor = Color.BLACK;
	private static final Component imageObserver = new Component() { private static final long serialVersionUID = 1L; };
	private static final Stroke defaultStroke = new BasicStroke();
	
	private boolean transparent = false;
	private BufferedImage image;
	private Graphics2D g;
    private TextSurfaceDrawer textSurfaceDrawer;

    // constructors

	private Surface() {
		super();
	}

	public Surface(Image image) {
		this(image, false);
	}
	
	public Surface(Surface surface) {
		this(surface, false);
	}
	
	public Surface(Surface surface, boolean transparent) {
		this(surface.width(), surface.height(), transparent);
		blit(surface);
	}
	
	public Surface(Image image, boolean transparent) {
		super();	
		this.image = new BufferedImage(
				image.getWidth(imageObserver), 
				image.getHeight(imageObserver), 
				transparent ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB);
		this.transparent = transparent;
		//
		g = this.image.createGraphics();
		blit(image, 0, 0);
	}	
	
	public Surface(int width, int height) {
		this(width, height, false);
	}
	
	public Surface(int width, int height, boolean transparent) {
		super();
		this.transparent = transparent;
		image = new BufferedImage(width, height, transparent ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB);
		g = image.createGraphics();	
	}	

	public static Surface fromImage(BufferedImage image) {
		Surface surface = new Surface();
		surface.image = image;
		surface.g = image.createGraphics();		
		return surface;		
	}
	
	// create transformed instance methods
	
	public Surface createScaled(float scale) {
		int newWidth = Math.round(this.width() * scale);  
		int newHeight = Math.round(this.height() * scale);		
		//
		Surface surface = new Surface(newWidth, newHeight, transparent);		
		surface.blit(this, 0, 0, newWidth, newHeight);			
		return surface;
	}
		
	// accessors

    public boolean transparent() {
        return transparent;
    }

	public int width() {
		return image.getWidth();
	}

	public int height() {
		return image.getHeight();
	}

    public TextSurfaceDrawer textSurfaceDrawer() {
        if (textSurfaceDrawer == null) {
            textSurfaceDrawer = new TextSurfaceDrawer(this);
        }
        return textSurfaceDrawer;
    }

	// drawing methods
	
	public void blit(Image image, int x, int y) {
		g.drawImage(image, x, y, imageObserver);
	}
	
	public void blit(Surface image) {
		blit(image, 0, 0);
	}

	public void blit(Surface image, int x, int y) {
		g.drawImage(image.image, x, y, imageObserver);
	}
	
	public void blit(Surface image, int x, int y, int width, int height) {
		g.drawImage(image.image, x, y, width, height, imageObserver);
	}
	
	public void border() {
		rect(0, 0, width(), height());
	}

	public void clear(Color color) {
		color(color);
		clear();
	}

	public void clear() {
		g.fillRect(0, 0, width(), height());
	}
	
	public Color color() {
		return g.getColor();
	}

	public void color(Color color) {
		g.setColor(color);
	}

	public void fill(Color c, int x, int y, int width, int height) {
		color(c);
		fill(x, y, width, height);
	}

	public void fill(int x, int y, int width, int height) {
		g.fillRect(x, y, width, height);
	}

	public void font(Font font) {
		g.setFont(font);
	}

	public void line(Color c, int x1, int y1, int x2, int y2) {
		color(c);
		line(x1, y1, x2, y2);
	}

	public void line(int x1, int y1, int x2, int y2) {
		g.drawLine(x1, y1, x2, y2);
	}

	public void rect(Color c, int x, int y, int width, int height) {
		color(c);		
		rect(x, y, width, height);
	}

	public void rect(int x, int y, int width, int height) {
		g.drawRect(x, y, width-1, height-1);
	}

	public void pixel(Color c, int x, int y) {
		color(c);
		pixel(x, y);
	}

	public void pixel(int x, int y) {
		g.drawRect(x, y, 1, 1);
	}

	public void stroke(Stroke stroke) {
		this.g.setStroke(stroke);	
	}
		
	public void text(Color c, String text, int x, int y) {
		color(c);
		text(text, x, y);
	}

	public void text(String text, int x, int y) {
		g.drawString(text, x, y);		
	}	
	
	/**
	 * Draws a text and spans over several lines if necessary. Use " <br> " inside the 
	 * text to force a line break.
	 * 
	 * @param color the color of the text.
	 * @param text the test.
	 * @param p an optional point where to start displaying the text. If set to null, 
	 * 			the text is centered.
	 * @param width the width of the text area.
	 * @param height the height of the text area.
	 */
	public void textArea(Color color, String text, Point p, int width, int height) {
        textSurfaceDrawer().textArea(color, text, p, width, height);
	}
	
	public Point getTextMiddlePos(String text, int width, int height) {
		Rectangle2D r = getFontRectangle(text);		
		return new Point((int)((width - r.getWidth()) / 2), (int)((height + r.getHeight() - 4) / 2));		
	}
	
	public Point getTextRightPos(String text, int width, int height) {
		Rectangle2D r = getFontRectangle(text);
		return new Point((int)(width - r.getWidth()), (int)((height + r.getHeight() - 4) / 2));
	}
	
	public Point getTextLeftPos(String text, int width, int height) {
		Rectangle2D r = getFontRectangle(text);
		return new Point(0, (int)((height + r.getHeight() - 4) / 2));
	}
	
	public int getFontHeight(String text) {	
		Rectangle2D r = getFontRectangle(text);
		return (int)(r.getHeight());
	}
		
	public void translate(int x, int y) {
		g.translate(x, y);
	}

	// technical methods and configuration
	
	public Surface copy() {
		Surface g = new Surface(width(), height(), transparent);
		
		
		WritableRaster wr = WritableRaster.createWritableRaster(bufferedImage().getSampleModel(),new Point(0,0));
		g.bufferedImage().copyData(wr);
		BufferedImage temp = new BufferedImage(g.bufferedImage().getColorModel(),wr,true,null);
		
		return Surface.fromImage(temp);
	}
	
	public Surface flipHorizontally() {
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-image.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		image = op.filter(image, null);
		return this;
	}
	
	public void turnAliasOn() {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}

	public void defaults() {
		g.setColor(defaultColor);
		g.setStroke(defaultStroke);
	}
	
	public Surface stamp(int x, int y, int width, int height) {
		return new Surface(image.getSubimage(x, y, width, height), transparent);
	}

	public void release() {
        textSurfaceDrawer = null;
		if (g != null) {
			g.dispose();
			g = null;
		}
		if (image != null) {
			image.flush();
			image = null;
		}
	}
	
	public BufferedImage bufferedImage() {
		return image;
	}
	
	// package methods
	
	Rectangle2D getFontRectangle(String text) {
		return g.getFont().getStringBounds(text, g.getFontRenderContext());
	}
	
	// implementation of Bitimage

	public Image image() {
		return image;
	}

}
