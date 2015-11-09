package com.captainsoft.spark._sparkmuell;

import com.captainsoft.spark.ui.*;
import com.captainsoft.spark.ui.box.UiBox;
import com.captainsoft.spark.ui.drawing.*;

/**
 * 
 *
 * @author mathias fringes
 */
public class DrawableUiBox extends UiBox {

	// fields
	
	private Drawing drawing = null;

	// constructors

	public DrawableUiBox() {
		super();		
	}
	
	public DrawableUiBox(Drawing drawing) {
		super();		
		this.drawing = drawing;
	}

	public DrawableUiBox(int x, int y, int width, int height) {
		this(null, x, y, width, height);
	}
	
	public DrawableUiBox(Drawing drawing, int x, int y, int width, int height) {
		super(x, y, width, height);
		this.drawing = drawing;
	}
	
	// public methods
	
	public void set(Drawing drawing) {
		this.drawing = drawing;
	}
	
	public void add(Drawing ... drawings) {
		if (this.drawing == null) {
			DrawingList list = new DrawingList();
			list.addAll(drawings);
			drawing = list; 
		} else if (drawing instanceof DrawingList) {
			((DrawingList) this.drawing).addAll(drawings);
		} else {
			DrawingList list = new DrawingList();
			list.add(this.drawing);
			list.addAll(drawings);
		}
	}

	// overridden methods

	@Override
	protected void draw(Surface s) {
		if (drawing != null) {
			drawing.draw(s);
		}
	}

}
