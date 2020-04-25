/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.menu;

/**
 * The API for the main game menu.
 *
 * @author mathias fringes
 */
public interface MenuViewer {

    public void close();

    public void display();

    public boolean isShowing();

    public void showLoadView();

    public void showMainMenuView();

    public void showSaveView();

    public void showSettingsView();

    public void showAboutView();

}