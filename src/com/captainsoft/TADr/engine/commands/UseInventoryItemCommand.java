/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.engine.commands;

import com.captainsoft.TADr.engine.GameEngine;
import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.TADr.gui.boxes.common.ItemBox;
import com.captainsoft.TADr.model.Position;
import com.captainsoft.TADr.model.item.Item;
import com.captainsoft.TADr.model.item.ItemInstance;
import com.captainsoft.TADr.model.map.LevelMap;
import com.captainsoft.TADr.model.party.ItemPosition;
import com.captainsoft.TADr.model.party.Party;
import com.captainsoft.TADr.model.party.PartyMember;
import com.captainsoft.TADr.model.puzzle.TileValues;
import com.captainsoft.TADr.model.puzzle.book.BookWindowController;
import com.captainsoft.TADr.model.puzzle.teleporter.Teleporter;
import com.captainsoft.TADr.model.puzzle.teleporter.TeleporterPuzzle;
import com.captainsoft.TADr.sound.SndPlayer;
import com.captainsoft.spark.command.ParamCommand;
import com.captainsoft.spark.utils.Utils;

import static com.captainsoft.spark.utils.Utils.stringer;

/**
 * Uses an item from the inventory (not in fights).
 *
 * @author mathias fringes
 */
public final class UseInventoryItemCommand implements ParamCommand<ItemBox> {

    // fields

    private final SndPlayer sndPlayer;
    private final GameEngine gameEngine;

    // constructors

    public UseInventoryItemCommand() {
        super();
        this.gameEngine = GameEngine.instance();
        this.sndPlayer = TadRepo.inst().SndPlayer();
    }

    // Command

    public void execute(ItemBox box) {

        if (gameEngine.itemInHand() != null) {
            return;
        }

        final Item item = box.item();
        if (item == null) {
            return;
        }

        if (item.isFightItem()) {
            gameEngine.say("item.use.fightOnly");
            return;
        }

        //
        // check for items that cannot be used if a window is shown!
        if (gameEngine.mainViewer().windowShown()) {
            switch (item.type()) {
                case Book:
                case Teleporter:
                    return;
                default:
                    if (ItemInstance.Lockpick.match(item) || item.isPuzzleItem()) {
                        return;
                    }
            }
        }

        //
        // do individual item action

        Party party = gameEngine.party();
        PartyMember member = gameEngine.mainViewer().currentMember();
        ItemPosition itemPosition = new ItemPosition(member, box.inventoryIndex);
        boolean killItem = true;

        switch (item.type()) {

            case Book:
                BookWindowController wc = new BookWindowController(gameEngine, item.value());
                gameEngine.showWindow(wc);
                killItem = false;
                break;

            case CurrentFunPoints:
                if (member.fun.isFull()) {
                    gameEngine.say("fitPoints.eat.full");
                    killItem = false;
                } else {
                    member.fun.addCur(item.value() + (int) (Math.random() * (item.value() / 2)));
                    gameEngine.mainViewer().updateFunBars(member);
                    gameEngine.say("fitPoints.eat.thankyou");
                }
                break;

            case Teleporter:
                int mapNr = gameEngine.game().LevelMap().nr();
                if (mapNr != 4 && mapNr != 5 && mapNr < 18) {
                    Teleporter tp = createPrismaTeleporter();
                    new TeleporterPuzzle(tp).execute();
                    gameEngine.say("telep.prisma.funny");
                } else {
                    gameEngine.sayCurrent("teleporter.misuse." + mapNr);
                    killItem = false;
                }
                break;

            case Chili:
                gameEngine.say("chiliStuff.dontEat");
                killItem = false;
                break;

            case FunPointsMax:
                sndPlayer.playSound("sifc", 23);
                int dval = item.value() + (int) (Math.random() * (item.value() / 2));
                member.fun.addMax(dval);
                gameEngine.mainViewer().updateFunBars(member);
                gameEngine.say("item.use.funPointsMax");
                break;

            case FitPointsMax:
                sndPlayer.playSound("sifc", 23);
                member.setPtsFit(member.getPtsFit() + 1);
                gameEngine.mainViewer().updateSkillView(member);
                gameEngine.say("item.use.fitPointsMax");
                break;

            case FoxPointsMax:
                sndPlayer.playSound("sifc", 23);
                member.setPtsFox(member.getPtsFox() + 1);
                if (member == party.detective()) {
                    member.specialMemberSkill.learn(Utils.random(5) + 9);
                }
                gameEngine.mainViewer().updateSkillView(member);
                if (member == party.blob()) {
                    int i = member.getPtsFox() + 1;
                    gameEngine.say("item.use.foxPointsMax", (i * i), i);
                } else {
                    gameEngine.say("item.use.foxPointsMax");
                }
                break;

            default:
                //
                //  puzzle item				
                if (item.isPuzzleItem()) {
                    LevelMap map = gameEngine.levelMap();
                    Position pos = gameEngine.party().position();
                    Position pz = map.isPuzzleAround(pos);
                    if (pz != null) {
                        gameEngine.puzzleEngine().doItemPuzzle(item, pz);
                    } else {
                        gameEngine.say("item.use.wrong");
                    }
                    return;

                    //
                    // lockpick for chests
                } else if (ItemInstance.Lockpick.match(item)) {
                    LevelMap map = gameEngine.levelMap();
                    Position pos = gameEngine.party().position();
                    Position pz = map.isPuzzleAround(pos);
                    if (pz != null) {
                        gameEngine.puzzleEngine().doChestLockpickPuzzle(pz, itemPosition);
                    } else {
                        gameEngine.say("item.use.wrong");
                    }
                    return;

                } else {
                    gameEngine.say("item.use.cannot");
                    killItem = false;
                }
                break;
        }
        //
        if (killItem) {
            gameEngine.deleteItem(itemPosition);
        }
    }

    // private

    private Teleporter createPrismaTeleporter() {
        Teleporter tp = new Teleporter();
        tp.mapNr = 2;
        tp.playSound = true;
        tp.position = new Position(51, 44);
        tp.tileValue = new TileValues();
        return tp;
    }

    // overridden

    @Override
    public String toString() {
        return stringer("UseInventoryItemCommand");
    }

}
