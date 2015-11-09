/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.menu.swing;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.captainsoft.TADr.TadLang;
import com.captainsoft.spark.utils.Log;

/**
 * Some utilities for AWT.
 *
 * @author mathias fringes
 */
public final class TADGuiToolkit {

	// fields
	
    public static final String LS = System.getProperty("line.separator");
        
    private static ArrayList<Image> iconImages = null;

    // constructors
    
    private TADGuiToolkit() {
        super();
    }

    // public

    public static void centerFrame(Window frame) {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((d.width - frame.getSize().width) / 2, (d.height - frame.getSize().height) / 2);      
    }     
     
    public static java.util.List<Image> getIconImages() {
        if (iconImages == null) {
            iconImages = new ArrayList<Image>();
            try {
                iconImages.add(ImageIO.read(new File(TadLang.folder() + "icon_big.png")));
                iconImages.add(ImageIO.read(new File(TadLang.folder() + "icon_sm.png")));
            } catch (IOException e) {
            	Log.error(e);    			
            }
        }
        return iconImages;
    }
    
}