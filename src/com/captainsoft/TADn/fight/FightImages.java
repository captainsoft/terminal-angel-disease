/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.fight;

import com.captainsoft.TADn.*;
import com.captainsoft.TADn.loader.*;
import com.captainsoft.spark.ui.drawing.*;
import com.captainsoft.spark.utils.*;

/**
 * Pre-loaded images for the Fight.
 *
 * @author mathias fringes
 */
public final class FightImages {

	// static	

	private static FightImages inst;
	
	public static FightImages inst() {
		if (inst == null) {
			inst = new FightImages();
		}
		return inst;
	}
	
	// private
	
	private Surface[] effectImages;
	
	public Surface monSelectSurface;
	
	// constructors

	private FightImages() {
		super();
		ImageLoader imageLoader = TadRepo.inst().ImageLoader();
		//
		// effects
		int val[] = {11, 12, 13, 14, 15, 16, 21, 22, 31, 32, 41, 42, 51, 52, 61, 62, 71, 72, 81, 82};
		effectImages = new Surface[val.length];
		for (int i = 0; i < effectImages.length; i++) {
			effectImages[i] = imageLoader.load("meff", val[i]);
		}
		//
		// interface
		monSelectSurface =  TadRepo.inst().ImageLoader().load("ifc", 15);
	}

	// public

	public Surface effectImage(int index) {
		return effectImages[index];
	}

	public Surface weaponAttackImage() {
		int index = Utils.random(0, 6);
		return effectImage(index);
	}
	
}