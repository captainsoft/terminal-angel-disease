/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.ui.mouse.awt;

import java.awt.event.*;

import com.captainsoft.spark.control.*;
import com.captainsoft.spark.ui.mouse.*;

/**
 * @author mathias fringes
 */
public class AwtBoxMouseClickAdapter implements MouseListener {

    private final BoxCommandManager boxCommandManager;

    public AwtBoxMouseClickAdapter(BoxCommandManager boxCommandManager) {
        this.boxCommandManager = boxCommandManager;
    }

    public void mouseReleased(MouseEvent e) {
        boolean doubleCick = (e.getClickCount() == 2);
        MouseButton button = MouseButton.get(e.getButton());
        boxCommandManager.mouseReleased(e.getX(), e.getY(), button, doubleCick);
    }

    public void mousePressed(MouseEvent e) {
        boxCommandManager.mousePressed(e.getX(), e.getY());
    }

    public void mouseExited(MouseEvent e) {
        boxCommandManager.mouseExited();
    }

    public void mouseEntered(MouseEvent e) {
        boxCommandManager.mouseEntered();
    }

    public void mouseClicked(MouseEvent e) {
    }

}