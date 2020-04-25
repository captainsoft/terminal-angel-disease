/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model.i18n;

/**
 * Interface for i18n-iced GuiMessages!
 *
 * @author mathias fringes
 */
public interface GuiMessages {

    final String np = TadTranslator.np;

    final String prt = TadTranslator.prt;

    final String br = " <br> ";

    final String br2 = br + br;

    final String sep = "&&&&";

    /**
     * Get all the gui message data.
     *
     * @return
     */
    public String[] data();

}