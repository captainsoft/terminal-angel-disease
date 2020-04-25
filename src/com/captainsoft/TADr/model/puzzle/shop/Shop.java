/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model.puzzle.shop;

import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.TADr.model.item.Item;
import com.captainsoft.TADr.model.puzzle.PuzzleFileData;

/**
 * Shop data with item info!
 *
 * @author mathias fringes
 */
final class Shop {

    private Item[] items = new Item[9];

    public Shop() {
        super();
    }

    public Shop(PuzzleFileData fileData) {
        for (int i = 0; i < items.length; i++) {
            items[i] = TadRepo.inst().ItemRepo().item(fileData.value(i + 1));
        }
    }

    public Item item(int index) {
        return items[index];
    }

}