/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.gui.swing;

import java.awt.Color;

import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.mouse.BoxCommandManager;
import com.captainsoft.spark.ui.mouse.awt.AwtBoxMouseClickAdapter;
import com.captainsoft.spark.ui.swing.SwingBoxUpdater;
import com.captainsoft.spark.ui_swing.SwingBoxPanel;

/**
 * 
 *
 * @author mathias fringes
 */
public final class MainViewerSwingBoxCreator {
	
	private SwingBoxPanel backgroundPanel;
	private SwingBoxPanel scrollPanel;
	private SwingBoxPanel mapPanel; 
	private SwingBoxPanel overviewPanel;
	
	public SwingBoxPanel createSwingPanel(UiBoxContainer backgroundBox, 
			UiBoxContainer scrollBox, UiBoxContainer mapBox, UiBoxContainer overviewBox) {
		// create swing stuff		
		backgroundPanel = new SwingBoxPanel(backgroundBox);
		backgroundPanel.setLayout(null);
		backgroundPanel.setBackground(Color.RED);
				
		scrollPanel = new SwingBoxPanel(scrollBox);	
		scrollPanel.setBackground(Color.GREEN);
		scrollPanel.setLocation(15, 512);		
		backgroundPanel.add(scrollPanel);
		
		mapPanel = new SwingBoxPanel(mapBox);
		mapPanel.setBackground(Color.BLUE);
		mapPanel.setLocation(16, 56);
		
		backgroundPanel.add(mapPanel);
		
		overviewPanel = new SwingBoxPanel(overviewBox);
		overviewPanel.setBackground(Color.WHITE);
		overviewPanel.setLocation(709, 13);
		
		backgroundPanel.add(overviewPanel);					
		return backgroundPanel;
	}	
	
	public void registerCommands(BoxCommandManager backgroundMouseCommander, 
			BoxCommandManager mapMouseCommander, 
			BoxCommandManager overviewBoxCommander) {
		overviewPanel.addMouseListener(new AwtBoxMouseClickAdapter(overviewBoxCommander));
		backgroundPanel.addMouseListener(new AwtBoxMouseClickAdapter(backgroundMouseCommander));		
		mapPanel.addMouseListener(new AwtBoxMouseClickAdapter(mapMouseCommander));
	}
	
	public void registerUpdateManager(SwingBoxUpdater boxUpdateManager) {	
		boxUpdateManager.registerComponent(scrollPanel, scrollPanel.box());
		boxUpdateManager.registerComponent(mapPanel, mapPanel.box());
		boxUpdateManager.registerComponent(overviewPanel, overviewPanel.box());
		boxUpdateManager.registerComponent(backgroundPanel, backgroundPanel.box());
	}
	
}
