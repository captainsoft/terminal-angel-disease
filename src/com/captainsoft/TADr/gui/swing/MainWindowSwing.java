/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.gui.swing;

import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.TADr.engine.commands.GameEngineCommand;
import com.captainsoft.TADr.engine.controller.ChiefMouseWheelController;
import com.captainsoft.TADr.menu.MenuController;
import com.captainsoft.TADr.menu.swing.TADGuiToolkit;
import com.captainsoft.spark.command.AbstractCommand;
import com.captainsoft.spark.control.key.KeyInput;
import com.captainsoft.TADr.engine.controller.MovementKeyState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The Swing main window.
 *
 * @author mathias fringes
 */
public final class MainWindowSwing extends JFrame {

    // fields

    private static final long serialVersionUID = 1L;

    private final KeyInput keyInput;

    private final MovementKeyState keyState;

    // constructors

    public MainWindowSwing(KeyInput keyInput, MovementKeyState keyState) {
        super("Captainsoft's The Terminal Angel Disease");
        this.keyInput = keyInput;
        this.keyState = keyState;

        setBackground(Color.BLACK);
        getContentPane().setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new GridBagLayout());
        //
        // width=971,height=663    win vista
        // width=963,height=654    win classic
        Dimension wd = new Dimension(971, 663);
        setSize(wd);
        setMinimumSize(wd);
        //
        addKeyListener(new MainFrameKeyListener());
        addWindowListener(new MainFrameWindowListener());
        addMouseWheelListener(new ChiefMouseWheelController());
        setIconImages(TADGuiToolkit.getIconImages());
    }

    // public

    public void setMainComponent(Component c) {
        if (c == null) {
            return;
        }
        if ((getContentPane().getComponentCount() == 1) && (getContentPane().getComponent(0) != c)) {
            remove(c);
            getContentPane().removeAll();
        }
        if (getContentPane().getComponentCount() == 0) {
            add(c);
        }
        pack();
    }

    //
    // nested classes


    private final class MainFrameKeyListener extends KeyAdapter {

        @Override
        public void keyPressed(final KeyEvent event) {
            if (event.getID() == KeyEvent.KEY_PRESSED) {

                final int keyCode = event.getKeyCode();

                TadRepo.inst().GameEngine().directCommand(new AbstractCommand("press key: " + keyCode) {
                    public void execute() {
                        keyInput.keyPress(keyCode);
                    }
                });

                keyState.press(keyCode);
            }

        }

        @Override
        public void keyReleased(KeyEvent event) {
            keyState.release(event.getKeyCode());
        }

    }

    private final class MainFrameWindowListener extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {

            TadRepo.inst().GameEngine().nextCommand(new GameEngineCommand("show quit game window") {

                public void execute() {
                    if (gameEngine.canShowDiskMenu()) {
                        new MenuController(gameEngine).showQuitGameFrame();
                    }
                }
            });
        }
    }

}