package com.captainsoft.tools.editorx.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import com.captainsoft.tools.editorx.framw.CsToolkit;


@SuppressWarnings("serial")
public class ToolPanel extends JPanel
{

	
	
	
	public ToolPanel()
	{
	
		
		// init interface		
		setBackground(Color.RED);
		
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc;
		setLayout(gbl);
		
		Component c;
			
		c = new JPanel();
		c.setSize(200, 200);
		gbc = CsToolkit.createGbc(0,0,1,1,100,0);
		gbl.setConstraints(c, gbc);				
		add(c);
		
		c = CsToolkit.createLabel("Sel: ");
		gbc = CsToolkit.createGbc(0,1,1,1,100,0,2);
		gbl.setConstraints(c, gbc);				
		add(c);
		
		
	}
	
	
	
}
