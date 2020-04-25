/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model.puzzle.shop;

import static com.captainsoft.spark.utils.Truth.is;

import com.captainsoft.TADr.engine.GameEngine;
import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.TADr.gui.MainViewer;
import com.captainsoft.TADr.model.item.Item;
import com.captainsoft.TADr.model.puzzle.Puzzle;
import com.captainsoft.TADr.model.puzzle.PuzzleFileData;
import com.captainsoft.spark.command.Command;
import com.captainsoft.spark.i18n.Translator;
import com.captainsoft.spark.ui.BaseWindowController;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.mouse.BoxCommandList;

/**
 * Puzzle for a shop (actually not a puzzle, just the puzzle action ;)
 *
 * @author mathias fringes
 */
public final class ShopPuzzle extends BaseWindowController implements Puzzle {

    // fields

    private GameEngine gameEngine;
    private Shop shop;
    private Translator trans;

    // constructors

    public ShopPuzzle(GameEngine gameEngine, PuzzleFileData data) {
        super();
        this.gameEngine = gameEngine;
        this.shop = new Shop(data);
        this.trans = TadRepo.inst().Trans();
    }

    // Puzzle

    public void execute() {
        gameEngine.showWindow(this);
    }

    // WindowController

    public UiBoxContainer createWindow(final BoxCommandList mg) {
        final ShopWindow w = new ShopWindow(shop);
        w.pos(20, 20);
        final MainViewer mainViewer = gameEngine.mainViewer();
        mg.setCmd(w.exitBox, new Command() {
            public void execute() {
                gameEngine.closeWindows();
            }
        });
        mg.setCmd(w.sellButton, new Command() {
            public void execute() {
                if (mainViewer.hasItemInHand()) {
                    Item item = mainViewer.itemInHand();
                    if (item.coins() <= 0) {
                        w.shopManSays(trans.word("shop.dontwantit"));
                    } else {
                        gameEngine.takeItem(null);
                        int coins = item.coins() / 2;
                        gameEngine.addCoins(coins);
                        w.shopManSays(trans.word("shop.solditem", coins + ""));
                        mainViewer.scroll(trans.word("shop.solditemscroll", item.name()));
                    }
                    mainViewer.updateBox(w.shopManSaysBox);
                }
            }
        });
        for (int i = 0; i < w.itemBoxes.length; i++) {
            final Item item = w.itemBoxes[i].item();
            mg.setRightCmd(w.itemBoxes[i], new Command() {
                public void execute() {
                    if (is(item) && !mainViewer.hasItemInHand()) {
                        if (gameEngine.party().isAffordable(item.coins())) {
                            gameEngine.addCoins(-item.coins());
                            gameEngine.takeItem(item);
                            w.shopManSays(trans.word("shop.buyitem", item.coins() + ""));
                            mainViewer.scroll(trans.word("shop.buyitemscroll", item.name()));
                        } else {
                            w.shopManSays(trans.word("shop.buyitemnomoney"));
                        }
                        mainViewer.updateBox(w.shopManSaysBox);
                    }
                }
            });
        }
        w.shopManSays(trans.word("shop.hello"));
        return w;
    }

}
