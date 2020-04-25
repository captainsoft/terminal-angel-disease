/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.tools.editorx.controller;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.captainsoft.tools.editorx.Windows;
import com.captainsoft.tools.editorx.gui.TadEditorMainWindow;

/**
 * Controller for the main editor window.
 *
 * @author mathias fringes
 */
public final class MainWindowController {

    public MainWindowController() {
        super();
    }

    public void show() {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                showInternal();
            }
        });
    }

    private void showInternal() {
        JFrame window = new TadEditorMainWindow();
        Windows.mainWindow = window;

        window.setVisible(true);
    }

}