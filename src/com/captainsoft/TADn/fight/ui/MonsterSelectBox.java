/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.fight.ui;

import java.awt.Color;

import com.captainsoft.TADn.fight.FightImages;
import com.captainsoft.spark.ui.box.ImageBox;
import com.captainsoft.spark.ui.drawing.Surface;

/**
 * The little monster select boxes for each one of the monster party. 
 *
 * @author mathias fringes
 */
public final class MonsterSelectBox extends ImageBox {

	//
	
	public final int monsterPlaceIndex;	
	public boolean selected = false;	
	
	//
	
	MonsterSelectBox(int monsterPlaceIndex) {
		super(FightImages.inst().monSelectSurface);
		this.monsterPlaceIndex = monsterPlaceIndex;
		name = "MonsterSelectBox [" + monsterPlaceIndex + "]";
	}	
	
	//
	
	@Override
	protected void draw(Surface s) {	
		super.draw(s);
		if (selected) {
			s.color(Color.RED);
			s.rect(0, 0, width, height);
		}
	}

}