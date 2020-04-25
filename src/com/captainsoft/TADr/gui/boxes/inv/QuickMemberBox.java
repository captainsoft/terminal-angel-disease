/*

 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.gui.boxes.inv;

import java.awt.Color;

import com.captainsoft.spark.ui.box.UiDrawBox;
import com.captainsoft.spark.ui.drawing.Surface;

/**
 * Quick selection box for party members.
 *
 * @author mathias fringes
 */
public final class QuickMemberBox extends UiDrawBox {

    // fields

    private static final Color colSelected = new Color(255, 192, 192);

    public boolean selected = false;

    // constructors

    public QuickMemberBox() {
        super();
        size(26, 29);
    }

    // UiDrawBox

    @Override
    protected void draw(Surface s) {
        super.draw(s);
        if (selected) {
            s.rect(colSelected, 1, 1, 26 - 2, 29 - 2);
        }
    }

}
