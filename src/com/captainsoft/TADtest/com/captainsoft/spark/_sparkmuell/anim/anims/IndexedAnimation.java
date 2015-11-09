/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.spark._sparkmuell.anim.anims;

import java.util.*;

import com.captainsoft.spark._sparkmuell.anim.*;

/** 
 *
 * @author mathias fringes
 */
public abstract class IndexedAnimation<T> implements Animation {
	
	// fields
	
	private int lastAddedFrame = 0;
	private int highestFrame = 0;
	private int played = 0;
	private Map<Frame, T> values;
	private T defaultValue;
	
	// constructors
	
	public IndexedAnimation() {
		super();
		values = new HashMap<Frame, T>(1);
	}
	
	// public methods
	
	public final void set(int frame, T value) {
		values.put(new Frame(frame), value);
		lastAddedFrame = frame;
		if (frame > highestFrame) {
			highestFrame = frame;
		}
	}
	
	public final void setNext(T value) {
		set(lastAddedFrame + 1, value);
	}
	
	public final void setPlus(int plusFrames, T value) {
		set(lastAddedFrame + plusFrames, value);
	}
	
	public final void first(T value) {
		set(Frame.FIRST.no(), value);
	}
	
	public final int highestFrame() {
		return highestFrame;
	}
	
	public final void defaultValue(T value) {
		defaultValue = value;
	}
	
	public final T defaultValue() {
		return defaultValue;
	}
	
	public final String toStringAt(int frame) {
		T value = at(frame);		
		return "(" + frame + "): " + value; 		
	}
	
	// private methods
	
	private boolean finished() {
		return (played >= values.size());
	}
	
	private T at(int frame) {
		return this.values.get(new Frame(frame));
	}
	
	// protected methods
	
	protected abstract void valueChange(T value);	
		
	// overridden methods 
	
	@Override
	public final PlayState play(int frame) {		
		T v = at(frame);		
		if (v != null) {
			valueChange(v);
			played++;
			if (finished()) {				
				return PlayState.Finished;
			} else {
				return PlayState.Action;
			}
		} else {
			return PlayState.Yield;
		}
	}	
	
	@Override
	public final void reset() {		
		played = 0;		
		T dv = defaultValue();
		if (dv != null) {			
			valueChange(dv);
		}
	}
	
}
