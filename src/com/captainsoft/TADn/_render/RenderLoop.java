package com.captainsoft.TADn._render;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author mathias
 */
public final class RenderLoop {
	
	//
	
	private List<AnimationCommand> commands = new ArrayList<AnimationCommand>();
	
	//
	
	public RenderLoop() {
		
	}
	
	// public
	
	public void status(long elapsed) {
		
		//
		if (elapsed > 100) {
			if (commands.size() > 0) {
				Iterator<AnimationCommand> it = commands.iterator();
				while(it.hasNext()) {
					AnimationCommand ac = it.next();
					ac.deduct(elapsed);
					if (ac.over()) {
						ac.execute();
						it.remove();
					}
				}				
			}
		}
	}	
	
	public void render() {
		
	}

}