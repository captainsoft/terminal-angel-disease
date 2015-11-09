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
public final class AnimationChain implements Animation {

	// privates 
	
	private Animation currentAnimation;
	private List<Animation> animations;	
	private int playCount = 0;
	private int startFrame = 0;
	
	// constructors
	
	public AnimationChain() {
		super();
		animations = new ArrayList<Animation>();
	}	
	
	// private methods
	
	private boolean finished() {
		return (playCount >= animations.size()); 
	}
	
	// public methods
	
	public void add(Animation a) {
		animations.add(a);
	}
	
	// overridden methods
	
	@Override
	public PlayState play(int frame) {	
		if (currentAnimation == null)  {
			currentAnimation = animations.get(playCount);
			playCount++;
			startFrame = frame - 1;
		}
		
		PlayState state = currentAnimation.play(frame - startFrame);		
		if (state == PlayState.Finished) {
			if (!finished()) {
				currentAnimation = null;
				state = PlayState.Action;
			}
		}
		return state;
	}

	@Override
	public void reset() {		
		playCount = 0;
		currentAnimation = null;
		for (Animation a : animations) {
			a.reset();
		}
	}
	
}