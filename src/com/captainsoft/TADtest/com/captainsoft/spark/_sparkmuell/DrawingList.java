package com.captainsoft.spark._sparkmuell;

import java.util.*;

import com.captainsoft.spark.ui.drawing.*;

@SuppressWarnings("serial")
public class DrawingList extends ArrayList<Drawing> implements Drawing {

	public DrawingList() {
		super();	
	}

	public DrawingList(Collection<? extends Drawing> c) {
		super(c);
	}

	public DrawingList(int initialCapacity) {
		super(initialCapacity);	
	}
	
	public void addAll(Drawing ... drawings) {
		for(Drawing d : drawings) {
			add(d);
		}
	}

	@Override
	public void draw(Surface s) {
		for(Drawing d : this) {
			d.draw(s);
		}
	}

}
