/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.menu.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.TextListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.captainsoft.spark.i18n.Translator;
import com.captainsoft.spark.ui.Fonts;
import com.captainsoft.spark.utils.Truth;

/**
 * Formular panel for name of player and name of party.
 * 
 * @author mathias fringes
 */
@SuppressWarnings("serial") 
class NamesFormularPanel extends JPanel {

	// fields
		
	private static final Font systemFont = new Font(Fonts.Courier, Font.PLAIN, 11);

	private final TextField nameOfPartyTxtField;
	private final TextField nameOfPlayerTxtField;  
	
	// constructors

	public NamesFormularPanel(Translator trans, TextListener al) {
		super();               
		//
		setLayout(null);
		setSize(new Dimension(224, 90));
		setBackground(Color.WHITE);
		setForeground(Color.BLACK);
		//
		// text input fields
		nameOfPlayerTxtField = new TextField();
		nameOfPlayerTxtField.addTextListener(al);
		nameOfPlayerTxtField.setFont(systemFont);
		nameOfPlayerTxtField.setBounds(5, 5, 210, 22);
		add(nameOfPlayerTxtField);
		//
		nameOfPartyTxtField = new TextField();
		nameOfPartyTxtField.addTextListener(al);
		nameOfPartyTxtField.setFont(systemFont);               
		nameOfPartyTxtField.setBounds(5, 50, 210, 22);
		add(nameOfPartyTxtField);	
		//
		// ... and their labels
		JLabel lbl = new JLabel(trans.word("menu.settings.namePlayer"));
		lbl.setBounds(5, 25, 210, 20);
		lbl.setFont(SettingsPanel.cursiveFont);
		add(lbl);
		//
		lbl = new JLabel(trans.word("menu.settings.nameParty"));
		lbl.setBounds(5, 70, 210, 20);
		lbl.setFont(SettingsPanel.cursiveFont);
		add(lbl);
	}
	
	// public
	
	public void set(String player, String party) {
		nameOfPartyTxtField.setText(party);
		nameOfPlayerTxtField.setText(player);
	}
	
	public String nameOfPlayer() {
		return nameOfPlayerTxtField.getText();
	}
	
	public String nameOfParty() {
		return nameOfPartyTxtField.getText();
	}

	public boolean isValidInput() {
		return (Truth.isLenient(nameOfParty()) && Truth.isLenient(nameOfPlayer()));		
	}

}