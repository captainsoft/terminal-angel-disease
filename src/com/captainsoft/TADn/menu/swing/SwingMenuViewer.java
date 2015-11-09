/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.menu.swing;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;

import com.captainsoft.TADn.Debugger;
import com.captainsoft.TADn.TAD;
import com.captainsoft.TADn.TadRepo;
import com.captainsoft.TADn.menu.MenuController;
import com.captainsoft.TADn.menu.MenuViewer;
import com.captainsoft.TADn.party.Game;
import com.captainsoft.spark.ui.Fonts;
import com.captainsoft.spark.ui.drawing.ImageSurface;
import com.captainsoft.spark.ui_swing.BorderPanel;
import com.captainsoft.spark.ui_swing.ImagePanel;

/**
 * Main Menu-Dialog with only the main buttons.
 *
 * @author mathias fringes
 */
@SuppressWarnings("serial")
public class SwingMenuViewer extends BasicMenuDialog implements MenuViewer {
	
	// fields
	
	private final Game game;
	private final MenuController menuController;
	
	private CardLayout cl = null;
	private Container backPanel = null;
	private MenuPanelCreator panelCreator = null;	
		
	// constructors
	
    public SwingMenuViewer(MenuController menuController, Game game) {
        super(null, "Captainsoft's The Terminal Angel Disease");        
        //        
        this.menuController = menuController;
        this.game = game;
        //
        panelCreator = new MenuPanelCreator(menuController);
        init();
        TADGuiToolkit.centerFrame(this);
    }
    
    // private
        
    private void init() {
    	//
    	// window
    	setModal(true);
        setSize(360, 405);
        setLayout(null);  
        addWindowListener(new StartUpListener());
        //
        // image
        Image tadLogoImage = new ImageSurface(TadRepo.inst().ImageLoader().load("ifc", 38)).image();        
        ImagePanel logoPanel = new ImagePanel(tadLogoImage);
        logoPanel.setBackground(Color.BLACK);
        logoPanel.grow(1);       
        logoPanel.setLocation(50, 18);
        logoPanel.setRenderStyle(ImagePanel.RENDER_CENTER);        
        add(logoPanel);             
        //
        // back & variable panels        
        backPanel = panelCreator.createEmptyPanel();
        cl = new CardLayout();
        backPanel.setLayout(cl);     
        //
        Container pl = panelCreator.createMainMenuPanel(game);
        cl.addLayoutComponent(pl, "main");
        Container plLoad = panelCreator.createLoadGamePanel();
        cl.addLayoutComponent(plLoad, "load");        
        Container plSave = this.panelCreator.createSaveGamePanel(game);
        cl.addLayoutComponent(plSave, "save");        
        Container plSettings = new SettingsPanel(menuController);
        cl.addLayoutComponent(plSettings, "sett");                             
        backPanel.add(plLoad, "load");
        backPanel.add(pl, "main");
        backPanel.add(plSave, "save");
        backPanel.add(plSettings, "sett");          
        backPanel.setLocation(58, 118);
        add(backPanel);
        //
        // homepage                    
        JPanel hPanel = new BorderPanel();     
        hPanel.setLayout(null);
        hPanel.setBounds(50, 320, logoPanel.getWidth(), 20);
        add(hPanel);
        Label hLabel = new Label(menuController.trans.word("menu.main.link"));
        hLabel.setAlignment(Label.CENTER);        
        hLabel.setBounds(1, 2, logoPanel.getWidth()-2, 15);
        hPanel.add(hLabel);        
        //
        // low copyright / version line
        add(MenuPanelCreator.createSeparator(15, 360, 330));        
        final Font smFont = new Font(Fonts.Verdana, Font.PLAIN, 9);
        // copyright label
        Label copyrightLabel = new Label(menuController.trans.word("menu.main.copyright"));
        copyrightLabel.setBounds(20, 360, 240, 20);
        copyrightLabel.setFont(smFont);
        add(copyrightLabel);        
        // version label
        Label versionLabel = new Label(menuController.trans.word("menu.main.version", TAD.Version) + (Debugger.inst.on ? "DEBUG" : ""));        
        versionLabel.setBounds(265, 360, 70, 20);        
        versionLabel.setAlignment(Label.RIGHT);
        versionLabel.setFont(smFont);
        add(versionLabel);
        //
        // last init and show               
        showMainMenuView();
    }    
    
    // nested classes
    
    private final class StartUpListener extends WindowAdapter {
    	@Override
        public void windowClosing(WindowEvent e) {
    		if (game == null) {
    			SwingMenuViewer.this.menuController.quitGame();    			
    		} else {
    			close();
    		}
        }
    }
    
    // MenuViewer
    
    @Override
    public void close() {
    	dispose();
    }
    
    @Override
    public void display() {
    	if (menuController.settings.isNewInstall()) {
    		menuController.showSettingsView();
    	}    	
    	setVisible(true);
    }
    
    @Override
    public void showLoadView() {
    	cl.show(this.backPanel, "load");
    }
    
    @Override
    public void showMainMenuView() {
    	cl.show(this.backPanel, "main");
    }
    
    @Override    
    public void showSaveView() {
    	cl.show(this.backPanel, "save");
    }
    
    @Override
    public void showSettingsView() {
    	cl.show(this.backPanel, "sett");
    }
    
}
