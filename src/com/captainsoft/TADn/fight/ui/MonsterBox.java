/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.fight.ui;

import com.captainsoft.TADn.TadRepo;
import com.captainsoft.TADn.fight.Monster;
import com.captainsoft.TADn.loader.ImageLoader;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.drawing.Surface;

/**
 * UiBox for monsters. 
 *
 * @author mathias fringes
 */
public final class MonsterBox extends UiBoxContainer {
	
	// fields
	
	private boolean isHops = false;
	private Surface monsterImage;
		
	// constructors
	
	MonsterBox(int width, int height, Monster monster) {
		super();
		this.size(width, height);	
		this.name = "MonsterMox [" + monster.name + "]";
		ImageLoader loader = TadRepo.inst().ImageLoader();
		monsterImage = loader.load("mimg", monster.image, width, height);			
	}
	
	//
	
	public void hops() {
		if (isHops) {
			return;
		}
		y -= 10;
		isHops = true;
	}
	
	public void rehops() {
		if (!isHops) {
			return;
		}
		y += 10;
		isHops = false;		
	}
	
	//
	
	@Override
	protected void draw(Surface s) {		
		s.blit(monsterImage);
	}

}
