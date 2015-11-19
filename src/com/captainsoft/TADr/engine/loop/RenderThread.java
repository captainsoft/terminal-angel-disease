/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.engine.loop;

import com.captainsoft.TADr.TadExceptionHandler;
import com.captainsoft.spark.utils.Log;

/**
 * Always running thread that spins the render loop.
 * 
 * @author mathias fringes
 */
public final class RenderThread extends Thread {

	// fields
	
	private final RenderLoop renderLoop;
    private final FPSCounter fps;

	private long ms = System.currentTimeMillis();

	// constructor
	
	public RenderThread(RenderLoop renderLoop) {
		super("RenderThread");
		this.renderLoop = renderLoop;
        this.fps = new FPSCounter();
	}

    // private

    private void gasp() {
        // gasp a little to give the system time to breathe.
        try {
            Thread.sleep(fps.sleep());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

	// Thread

	@Override
	public void run() {

	    try {
			while(true) {
				//
				long delta = System.currentTimeMillis() - ms;				
				if (delta > 0) {					
					ms = System.currentTimeMillis();
				}		
				//
				synchronized(this) {
					try {
						renderLoop.update(delta);
					} catch (Exception e) {
                        Log.force("ERROR");
                        renderLoop.clear();
						TadExceptionHandler.errorMessageAndMenu("An error during the game occurred! We are sorry...", e);
					}
				}
                //
                fps.count();
                gasp();
                //
            }
	    } catch (Throwable t) {
	    	TadExceptionHandler.errorMessageAndExit("A fatal error occurred! We are so sorry", t);
	    }
	}

}