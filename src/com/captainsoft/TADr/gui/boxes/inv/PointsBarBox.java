/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.gui.boxes.inv;

import java.awt.Color;

import com.captainsoft.TADr.gui.boxes.common.BarBox;
import com.captainsoft.TADr.model.party.PartyMember;
import com.captainsoft.spark.ui.drawing.Surface;

/**
 * FunPoints display bar box for one party member.
 *
 * @author mathias fringes
 */
public final class PointsBarBox extends BarBox {

    // fields

    private static final Color colDefault = new Color(83, 153, 89);

    public PartyMember member = null;

    public PointsBarBox() {
        super();
        color = colDefault;
        size(82, 10);
    }

    // overridden

    @Override
    protected void draw(Surface s) {
        cur = member.fun.cur();
        max = member.fun.max();
        super.draw(s);
    }

}