package com.captainsoft.TADn._render;

import com.captainsoft.spark.StoppableThread;


/**
 * 
 * @author mathias
 */
public final class RenderThread extends StoppableThread  {

	// fields
	
	private final RenderLoop renderLoop;
	
	private boolean pause = false;
	private long ms = System.currentTimeMillis();

	// constructor
	
	public RenderThread(RenderLoop renderLoop) {
		this.renderLoop = renderLoop;
	}
	
	// public
	
	public synchronized void pause(boolean pause) {
		this.pause = pause;		
	}

	// StoppableThread 
	
	@Override
	protected void doWork() {
		if (ms == 0) {
			ms = System.currentTimeMillis();
		}
		long delta = System.currentTimeMillis() - ms;
		long elapsed = 0;
		if (delta > 100) { // 1/10 second
			elapsed = delta;
			ms = System.currentTimeMillis();
		}
		//
		synchronized(this) {
			if (!pause) {
				renderLoop.status(elapsed);
				renderLoop.render();	
			}			
		}			
	}

	@Override
	protected int waitMillies() {
		return 0;
	}

}