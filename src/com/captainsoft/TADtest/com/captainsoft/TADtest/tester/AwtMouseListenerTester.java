package com.captainsoft.TADtest.tester;

import java.awt.*;
import java.awt.event.*;

public class AwtMouseListenerTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//
		Frame frame = new Frame();
		frame.setLayout(null);
		frame.addWindowListener(new WindowAdapter() {		
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}			
		});				
		Panel p = new Panel();
		p.setLayout(new BorderLayout());
		p.setSize(150, 150);
		p.setBackground(Color.RED);
		
		Panel p2 = new Panel();
		p2.setSize(50, 50);
		p2.setBackground(Color.GREEN);
		p2.setBounds(10, 10, 200, 300);
		
		p.add(p2, BorderLayout.SOUTH);
		
		p.addMouseListener(new MouseListener() {			
			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("Mouse Released " + e);
			}			
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("Mouse Pressed " + e);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				System.out.println("Mouse Exited " + e);
			}			
			@Override
			public void mouseEntered(MouseEvent e) {	
				System.out.println("Mouse Entered " + e);
			}			
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Mouse Clicked " + e);
			}
		});
		
		p.addMouseMotionListener(new MouseMotionListener() {			
			@Override
			public void mouseMoved(MouseEvent e) {
				//System.out.println("Mouse Moved " + e);
			}			
			@Override
			public void mouseDragged(MouseEvent e) {	
				System.out.println("Mouse Dragged " + e);
			}
		});
		
		frame.add(p);		
		frame.setBounds(100, 100, 300, 300);
		frame.setVisible(true);

		
		//
		
	}

}
