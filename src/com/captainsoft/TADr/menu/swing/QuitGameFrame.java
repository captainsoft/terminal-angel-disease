/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.menu.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.captainsoft.TADr.menu.MenuController;
import com.captainsoft.spark.ui.box.TextBox;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui_swing.CloseWindowAdapter;
import com.captainsoft.spark.ui_swing.SwingBoxPanel;

/**
 * Dialog class asking whether the user really wants to quit.
 * Includes appropriate action listener.
 *
 * @author mathias fringes
 */
@SuppressWarnings("serial")
public final class QuitGameFrame extends BasicMenuDialog {
        
    // fields

    private JButton btnYes = null;
	private MenuController menuController;
    
    // constructors

    public QuitGameFrame(Window owner, MenuController menuController ) {
        super(owner, "Frage");
        this.menuController = menuController;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //
        init();
        TADGuiToolkit.centerFrame(this);
        setVisible(true);
    }

    // private
        
    private void init() {
    	//
    	setLayout(new FlowLayout());
    	setModal(true);      
        setSize(300, 130);        
        addWindowListener(new CloseWindowAdapter());    
        //
        // label        
        UiBoxContainer uc = new UiBoxContainer(0, 0, 260, 100);        
        uc.createSurface();
        uc.backgroundColor(Color.WHITE);
        TextBox box = new TextBox(260, 60);
        box.text = menuController.trans.word("menu.quit.really");
        box.font = this.getFont();
        box.color(Color.BLACK);
        uc.add(box);
        uc.update();
        SwingBoxPanel label = new SwingBoxPanel(uc);
                
        label.setPreferredSize(new Dimension(260, 60));        
        add(label);
        //
        // buttons
        JButton btnNo = new JButton(menuController.trans.word("menu.button.no"));
        btnNo.setPreferredSize(new Dimension(120, 25));      
        add(btnNo);
        //        
        btnYes = new JButton(menuController.trans.word("menu.button.yes"));      
        btnYes.setPreferredSize(new Dimension(120, 25));
        add(btnYes);
        //
        // action listener
        ActionListener al = new ButtonActionListener();
        btnNo.addActionListener(al);
        btnYes.addActionListener(al);
    }
    
    //
    // nested classes
    
    private final class ButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {        	
            if (e.getSource() == QuitGameFrame.this.btnYes) {            	
                QuitGameFrame.this.menuController.quitGame();
            } else {            	
                QuitGameFrame.this.setVisible(false);
            }
        }
    } 

}
