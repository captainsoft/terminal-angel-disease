/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.tools.editorx.controller;

import javax.swing.JDialog;

import com.captainsoft.tools.editorx.Windows;
import com.captainsoft.tools.editorx.gui.MapPropertyWindow;
import com.captainsoft.spark.utils.Utils;

/**
 * @author mathias fringes
 */
public final class MapPropertyWindowController {

    private MapEditViewController mapEditController;
    private JDialog wnd;

    public MapPropertyWindowController(MapEditViewController mapEditController) {
        super();
        this.mapEditController = mapEditController;
    }

    public void show() {
        wnd = new MapPropertyWindow(Windows.mainWindow, this);
        wnd.setVisible(true);
    }

    public void close() {
        wnd.dispose();
    }

    public void loadMap(String map) {
        Integer mapInt = Utils.tryParse(map, 1, 20);
        if (mapInt != null) {
            mapEditController.loadMap(mapInt);
            close();
        }
    }

}