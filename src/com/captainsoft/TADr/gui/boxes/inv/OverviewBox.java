/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.gui.boxes.inv;

import java.awt.Font;

import com.captainsoft.TADr.model.party.Party;
import com.captainsoft.spark.ui.Fonts;
import com.captainsoft.spark.ui.box.TextBox;
import com.captainsoft.spark.ui.box.UiBox;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.drawing.Surface;

/**
 * The party overview box, with all four members and their fun points.
 *
 * @author mathias fringes
 */
public final class OverviewBox extends UiBoxContainer {

    // fields

    public final TextBox partyName;
    public final UiBox showInvBox;

    private final PointsBarBox[] pointsBarBoxs;

    // constructors

    public OverviewBox(Surface overviewBackgroundSurface) {
        super();
        name = "overview-box";
        Surface f = overviewBackgroundSurface.stamp(709, 13, 223, 560);
        initBackground(f);

        //
        partyName = new TextBox();
        partyName.font = new Font(Fonts.Times, Font.BOLD, 18);
        partyName.center();
        partyName.set(15, 10, 190, 55);
        add(partyName);
        //
        pointsBarBoxs = new PointsBarBox[4];
        for (int i = 0; i < 4; i++) {
            pointsBarBoxs[i] = new PointsBarBox();
            pointsBarBoxs[i].name = "pointsBarBox_" + (i + 1);
            pointsBarBoxs[i].pos(i % 2 == 0 ? 22 : 118, i < 2 ? 266 : 491);
            pointsBarBoxs[i].stampBackground(f);
            add(pointsBarBoxs[i]);
        }
        //
        showInvBox = new UiBox(3, 532, 217, 25);
        showInvBox.name = "showInvBox";
        add(showInvBox);
    }

    // public

    public void setParty(Party party) {
        partyName.text = party.name();
        for (int i = 0; i < 4; i++) {
            pointsBarBoxs[i].member = party.member(i + 1);
        }
    }

    public UiBox getBarBox(int nr) {
        return pointsBarBoxs[nr - 1];
    }

}
