/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.tools.editorx;

import com.captainsoft.TADr.TadLang;
import com.captainsoft.tools.editorx.controller.MainWindowController;

/**
 * Project to edit old TAD maps. Started in February 2014, absolutely not finished!
 * 
 * @author mathias fringes
 */
public final class TadEditor {
	
	// constructors
	
	public TadEditor() {
		super();	
	}

	// private
	
	private void showGui() {		
		MainWindowController mw = new MainWindowController();
		mw.show();
	}
	
	// main
	
	public static void main(String[] args) {
        TadLang.toGerman();
        TadEditor tadEditor = new TadEditor();
		tadEditor.showGui();			
	}
	
}