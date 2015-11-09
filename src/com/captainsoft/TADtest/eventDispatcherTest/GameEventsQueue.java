/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package eventDispatcherTest;

import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 
 *
 * @author mathias fringes
 */
public class GameEventsQueue {
	
	private final BlockingQueue<String> events;
	
	public GameEventsQueue() {
		events = new LinkedBlockingQueue<String>();
	}

	//
	
	public void clear() {
		events.clear();
	}
	
	public String get() throws InterruptedException {		
		return events.take();		
	}
	
	public void put(String event) {
		events.offer(event);
	}
	
	public int size() {
		return events.size();
	}
	
	//
	
	@Override
	public String toString() {
		String ts = "S:" + size() + ">>";
		for(String s : events) {
			ts += s + "|";
		}
		return ts;
	}
	
}