/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.gui.boxes.main;

import java.awt.Color;
import java.awt.Font;

import com.captainsoft.TADr.model.ScrollText;
import com.captainsoft.TADr.model.party.PartyMemberFactory;
import com.captainsoft.spark.ui.Fonts;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.drawing.Surface;

/**
 * The scroll text box.
 *
 * @author mathias fringes
 */
public final class ScrollBox extends UiBoxContainer {

    // fields

    private final ScrollText scrollText;

    private static Color[] typeColors = new Color[5];

    // static

    static {
        typeColors[0] = new Color(35, 35, 35);
        typeColors[1] = PartyMemberFactory.pm1;
        typeColors[2] = PartyMemberFactory.pm2;
        typeColors[3] = PartyMemberFactory.pm3;
        typeColors[4] = PartyMemberFactory.pm4;
    }

    // constructors

    public ScrollBox(Surface background, ScrollText scrollText) {
        super("scrollbox");
        this.scrollText = scrollText;
        initBackground(background);
        surface().turnAliasOn();
        surface().font(new Font(Fonts.Times, Font.BOLD, 18));
    }

    // UiBoxContainer

    @Override
    public void draw(Surface s) {
        super.draw(s);
        for (int i = 0; i < scrollText.length(); i++) {
            s.color(typeColors[scrollText.type(i)]);
            s.text(scrollText.text(i), 7, i * 22 + 18);
        }
    }

}