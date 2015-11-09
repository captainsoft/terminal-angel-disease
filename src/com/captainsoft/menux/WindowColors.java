/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.menux;

import java.awt.Color;
import java.awt.Font;

import com.captainsoft.spark.ui.Fonts;

/**
 * Set of default MX colors.
 *
 * @author mathias fringes
 */
public final class WindowColors {

    // fields

	public static Color txt;
	public static Color b_br;
	public static Color b_bg; 
	public static Color b_dk;
	
	public static Color br;
	public static Color bg; 
	public static Color dk;
	
	public static Color title_dk;
	public static Color title_br;
	public static Color title_bg;
	public static Font title_fnt;

    // static

	static {
		oldVbColors();		
	}

    // constructors
	
	private WindowColors() {	
		super();
	}

    // public

	public static void oldVbColors() {
		b_dk = new Color(32, 32, 72);
		b_br = new Color(72, 72, 152);
		b_bg = new Color(64, 64, 128);
		txt = new Color(229, 229, 229);
		
		// dialog
		dk = new Color(52, 52, 100);
		br = new Color(76, 76, 156);
		bg = new Color(64, 64, 128);
		
		// title
		title_dk = new Color(64, 64, 128);
		title_br = new Color(92, 92, 180);
		title_bg = new Color(76, 76, 156);
        title_fnt = new Font(Fonts.Verdana, Font.BOLD, 12);
	}
	
	/*
	public static void someOtherColors() {
		b_dk = new Color(32, 32, 72);
		b_br = new Color(65, 105, 117);
		b_bg = new Color(91, 147, 164);
		txt = new Color(229, 229, 229);
	}
	*/

}