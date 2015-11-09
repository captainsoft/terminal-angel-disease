/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.menu.swing;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import com.captainsoft.TADr.engine.Settings;
import com.captainsoft.TADr.menu.MenuController;
import com.captainsoft.spark.i18n.Translator;
import com.captainsoft.spark.ui.Fonts;
import com.captainsoft.spark.ui_swing.BorderPanel;

/**
 * The settings panel.
 *
 * @author mathias fringes
 */
@SuppressWarnings("serial")
final class SettingsPanel extends JPanel {

	// fields     
	
	public static final Font cursiveFont = new Font(Fonts.Verdana, Font.ITALIC, 10);
	
	private final Settings settings;
	
	private JButton backButton;
	private JButton pageButton;
	private JCheckBox chkMusic;
	private JCheckBox chkSounds;    
	private JSlider walkSpeedSlider;
	private NamesFormularPanel namesInputForm;

	// constructors

	public SettingsPanel(final MenuController menuController) {
		super();
		//
		this.settings = menuController.settings;
		//
		// init        
		setLayout(null);        
		setSize(MenuPanelCreator.PanelSize); 		
		MenuPanelCreator.addHeadLine(this, menuController.trans.word("menu.settings.caption"));    	    	  
		//
		// background panel
		CardLayout cl = new CardLayout();
		final JPanel backPanel = new JPanel(cl);    		
		backPanel.setBounds(2, 25, getWidth()-4, 130);
		add(backPanel, "");
		//
		final JPanel viewOne = this.createViewOne(menuController);
		backPanel.add(viewOne, "");
		cl.addLayoutComponent(viewOne, "one");
		//
		final JPanel viewTwo = this.createViewTwo(menuController.trans);       
		backPanel.add(viewTwo, "");
		cl.addLayoutComponent(viewTwo, "two");
		//
		// fill the controls with values
		namesInputForm.set(settings.nameOfPlayer(), settings.nameOfParty());
		chkMusic.setSelected(settings.playMusic);
		chkSounds.setSelected(settings.playSounds);
		walkSpeedSlider.setValue(Math.round(settings.scrollFactor * 100));
		//	
		// buttons
		pageButton = MenuPanelCreator.addCommandButton(this, menuController.trans.word("menu.settings.page"));
		pageButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == pageButton) {
					viewOne.setVisible(!viewOne.isVisible());						
					viewTwo.setVisible(!viewTwo.isVisible());									
				}
			}
		});
		backButton = MenuPanelCreator.addBackButton(this, menuController);
		backButton.addActionListener(new ActionListener() {			

			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == backButton) {
					updateSettings();						
					menuController.saveSettings();
				}
			}
		});
		enableButtons();
	}

	// private
	
	private void enableButtons() {
		backButton.setEnabled(namesInputForm.isValidInput());
	}

	private void updateSettings() {
		settings.nameOfPlayer(namesInputForm.nameOfPlayer());
		settings.nameOfParty(namesInputForm.nameOfParty());
		settings.playSounds = chkSounds.isSelected();
		settings.playMusic = chkMusic.isSelected();
		settings.scrollFactor = walkSpeedSlider.getValue() / 100f;		
	}

	private JPanel createViewOne(MenuController menuController) {
		JPanel pnl = createFormularPanel();
		//
		TextListener textActionListener = new TextListener() {

			public void textValueChanged(TextEvent e) {
				enableButtons();			
			}			
		};
		namesInputForm = new NamesFormularPanel(menuController.trans, textActionListener);
		namesInputForm.setLocation(2, 10);
		pnl.add(namesInputForm);	
		//
		return pnl;
	}

	private JPanel createViewTwo(Translator translator) {		
		JPanel pnl = createFormularPanel(); 
		//
		// check controls for music and sound
		chkMusic = new JCheckBox(translator.word("menu.settings.playMusic"));	
		chkMusic.setBackground(null);
		chkMusic.setBounds(10, 10, 160, 22);
		chkMusic.setFont(cursiveFont);
		pnl.add(chkMusic);
		//
		chkSounds = new JCheckBox(translator.word("menu.settings.playSounds"));
		chkSounds.setBackground(null);
		chkSounds.setBounds(10, 35, 160, 22);
		chkSounds.setFont(cursiveFont);
		pnl.add(chkSounds);
		//
		// control for scroll (walk) speed.
		JLabel capt = createDefLabel(translator.word("menu.settings.walkSpeed"));
		capt.setBounds(10, 60, 130, 22);
		pnl.add(capt);
		//
		walkSpeedSlider = new JSlider(50, 220);
		walkSpeedSlider.setBounds(10, 80, 140, 42);
		walkSpeedSlider.setExtent(30);
		walkSpeedSlider.setFont(cursiveFont);
		walkSpeedSlider.setInverted(true);
		walkSpeedSlider.setMinorTickSpacing(30);
		walkSpeedSlider.setPaintLabels(true);
		walkSpeedSlider.setPaintTicks(true);
		//
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put(new Integer(80), createDefLabel(translator.word("menu.settings.walkFast")));
		labelTable.put(new Integer(200), createDefLabel(translator.word("menu.settings.walkSlow")));
		walkSpeedSlider.setLabelTable(labelTable);		
		walkSpeedSlider.setPaintLabels(true);
		//
		pnl.add(walkSpeedSlider);
		//
		return pnl;
	}      

	private JPanel createFormularPanel() {
		BorderPanel pnl = new BorderPanel(null);		
		pnl.setBackground(Color.WHITE);
		return pnl;
	}
	
	private JLabel createDefLabel(String caption) {
		JLabel l = new JLabel(caption);
		l.setAlignmentX(LEFT_ALIGNMENT);
		l.setFont(cursiveFont);
		return l;
	}

}