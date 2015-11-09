/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.ui;

/**
 * A 2-dimensional Dimension. 
 *
 * @author mathias fringes
 */
public final class CDim {
	
	// fields
	
	public int width = 0;

	public int height = 0;
	
   // constructors
	
	public CDim() {
		super();
	}
	
	public CDim(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}
	
	public CDim(CDim dim) {
		this(dim.width, dim.height);		
	}
	
	// public
	
	public boolean equals(CDim dim) {
		if (dim == null) {
			return false;
		}
		return ((width == dim.width) && (height == dim.height));
	}
		
	public CDim scale(float ratio) {
		return scale(ratio, ratio);
	}
	
	public CDim scale(float ratiox, float ratioy) {
		this.width = Math.round(this.width * ratiox);
		this.height = Math.round(this.height * ratioy);
		return this;
	}
	
	public CDim set(int width, int height) {		
		this.width = width;
		this.height = height;
		return this;
	}
	
	// overridden
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof CDim)) {
			return false;
		}
		return this.equals((CDim)obj);
	}
	
	@Override
	public int hashCode() {
		return width ^ height;
	}
	
	@Override
	public String toString() {	
		return "CDim (" + width + "|" + height + ")";
	}

}
