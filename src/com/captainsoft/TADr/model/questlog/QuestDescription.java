/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model.questlog;

/**
 * A concrete quest description.
 *
 * @author mathias fringes
 */
public final class QuestDescription {

    // fields

    public final int id;

    public final int coins;

    public final String text;

    // constructors

    public QuestDescription(int id, int coins, String text) {
        this.id = id;
        this.coins = coins;
        this.text = text;
    }

    // overridden

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof QuestDescription)) {
            return false;
        }
        return ((QuestDescription) o).id == id;
    }

}