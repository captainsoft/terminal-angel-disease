/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.tools.editorx.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.captainsoft.tools.editorx.controller.MapPropertyWindowController;
import com.captainsoft.tools.editorx.framw.Layouter;

/**
 * 
 * @author mathias fringes
 */
@SuppressWarnings("serial")
public final class MapPropertyWindow extends JDialog {

	private MapPropertyWindowController controller;
	private JTextField mapInput;

	public MapPropertyWindow(JFrame parent, MapPropertyWindowController controller) {
		super(parent);
		this.controller = controller;
		
		this.setLocationRelativeTo(parent);
		
		Layouter layout = new Layouter(getContentPane(), 3, 1);
		
		mapInput = new JTextField(10);
		JButton doit = new JButton("load");
		doit.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				clickLoadMap();
			}
		});
		
		layout.set(mapInput, 0, 0, 2, 1, 100, 0);
		layout.set(doit, 0, 1, 1, 1, 0, 0);
		pack();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
	}
	
	private void clickLoadMap() {		
		controller.loadMap(mapInput.getText());			
	}
	
}
