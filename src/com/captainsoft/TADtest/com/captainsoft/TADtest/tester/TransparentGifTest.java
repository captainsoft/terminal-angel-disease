/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADtest.tester;

import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.spark.ui.drawing.Surface;
import com.captainsoft.spark.ui.drawing.Surface;

public class TransparentGifTest {
	
	@SuppressWarnings("serial")
	public static void main(String[] args) {
	
		Frame f = new Frame();
		
		TadRepo repo = TadRepo.inst();
		try {
			repo.setUp();
		} catch (IOException e1) {			
			e1.printStackTrace();
		}
		f.setSize(300, 200);
		
		final Surface ts = repo.ImageLoader().load("mimg", 1);		
	 
		
		Surface itemImage = repo.ItemRepo().getImage(10);
		
		
		
		//
		final Surface s = new Surface(200, 200);
		s.clear(Color.RED);
		s.fill(Color.GREEN, 10, 10, 100, 100);
		s.blit(ts, 40, 30);
		
		s.blit(itemImage, 10, 5);
		
		f.add(new Component() {
			@Override
			public void paint(Graphics g) {
				g.drawImage(s.image(), 0, 0, this);
				//g.drawImage(image, 40, 30, 100, 100, this);
				//super.paint(g);
			}	
		});
		f.addWindowListener(new WindowListener() {			
			@Override
			public void windowOpened(WindowEvent e) {				
			}			
			@Override
			public void windowIconified(WindowEvent e) {				
			}			
			@Override
			public void windowDeiconified(WindowEvent e) {				
			}			
			@Override
			public void windowDeactivated(WindowEvent e) {			
			}			
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);				
			}			
			@Override
			public void windowClosed(WindowEvent e) {				
			}			
			@Override
			public void windowActivated(WindowEvent e) {			
			}
		});
			
		
		f.setVisible(true);
	}
		

}
