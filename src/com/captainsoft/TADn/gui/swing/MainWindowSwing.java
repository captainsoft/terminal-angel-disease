/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.gui.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.captainsoft.TADn.engine.controller.MainInputController;

/**
 * The AWT main window.
 *
 * @author mathias fringes
 */
public final class MainWindowSwing extends JFrame {

	// fields
	
	private static final long serialVersionUID = 1L;
	
	private final MainInputController mainWindowController;
	
	// constructors
	
	public MainWindowSwing(MainInputController mainWindowController) {
		super();
		this.mainWindowController = mainWindowController;
		
		this.setBackground(Color.BLACK);
		this.getContentPane().setBackground(Color.BLACK);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setLayout(new FlowLayout());			
		//
		// width=971,height=663    vista
		// width=963,height=654    classico  // TODO size (über content pane??)
		Dimension wd = new Dimension(971, 663);		
		this.setSize(wd);		
		this.setMinimumSize(wd);
	 	//
		addKeyListener(new MainFrameKeyListener());		
		addMouseListener(new MainFrameMouseListener());
		addWindowListener(new MainFrameWindowListener());				
	}
	
	public void setMainComponent(Component c) {
		if (c == null) {
			return;
		}
		if (getContentPane().getComponentCount() == 1 && getContentPane().getComponent(0) != c) {
			remove(c);
			getContentPane().removeAll();
		}
		if (getContentPane().getComponentCount() == 0) { 
			add(c, BorderLayout.CENTER);
		}
		pack();	
	}
		
	//
	
	private final class MainFrameKeyListener extends KeyAdapter {		
		@Override
		public void keyPressed(KeyEvent event) {
			if (event.getID() == KeyEvent.KEY_PRESSED) {					
				mainWindowController.keyPress(event.getKeyCode());				
			}
		}		
	}
	
	private final class MainFrameMouseListener extends MouseAdapter {		
		@Override
		public void mousePressed(MouseEvent event) {
			mainWindowController.click(event.getX(), event.getY(), event.getButton());
		}		
	}
	
	private final class MainFrameWindowListener extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {			
			mainWindowController.closeWindow();			
		}
	}

}