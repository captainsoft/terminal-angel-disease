/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.ui.mouse.awt;

import java.awt.event.*;

import com.captainsoft.spark.ui.mouse.*;

/**
 * @author mathias fringes
 */
public class AwtBoxMouseMoveAdapter implements MouseMotionListener {
		
	private final BoxCommandManager boxCommandManager;
	
	public AwtBoxMouseMoveAdapter(BoxCommandManager boxCommandManager) {
		this.boxCommandManager = boxCommandManager;
	}

	public void mouseMoved(MouseEvent e) {
		boxCommandManager.mouseMove(e.getX(), e.getY());
	}

	public void mouseDragged(MouseEvent e) {							
	}
	
}