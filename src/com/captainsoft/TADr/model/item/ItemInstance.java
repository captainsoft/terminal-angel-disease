/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model.item;

/**
 * An concrete and special instance of an item entity.
 *
 * @author mathias fringes
 */
public enum ItemInstance {

    // members

    CoffeeCupEmpty(5),
    CoffeeCupFull(20),
    Sweets1(15),
    Sweets2(31),
    Sweets3(17),
    Sweets4(19),
    Limo(18),
    InstantCoffee(179),
    Lockpick(211);

    // fields

    private int id = 0;

    // constructors

    ItemInstance(int id) {
        this.id = id;
    }

    // public

    public int id() {
        return id;
    }

    public boolean match(Item item) {
        return ((item != null) && (item.id() == id));
    }

}
