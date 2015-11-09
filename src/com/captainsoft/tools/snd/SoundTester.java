/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.tools.snd;

import com.captainsoft.TADr.engine.excp.GameDataIoException;
import com.captainsoft.TADr.sound.SndPlayer;
import com.captainsoft.TADr.sound.VbSndPlayer;
import com.captainsoft.spark.utils.Utils;


public class SoundTester {
	
	SndPlayer player;
	
	public SoundTester(SndPlayer player) {
		super();
		this.player = player;		
	}
	
	//
	
	private void playSifc() {
		final String k = "sifc";
		for (int i = 0; i < 30; i++) {
			playSound(k, i);
		}
	}
	
	private void playSmat() {
		final String k = "smat";
		for (int i = 1; i < 12; i++) {
			playSound(k, i);
		}
		playSound(k, 99);
	}
	
	private void playSmpr() {
		final String k = "smpr";
		
		for (int i = 1; i < 12; i++) {
			playSound(k, i);
			// 4, 11  is not
		}
		// playSound(k, 9);
		playSound(k, 99);
	}
	
	private void playSscl() {
		final String k = "sscl";
		for (int i = 0; i < 12; i++) {
			playSound(k, i);			
		}
	}
	
	// 
	
	private void playSound(String k, int i) {
		try { 
			player.playSound(k, i);
			Utils.sleepySecs(3);
		} catch(GameDataIoException e) {
			String msg = e.getMessage();
			System.err.println(msg);
			if (msg.contains("playing")) {						
				System.err.println(e.getCause().getMessage());
			}			
		}
	}
	
	//
	
	public static void main(String[] args) {
		System.out.println("HELLO!");
		VbSndPlayer player = new VbSndPlayer();
		player.enabled(true);
		SoundTester st = new SoundTester(player);
		//
		st.playSifc();
		// st.playSmat();
		// st.playSmpr();
		// st.playSscl(); // !!! i not compiled by ResCompileer.java
		System.out.println("DONE!");
	}

}
