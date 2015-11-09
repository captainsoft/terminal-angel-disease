/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.spark._sparkmuell.anim.anims;

import com.captainsoft.spark.control.Command;
import com.captainsoft.spark.utils.*;

/**
 *
 * @author mathias fringes
 */
public final class CommandAnimation extends IndexedAnimation<Command> {

	@Override
	protected void valueChange(Command value) {
		value.execute();		
	}
	
}
