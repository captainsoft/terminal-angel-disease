/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.spark._sparkmuell.anim;

import java.util.*;

import com.captainsoft.spark.control.Command;
import com.captainsoft.spark.utils.*;

/**
 * 
 *
 * @author mathias fringes
 */
public final class Movie {
	
	//
	
	private Command command;
	private List<Scene> scenes;	
	
	//
	
	public Movie() {
		super();
		scenes = new ArrayList<Scene>();
	}
	
	public Movie(Scene scene) {
		this();
		addScene(scene);
	}
	
	//
		
	public void command(Command command) {
		this.command = command;
	}
	
	public void addScene(Scene s) {
		scenes.add(s);		
	}

	public void play() {
		Thread t = new Thread() {
			public void run() {
				for(Scene s : scenes) {
					s.play();
				}					
			};			
		};
		t.start();
		try {
			t.join();
			System.out.println("That was this movie!");			
		} catch (InterruptedException e) {			
		}
		if (command != null) {
			command.execute();
		}
	}
	
}
