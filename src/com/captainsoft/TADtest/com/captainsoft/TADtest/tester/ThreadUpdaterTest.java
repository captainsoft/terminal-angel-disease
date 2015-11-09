/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADtest.tester;

import java.awt.*;
import java.awt.event.*;

public class ThreadUpdaterTest {

	public ThreadUpdaterTest() {
		super();
		ThreadFrame d = new ThreadFrame();
		d.setVisible(true);
		final PaintPanel pp = new PaintPanel();
		d.add(pp);
		System.out.println("--ctor");
		
		new Thread(){
			public void run() {
		pp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {			
				System.out.println("CLICK!");
				new Thread() {					
					public void run() {
						pp.x = 1;
						for (int i = 0; i < 60; i += 3) {					
							sleeps(10);
							System.out.println("i do");
							pp.x += i;
							pp.repaint();
						}	
					}
				}.start();
				System.out.println("DISPATCH: " + EventQueue.isDispatchThread());
			}			
		});
			};
		}.start();
				
	
		
	}
	
	public static void main(String[] args) {
		new ThreadUpdaterTest();
		System.out.println("--main");
	}
	
	@SuppressWarnings("unused")
	private void sleeps(int m) {		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	//
	//
	
	@SuppressWarnings("serial")
	private static class ThreadFrame extends Frame {		
		public ThreadFrame() {
			super();
			this.setSize(400, 500);
			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {				
					System.exit(0);
				}
			});
		}
		
	}
	
	@SuppressWarnings("serial")
	private static class PaintPanel extends Panel {
		public Color back = Color.RED;
		public int x = 10;
		@Override
		public void paint(Graphics g) {			
			super.paint(g);
			this.setBackground(Color.black);
			g.clearRect(0, 0, 400, 500);
			g.setColor(back);
			g.fillRect(x, 10, 10, 10);
		}
	}
	
}
