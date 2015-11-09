/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model.questlog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.captainsoft.spark.utils.Truth.is;
import static com.captainsoft.spark.utils.Truth.not;

/**
 * The current list of quest logs for the party.
 *
 * @author mathias fringes
 */
public final class PartyQuestLog {

    // fields

    private final List<Item> items = new ArrayList<Item>();

    // constructors

    public PartyQuestLog() {
        super();
    }

    // accessors

    public List<Item> items() {
        return items;
    }

    // public

    public void randomize() {
        Collections.shuffle(items);
    }

    public void add(QuestDescription questDescription, boolean solved) {
        Item p = findByQuestDescription(questDescription);
        if (not(p)) {
            items.add(0, new Item(questDescription, solved));
        }
    }

    public void append(QuestDescription questDescription, boolean solved) {
        Item p = findByQuestDescription(questDescription);
        if (not(p)) {
            items.add(new Item(questDescription, solved));
        }
    }

    public void solve(QuestDescription questDescription) {
        Item p = findByQuestDescription(questDescription);
        if (is(p)) {
            p.solved = true;
        } else {
            add(questDescription, true);
        }
    }

    // private

    private Item findByQuestDescription(QuestDescription questDescription) {
        for(Item item : items) {
            if (item.questDescription.equals(questDescription)) {
                return item;
            }
        }
        return null;
    }

    //
    // nested classes

    public final class Item {

        public boolean solved;

        public QuestDescription questDescription;

        public Item(QuestDescription questDescription, boolean solved) {
            this.questDescription = questDescription;
            this.solved = solved;
        }

    }

}