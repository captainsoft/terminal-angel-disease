/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.ui.box;

import java.awt.*;

import com.captainsoft.spark.ui.drawing.*;
import com.captainsoft.spark.utils.*;

/**
 * A extended box ready for used in a GUI application.
 *
 * This box does not have its own surface! For drawing, it takes
 * the parents surface. See also {@link UiBoxContainer}!
 * 
 * @author mathias fringes
 */
public class UiBox extends Box {

	// fields

	public Color debugColor = Color.LIGHT_GRAY;
	
	private boolean clickable = true;
	private boolean enabled = true;	
	private boolean visible = true;
	private UiBoxContainer parent = null;

	// constructors

	public UiBox() {
		super();		
	}
	
	public UiBox(String name) {
		super();
		this.name = name;
	}
	
	public UiBox(UiBoxContainer parent, int x, int y, int width, int height) {
		this("", x, y, width, height);
		parent(parent);		
	}

	public UiBox(int x, int y, int width, int height) {
		this("", x, y, width, height);		
	}
		
	public UiBox(String name, int x, int y, int width, int height) {
		super(x, y, width, height);
		this.name = name;
	}

	// accessors	
	
	public final boolean clickable() {
		return clickable;
	}

	public final void clickable(boolean clickable) {
		this.clickable = clickable;
	}

	public final boolean enabled() {
		return (parent == null) ? enabled : enabled && parent.enabled();
	}

	public final void enabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public final UiBoxContainer parent() {
		return parent;
	}

	public final void parent(UiBoxContainer parent) {
		if (parent == this) {
			throw new IllegalArgumentException("Cannot set itself to a parent!");
		}
		if (this.parent != null) {
			this.parent.remove(this);
		}
		this.parent = parent;
		if (parent != null) {
			parent.add(this);			
		}
	}	

	public final int screenx() {
		return (parent == null) ? x : parent.screenx() + x;
	}

	public final int screeny() {
		return (parent == null) ? y : parent.screeny() + y;
	}

	public final boolean visible() {
		return (parent == null) ? visible : visible && parent.visible();
	}	

	public final void visible(boolean visible) {
		this.visible = visible;
	}
	
	public final boolean visible(int x, int y) {
		return (visible() && contains(x, y));
	}	

	// public
	
	public final void center() {
		if (parent != null) {
			pos(Math.round((parent.width - width) / 2), Math.round((parent.height - height) / 2));
		}
	}

	public final UiBox getLowestBox() {
		return ((parent() == null) ? this : parent.getLowestBox());		
	}	

	public UiBox getBox(int x, int y) {
		return (visible(x, y) ? this : null); 		
	}
	
	public UiBox getClickableBox(int x, int y) {
		return (clickable() && visible(x, y) ? this : null); 		
	}

	public void update() {
		if (visible()) {
			Log.paint(this.name != null && this.name.length() > 0 ? this.name : this);
			Surface s = this.surface();
			if (s != null) {				
				s.translate(graphicsx(), graphicsy());				
				filterAndDraw(s);
				s.translate(-graphicsx(), -graphicsy());
			}			
		}
	}	

	// protected

	protected void draw(Surface s) {		
	}
	
	/**
	 * Gets the lowest surface.
	 * 
	 * @return
	 */
	protected Surface surface() {
		return (parent == null) ? null : parent().surface();
	}

	protected final int graphicsx() {
		return (parent == null) ? 0 : parent.graphicsx() + x;
	}

	protected final int graphicsy() {
		return (parent == null) ? 0 : parent.graphicsy() + y;
	}
	
	// private
	
	private void filterAndDraw(Surface s) {
		draw(s);
		boolean drawDebugInfo = false;
		if (drawDebugInfo) {
			Color c = s.color();	
			s.stroke(new BasicStroke(1f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1f, new float[] {1f, 5f}, 1f));			
			s.rect(debugColor, 0, 0, this.width, this.height);
			s.defaults();
			s.color(c);			
		}
	}	

}