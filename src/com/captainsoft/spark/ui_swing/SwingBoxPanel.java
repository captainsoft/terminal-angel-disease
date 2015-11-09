/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.ui_swing;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.captainsoft.spark.ui.box.UiBoxContainer;

/**
 * A swing panel that draws the content of the Captainsoft's Spark framework
 * UiBoxContainer class.
 * 
 * @author mathias fringes
 */
@SuppressWarnings("serial")
public class SwingBoxPanel extends JPanel {

	// fields
		
	private final UiBoxContainer box;

	// constructors	

	public SwingBoxPanel(UiBoxContainer box) {
		super();
		this.box = box;		
		setSize(new Dimension(box.width, box.height));
		setPreferredSize(getSize());
	}
	
	// public
	
	public final UiBoxContainer box() {
		return box;
	}
	
	// overridden
	
	@Override
	public boolean isFocusable() {
		return false;
	}
	
	@Override
	public void paintComponent(Graphics g) {		
		synchronized(box) {
			g.drawImage(box.image(), box.x, box.y, this);
		}
	}

}