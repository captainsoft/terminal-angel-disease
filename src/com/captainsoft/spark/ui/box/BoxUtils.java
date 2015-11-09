/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.ui.box;

import com.captainsoft.spark.ui.CDim;
import com.captainsoft.spark.ui.CPos;

/**
 * Utilities for boxes. Static methods here only.
 *
 * @author mathias fringes
 */
public final class BoxUtils {
	
	// constructors
	
	private BoxUtils() {
		super();
	}
	
	// public
	
	public static CPos pos(Box box) {
		return new CPos(box.x, box.y);
	}
	
	public static CDim dim(Box box) {
		return new CDim(box.width, box.height);
	}
	
	public static void pos(Box box, CPos pos) {
		box.pos(pos.x, pos.y);
	}
	
	public static void posMiddle(Box box, CPos pos) {
		box.posMiddle(pos.x, pos.y);
	}
	
	public static void dim(Box box, CDim dim) {
		box.size(dim.width, dim.height);
	}
	
	public static void set(Box box, CPos pos, CDim dim) {
		box.set(pos.x, pos.y, dim.width, dim.height);
	}	
		
}
