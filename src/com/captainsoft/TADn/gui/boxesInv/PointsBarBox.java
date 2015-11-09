/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.gui.boxesInv;

import java.awt.Color;

import com.captainsoft.TADn.gui.boxesCommon.BarBox;
import com.captainsoft.TADn.party.PartyMember;
import com.captainsoft.spark.ui.drawing.Surface;

/**
 *
 * @author mathias fringes
 */
public final class PointsBarBox extends BarBox {

	private static final Color colDefault = new Color(83, 153, 89);
	
	public PartyMember member = null;	
	
	public PointsBarBox() {
		super();
		color = colDefault;
		size(82, 10);
	}
	
	@Override
	protected void draw(Surface s) {
		cur = member.fun.cur();
		max = member.fun.max();
		super.draw(s);
	}
	
}