/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.model.i18n;

import com.captainsoft.TADn.model.TadTranslator;

/**
 * Interface for i18n-ized GuiMessages!
 *
 * @author mathias fringes
 */
public interface GuiMessages {
	
	String np = TadTranslator.np;
	String prt = TadTranslator.prt;
	
	/**
	 * Get all the gui message data.
	 * 
	 * @return
	 */
	public String[] data();

}