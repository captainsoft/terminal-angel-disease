/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.ui_swing;

import javax.swing.*;

/**
 * Utility class for Swing message boxes.
 *
 * @author mathias fringes
 */
public final class Messenger {

    // constructors

	private Messenger() {
		super();
	}

    // public
	
	public static void msg(String text) {
		JOptionPane.showMessageDialog(null, text);
	}
	
}
