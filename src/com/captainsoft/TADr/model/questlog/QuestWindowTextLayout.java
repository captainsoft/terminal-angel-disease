/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model.questlog;

import com.captainsoft.spark.ui.Fonts;
import com.captainsoft.spark.ui.box.TextBox;
import com.captainsoft.spark.ui.box.UiBoxContainer;

import java.awt.*;

/**
 * Layout manager specially for the quest log box.
 *
 * @author mathias fringes
 */
final class QuestWindowTextLayout {

    // fields

    private final UiBoxContainer target;
    private final Font markFont = new Font(Fonts.Courier, Font.PLAIN, 10);
    private final Font textFont = new Font(Fonts.Verdana, Font.PLAIN, 12);

    private int y = 0;

    // constructors

    public QuestWindowTextLayout(UiBoxContainer target) {
        this.target = target;
        y = 25;
    }

    // public

    public boolean stillSpace() {
        return (y < target.height - 10);
    }

    public boolean add(boolean solved, String second) {

        // solve box
        TextBox b1 = new TextBox();
        b1.font = markFont;
        b1.width = 20;
        b1.height = 15;
        b1.text = solved ? "x" : "o";
        b1.color(solved ? new Color(98, 211, 46) : new Color(233, 107, 19));
        b1.alignLeft();
        b1.pos(10, y - 1);
        target.add(b1);

        // text box
        TextBox b2 = new TextBox();
        b2.color(solved ? new Color(80, 150, 80) : new Color(248, 248, 248));
        b2.font = textFont;
        b2.width = target.width - 50;
        b2.height = 50;
        b2.text = second;
        b2.alignLeft();
        b2.pos(25, y);
        target.add(b2);
        b2.update();
        b2.height = 17 * b2.lines();

        y += b2.height + 5;

        // check for next page
        if (!stillSpace()) {
            target.remove(b1);
            target.remove(b2);
            return false;
        }


        return true;
    }

}