/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.spark._sparkmuell.anim;

import com.captainsoft.spark.ui.box.UiBox;
import com.captainsoft.spark.ui.swing.SwingBoxUpdater;
import com.captainsoft.spark.utils.Sleeper;

/** 
 *
 * @author mathias fringes
 */
public final class Scene {
	
	//	
	
	private Animation animation;
	private SwingBoxUpdater updater;
	private int sleep = 100;	
	private UiBox screen;
	
	//
	
	public Scene(UiBox screen, SwingBoxUpdater mg) {
		super();
		this.screen = screen;
		this.updater = mg;		
	}
	
	// 
	
	public void animate(Animation animation) {
		this.animation = animation;
	}
	
	public void blur(float scale) {
		sleep = Math.round(sleep * scale);
	}	
		
	public void play() {
					
		int frameIdx = Frame.FIRST.no();
		Sleeper sleeper = new Sleeper(sleep);
		
		animation.reset();
		while (true) {

			sleeper.reset();						
			PlayState playState = animation.play(frameIdx);			
			
			// update and wait and stuff
			if (playState.isUpdateState()) {
				updater.updateBoxDrawing(screen);
			}
			//					
			frameIdx++;
			
			sleeper.sleep();
			
			if (playState == PlayState.Finished) {					
				break;
			}			
			
		}
					
	}
		
}
