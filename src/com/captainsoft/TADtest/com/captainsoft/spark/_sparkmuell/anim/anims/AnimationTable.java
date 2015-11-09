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
public final class AnimationTable implements Animation {
	
	// privates 
		
	private int played = 0;
	private List<AnimationSchedule> activeAnimations;
	private Map<Frame, List<Animation>> animations;	
	
	// constructors
	
	public AnimationTable() {
		super();
		animations = new HashMap<Frame, List<Animation>>();
		activeAnimations = new ArrayList<AnimationSchedule>();
	}	
	
	// public methods
	
	public void add(int frame, Animation animation) {
		final Frame f = new Frame(frame);
		List<Animation> al =  animations.get(f);
		if (al == null) {
			al = new ArrayList<Animation>(1);
			animations.put(f, al);
		}
		al.add(animation);
    }		
	
	public boolean finished() {
		return (played >= animations.size()); 
	}
	
	//
	
	@Override
	public PlayState play(int frame) {
		
		PlayState resultState = PlayState.Yield;
		
		// active animations
		if (activeAnimations.size() > 0) {
			Iterator<AnimationSchedule> i = activeAnimations.iterator();						
			while (i.hasNext()) {
				AnimationSchedule as = i.next();				
				PlayState ps = as.a.play(frame - as.start.no() + 1);
				if (ps == PlayState.Finished) {					
					i.remove();
					played++;
					if (resultState == PlayState.Yield) {
						resultState = ps; 
					}
				} else {
					resultState = ps;
				}
				
			}
		}
		
		// start new animations for this frame
		final Frame fo = new Frame(frame);
		List<Animation> al = animations.get(fo);
		if (al != null) {
			for(Animation a : al) {			
				PlayState ps = a.play(Frame.FIRST.no());													
				if (ps == PlayState.Finished) {
					played++;
					if (resultState == PlayState.Yield) {
						resultState = ps; 
					}
				} else {
					activeAnimations.add(new AnimationSchedule(fo, a));
					resultState = ps;
				}				
			}
		}
		
		return resultState;	
	}	
	
	@Override
	public void reset() {
		played = 0;
		activeAnimations.clear();
		
		for (List<Animation> as : animations.values()) {
			for (Animation a : as) {
				a.reset();
			}
		}
	}
	
	// nested classes

	private static final class AnimationSchedule {	
		public Animation a;
		public Frame start;		
		public AnimationSchedule(Frame start, Animation a) {			
			this.a = a;
			this.start = start;
		}
		public String toString() {		
			return "[" + start + "] " + a;  
		}
	}

}
