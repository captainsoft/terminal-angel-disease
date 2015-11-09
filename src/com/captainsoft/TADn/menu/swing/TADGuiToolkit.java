/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.menu.swing;

import java.awt.*;
import java.io.*;
import java.util.*;

import javax.imageio.*;

import com.captainsoft.spark.utils.*;

/**
 * Some utilities for AWT.
 *
 * @author mathias fringes
 */
public final class TADGuiToolkit {

	//
	
    public static final String LS = System.getProperty("java.lineSeperator");    
        
    private static ArrayList<Image> images = null;

    //
    
    private TADGuiToolkit() {
        super();
    }
    
    //

    public static void centerFrame(Window frame) {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((d.width - frame.getSize().width) / 2, (d.height - frame.getSize().height) / 2);      
    }     
     
    public static java.util.List<Image> getIconImages() {
        if (images == null) {
            images = new ArrayList<Image>();
            try {
                images.add(ImageIO.read(new File("files/icon_big.png")));
                images.add(ImageIO.read(new File("files/icon_sm.png")));
            } catch (IOException e) {
            	Log.error(e);    			
            }
        }
        return images;
    }
    
}