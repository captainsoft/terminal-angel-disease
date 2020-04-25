/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.tools.editorx.gui;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.captainsoft.tools.editorx.controller.MainMenuBarController;
import com.captainsoft.tools.editorx.controller.MapEditViewController;
import com.captainsoft.tools.editorx.framw.Layouter;
import com.captainsoft.spark.ui.mouse.awt.AwtBoxMouseClickAdapter;
import com.captainsoft.spark.ui.swing.SingleComponentUpdater;
import com.captainsoft.spark.ui_swing.SwingBoxPanel;

/**
 * @author mathias fringes
 */
@SuppressWarnings("serial")
public final class TadEditorMainWindow extends JFrame {

    public TadEditorMainWindow() {
        super("TAD EDITOR");

        // init Interface
        setSize(880, 600);
        setBackground(Color.BLUE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // map panel
        SingleComponentUpdater mapPnlUpdater = new SingleComponentUpdater(null);

        final MapEditViewController mec = new MapEditViewController(mapPnlUpdater);

        // map panel ...swing
        SwingBoxPanel mapPnl = new SwingBoxPanel(mec.getBox());
        mapPnl.addMouseListener(new AwtBoxMouseClickAdapter(mec.getCommandManager()));
        mapPnl.setBackground(Color.GREEN);

        mapPnlUpdater.component(mapPnl);


        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

            public boolean dispatchKeyEvent(KeyEvent e) {


                if (e.getID() == KeyEvent.KEY_PRESSED) {
                    switch (e.getKeyCode()) {
                        case 68: // D

                            mec.removeDanger();
                            break;
                        case 87: // W

                            mec.saveMap();
                            break;
                        default:
                            return false;
                    }
                    return true;

                } else {
                    return false;
                }
            }
        });


        // other panels

        MainMenuBarController mc = new MainMenuBarController(mec);
        JPanel mnuPnl = new MainMenuBarView(mc);
        JPanel tolPnl = new ToolPanel();

        Layouter layout = new Layouter(getContentPane(), 2, 1);

        layout.set(mnuPnl, 0, 0, 2, 1, 100, 0);
        layout.set(tolPnl, 0, 1, 1, 1, 0, 100);
        layout.set(mapPnl, 1, 1, 1, 1, 100, 100);
        setLocation(100, 20);

        pack();
    }

}
