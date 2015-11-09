/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.model.puzzle.book;

import java.awt.Color;
import java.awt.Font;

import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.spark.ui.Fonts;
import com.captainsoft.spark.ui.box.TextBox;
import com.captainsoft.spark.ui.box.UiBoxContainer;

/**
 * Window for displaying book pages.
 *
 * @author mathias fringes
 */
final class BookWindow extends UiBoxContainer {
	
	private TextBox textBox;
	
	public BookWindow() {
		super();		
		initBackground(TadRepo.inst().ImageLoader().load("ifc", 1));
		//
		// text box
		textBox = new TextBox();
		textBox.color(new Color(40, 40, 40));
		textBox.font = new Font(Fonts.Times, Font.BOLD, 18);
		textBox.alignLeft();
		textBox.set(20, 20, 250, 250);
		add(textBox);		
	}
	
	public void text(String text) {
		textBox.text = text;	
	}

}