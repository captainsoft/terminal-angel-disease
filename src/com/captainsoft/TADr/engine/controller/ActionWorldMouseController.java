/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.engine.controller;

import static com.captainsoft.spark.utils.Truth.is;

import java.util.List;

import com.captainsoft.TADr.engine.debug.Debugger;
import com.captainsoft.TADr.engine.GameEngine;
import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.TADr.gui.MainViewer;
import com.captainsoft.TADr.loader.ItemRepository;
import com.captainsoft.TADr.model.Position;
import com.captainsoft.TADr.model.item.Item;
import com.captainsoft.TADr.model.item.ItemInstance;
import com.captainsoft.TADr.model.map.LevelMap;
import com.captainsoft.TADr.model.map.Tile;
import com.captainsoft.TADr.model.party.ItemPosition;
import com.captainsoft.TADr.model.party.PartyMember;
import com.captainsoft.spark.command.AbstractCommand;
import com.captainsoft.spark.command.Command;
import com.captainsoft.spark.control.MouseButton;
import com.captainsoft.spark.render.Animation;
import com.captainsoft.spark.utils.Log;

/**
 * Controller handling mouse click action in the map view (also called
 * the "Action-Welt").
 *
 * @author mathias fringes
 */
public final class ActionWorldMouseController {

    // fields

    private final ItemRepository itemRepo;
    private final GameEngine gameEngine;
    private final MainViewer mainViewer;

    // constructors

    public ActionWorldMouseController(GameEngine gameEngine) {
        super();
        this.gameEngine = gameEngine;
        this.mainViewer = gameEngine.mainViewer();
        //
        this.itemRepo = TadRepo.inst().ItemRepo();
    }

    // public

    public void clickMapPosition(final Position position, final MouseButton button) {
        final LevelMap levelMap = gameEngine.levelMap();
        Log.debug("ClickPosition " + position + " " + levelMap.tile(position));
        //
        gameEngine.stopParty();
        //
        switch (button) {

            case Left:
                //
                // LEFT: Tile click in the Action-Welt
                // moving and executing puzzles
                leftClickHandling(position, levelMap);
                break;

            case Right:
                //
                // RIGHT: Item handling stuff!
                debugClickHandling(position, levelMap);
                rightClickHandling(position, levelMap);
                break;

            case Unknown:
                //
                // MIDDLE
                // Debugging, middle mouse button moves party to a position!
                if (Debugger.Inst.on && Debugger.Inst.noClipping) {
                    gameEngine.teleportParty(gameEngine.party().mapNumber(), position);
                }
                break;

            default:
                break;
        }

    }

    // private

    private void leftClickHandling(final Position position, final LevelMap levelMap) {

        if (isWindowShown()) {
            return;
        }

        Position partyPosition = gameEngine.party().position();
        if (partyPosition.isNextTo(position)) {

            if (mainViewer.hasItemInHand()) {
                gameEngine.puzzleEngine().doItemPuzzle(gameEngine.itemInHand(), position);
                return;
            } else {
                if (gameEngine.puzzleEngine().executePassivePuzzle(position)) {
                    return;
                }
                if (gameEngine.tileEngine().extraTileClickAction(levelMap, position, partyPosition)) {
                    return;
                }
            }
        }

        gameEngine.goPartyGo(position, true);
    }

    private void debugClickHandling(final Position position, final LevelMap levelMap) {

        if (Debugger.Inst.tileInfo) {
            Tile tile = levelMap.tile(position);
            gameEngine.sayAsIs(position + " # " + tile);

            Animation a = mainViewer.mapDrawer.mapAnimations.get(position);
            if (a != null) {
                gameEngine.sayAsIs("Animation: " + a);
            }
            String r = mainViewer.mapDrawer.replacer.whatIsOn(position);
            if (r.length() > 0) {
                gameEngine.sayAsIs("Replaces: " + r);
            }
        }
    }

    private void rightClickHandling(final Position position, final LevelMap levelMap) {

        Position partyPosition = gameEngine.party().position();
        //
        // item dropping and taking
        if (partyPosition.isNextTo(position)) {
            dropTakeItem(position);
        }
        //
        // drop on party figure -> into the inventory!
        else if (partyPosition.equals(position)) {
            Item cursorItem = mainViewer.itemInHand();
            if (is(cursorItem)) {
                boolean take = takeItemIntoInventory(cursorItem);
                if (take) {
                    gameEngine.takeItem(null);
                }
            }
        }
        //
        // far distant items -> first walk there, then item action!
        else {
            if (!isWindowShown()) {
                //
                Tile tile = levelMap.tile(position);
                if (tile.dropable() && (mainViewer.hasItemInHand() || tile.hasItem())) {
                    gameEngine.goPartyTryToGoThere(position, new AbstractCommand("item handling after walking there") {

                        public void execute() {
                            dropTakeItem(position);
                        }
                    });
                }
                else if (!tile.dropable()) {
                    gameEngine.stopParty();
                    gameEngine.nextCommand(new Command() {

                        public void execute() {
                            gameEngine.meeeehParty();
                        }
                    });
                }
            }
        }

    }

    private boolean takeItemIntoInventory(Item item) {
        int startMemberNr = mainViewer.currentMember().nr();
        //
        List<PartyMember> allFrom = gameEngine.party().allFrom(startMemberNr);
        ItemPosition pos = gameEngine.itemEngine().findSuitablePos(allFrom, item);
        //
        if (pos != null) {
            gameEngine.party().inventory().set(pos, item);
            mainViewer.toInventory(pos, item);
            return true;
        }
        //
        return false;
    }

    private void dropTakeItem(Position position) {

        LevelMap levelMap = gameEngine.levelMap();

        Tile tile = levelMap.tile(position);
        Item cursorItem = mainViewer.itemInHand();

        Item tileItem = itemRepo.item(tile.item());
        if (cursorItem != null) {
            if (tile.dropable()) {
                tile.item(cursorItem.id());
                gameEngine.takeItem(tileItem);
                mainViewer.updatePosition(position);
            } else if (levelMap.isCoffeeAutomaton(position)) {
                if (ItemInstance.CoffeeCupEmpty.match(cursorItem)) {
                    gameEngine.takeItem(itemRepo.item(ItemInstance.CoffeeCupFull));
                }
            }
        } else {
            if (tileItem != null) {
                tile.removeItem();
                gameEngine.takeItem(tileItem);
                mainViewer.updatePosition(position);
            }
        }
    }

    private boolean isWindowShown() {
        return (mainViewer.windowShown());
    }

}
