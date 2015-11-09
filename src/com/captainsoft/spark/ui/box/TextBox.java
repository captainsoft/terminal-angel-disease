/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.ui.box;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import com.captainsoft.spark.ui.Fonts;
import com.captainsoft.spark.ui.drawing.Surface;

/**
 * A box that draws text.
 *
 * @author mathias fringes
 */
public final class TextBox extends UiDrawBox {
	
	// fields
	
	public static final Font DefaultFont = new Font(Fonts.Verdana, Font.BOLD, 10);
	
	public boolean oneLine = false;		
	public Font font = DefaultFont;
	public String text;

	private boolean left = false; 
	private boolean right = false;
	private boolean repaint = true;
	private Color color = Color.WHITE;
	private Surface bufferSurface;
	private String drawnText = null;	
	
	// constructors
	
	public TextBox() {
		this("", 0, 0, 100, 20);		
	}
	
	public TextBox(int width, int height) {
		this("", 0, 0, width, height);
	}
	
	public TextBox(String text, int x, int y, int width, int height) {
		super(x, y, width, height);		
		this.text = text;
		alignCenter();
		clickable(false);
	}
	
	// public
	
	public void color(Color color) {
		this.color = color;
		this.repaint = true;
	}
	
	public void alignLeft() {
		this.right = false;
		this.left = true;
	}
	
	public void alignRight() {
		this.right = true;
		this.left = false;
	}
	
	public void alignCenter() {
		this.right = false;
		this.left = false;
	}
	
	public void text(String text) {
		this.text = text;
		drawBuffer();
	}

    public int lines() {
        return oneLine ?  1 : bufferSurface.textSurfaceDrawer().lines();
    }
	
	// private
	
	private void drawBuffer() {
		if (font != null) {
			bufferSurface.font(font);
		}							
		if (text != null && text.length() > 0) {
						
			bufferSurface.turnAliasOn();
			if (oneLine) {
				Point p = null;
				if (left|| !right) {
					p = left ? bufferSurface.getTextLeftPos(text, width, height) : bufferSurface.getTextMiddlePos(text, width, height);
				}
				if (right) {
					p = bufferSurface.getTextRightPos(text, width, height);
				}
				bufferSurface.text(color, text, p.x, p.y);
			} else {
				Point p = left ? new Point(0, bufferSurface.getFontHeight(text)) : null;			
				bufferSurface.textArea(color, text, p, width, height);
			}
		}
	}
	
	// overridden
	
	@Override
	protected void draw(Surface s) {			
		super.draw(s);		
		if (repaint || (text == null && drawnText != null) || (text != null && !text.equals(drawnText))) {			
			bufferSurface = new Surface(width, height, true);			
			drawBuffer();
			drawnText = text;
			repaint = false;
		}
		s.blit(bufferSurface);		
	}
		
}