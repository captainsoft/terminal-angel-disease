/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.ui_swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;

import javax.swing.JPanel;

/**
 * a panel with a single line drawn border
 *
 * @author mathias fringes
 */
@SuppressWarnings("serial")
public class BorderPanel extends JPanel {
	
	private Color borderColor = Color.BLACK;
	
	public BorderPanel() {
		this(null);						
	}
	
	public BorderPanel(LayoutManager layoutManager) {
		super(layoutManager);				
		setBackground(Color.WHITE);
	}
	
	@Override
	public void paint(Graphics g) {	
		super.paint(g);
		g.setColor(borderColor);
		g.drawRect(0, 0, getWidth()-1, getHeight()-1);
	}		

}
