/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.menux;

import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.drawing.Surface;
import com.captainsoft.spark.utils.Truth;

/**
 * A base for captainsoft mx framework windows.
 *
 * @author mathias fringes
 */
public class MxWindow extends UiBoxContainer {
	
	// fields
	
	public String title = "";

	// constructors
	
	public MxWindow() {
		super();		
		backgroundColor(WindowColors.bg);		
	}
	
	// overridden

	@Override
	protected void draw(Surface s) {			
		super.draw(s);	
		
		//						
		// title
		int title_height = 15;
		
		if (Truth.is(title)) {
			s.color(WindowColors.title_bg);
			s.fill(1, 1, width-2, title_height);
			
			s.color(WindowColors.title_br);
			s.line(0, 0, width - 2, 0);
			s.line(0, 0, 0, title_height);
			
			s.color(WindowColors.title_dk);
			s.line(1, title_height + 1, width - 1, title_height + 1);
			s.line(width - 1, 1, width - 1, title_height + 1);
			
			s.color(WindowColors.txt);
			s.font(WindowColors.title_fnt);			
			s.text(title, 4, 12);
			
		} else {
			title_height = -2;
		}

		//
		// main area
		
		int top = title_height + 2;
		
		s.color(WindowColors.br);
		s.line(0, top, width - 2, top);
		s.line(0, top - 1, 0, height - 2);
		//
		s.color(WindowColors.dk);
		s.line(width - 1, top + 1, width - 1, height - 2);
		s.line(1, height- 1, width, height - 1);		
	}
	
}