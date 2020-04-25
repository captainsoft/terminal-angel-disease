/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model.party;

import static com.captainsoft.spark.utils.Truth.is;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.captainsoft.TADr.model.item.Item;
import com.captainsoft.spark.collect.ForIndex;

/**
 * The complete inventory for one party member, including bag content and equipment.
 * Calculates weight when adding/removing items.
 *
 * @author mathias fringes
 */
public final class Inventory {

    // static

    static final int NO_INDEX = -1;

    public static final int CAPACITY = 29;

    public static final List<Integer> bagPositions;

    static {
        List<Integer> bp = new ArrayList<Integer>();
        for (int i = 0; i < 20; i++) {
            bp.add(i);
        }
        bagPositions = Collections.unmodifiableList(bp);
    }

    // fields

    private final Item[] items;

    private int weight;

    // constructors

    public Inventory() {
        super();
        this.items = new Item[CAPACITY];
        this.weight = 0;
    }

    // accessors

    public Item item(InventoryPlace pos) {
        return item(pos.index());
    }

    public void item(InventoryPlace pos, Item item) {
        item(pos.index(), item);
    }

    public Item item(int index) {
        return items[index];
    }

    public void item(int index, Item item) {
        remove(index);
        items[index] = item;
        if (item != null) {
            weight += item.weight();
        }
    }

    public int weight() {
        return weight;
    }

    // public  

    public boolean empty(int index) {
        return (items[index] == null);
    }

    public int findIndex(int itemId) {

        ForIndex<Item> iterator = new ForIndex<Item>(items);
        for (Item item : iterator) {
            if (is(item) && (item.id() == itemId)) {
                return iterator.index();
            }
        }
        return NO_INDEX;
    }

    public void remove(int index) {
        if (items[index] != null) {
            weight -= items[index].weight();
            items[index] = null;
        }
    }

}