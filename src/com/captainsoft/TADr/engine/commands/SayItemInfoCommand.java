/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.engine.commands;

import com.captainsoft.TADr.engine.GameEngine;
import com.captainsoft.TADr.gui.boxes.common.ItemBox;
import com.captainsoft.TADr.model.item.Item;
import com.captainsoft.spark.command.ParamCommand;

import static com.captainsoft.spark.utils.Truth.is;

/**
 * @author mathias fringes
 */
public final class SayItemInfoCommand implements ParamCommand<ItemBox> {

    public void execute(ItemBox itemBox) {
        Item item = itemBox.item();
        if (is(item)) {
            GameEngine gameEngine = GameEngine.instance();
            String text = gameEngine.itemEngine().sayItemInfoString(item);
            gameEngine.mainViewer().scrollCurrent(text);
        }
    }

}