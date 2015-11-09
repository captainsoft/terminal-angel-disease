/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.menu.swing;

import java.awt.Window;

import javax.swing.JDialog;

/**
 * Base class for all menu dialogs.
 *
 * @author mathias fringes
 */
@SuppressWarnings("serial")
class BasicMenuDialog extends JDialog {
    
    // constructors   

    public BasicMenuDialog(Window owner, String title) {
        super(owner, title);
        setResizable(false);
        setIconImages(TADGuiToolkit.getIconImages());           
    }    

}