/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.engine.commands;

import java.awt.Color;

import com.captainsoft.TADn.cuts.CutScene;
import com.captainsoft.TADn.gui.MainViewer;
import com.captainsoft.spark.anim.Playable;
import com.captainsoft.spark.control.Command;
import com.captainsoft.spark.ui.SingleBoxUpdater;
import com.captainsoft.spark.ui.Updater;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.swing.SwingBoxUpdater;
import com.captainsoft.spark.ui_swing.SwingBoxPanel;

/**
 * 
 *
 * @author mathias fringes
 */
public abstract class ShowCutSceneCommand implements Command {

	// fields
	
	private final MainViewer mv;

	// constructors
	
	public ShowCutSceneCommand(MainViewer mv) {
		super();
		this.mv = mv;	
	}

	// protected
	
	protected void sceneIsOver() {
		return;
	}
	
	// abstract
	
	protected abstract CutScene createCutScene(Updater updater, UiBoxContainer backgroundBox);
	
	// Command
	
	@Override
	public final void execute() {
		//
		// ui....					
		UiBoxContainer backgroundBox = new UiBoxContainer(945, 617);
		backgroundBox.createSurface();			
		backgroundBox.backgroundColor(Color.BLACK);
		//
		final SwingBoxPanel p = new SwingBoxPanel(backgroundBox);					
		p.setBackground(Color.BLACK);					
		mv.setBackgroundPanel(p);		
		SwingBoxUpdater updateManager = new SwingBoxUpdater();		
		updateManager.registerComponent(p, backgroundBox);		
		Updater updater = new SingleBoxUpdater(backgroundBox, updateManager);										
		//
		// play...
		final CutScene outro = createCutScene(updater, backgroundBox);
		Thread t = new Thread() {
			@Override 
			public void run() {	
				int index = 1;
				Playable scene = null;
				while((scene = outro.createScene(index)) != null) {
					scene.play();
					index++;
				}
				sceneIsOver();
			};				
		};
		t.start();
	}
		
}
