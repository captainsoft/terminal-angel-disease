/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model.questlog;

import com.captainsoft.TADr.engine.GameEngine;
import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.TADr.model.Game;
import com.captainsoft.TADr.model.Position;

import java.util.HashMap;
import java.util.Map;

import static com.captainsoft.spark.utils.Truth.is;

/**
 * Engine for all quest log descriptions and their triggers. One instance only,
 * is ready to use. Enjoy!
 *
 * @author mathias fringes
 */
public final class QuestLogEngine {

    // fields

    public final static QuestLogEngine inst = new QuestLogEngine();

    private final GameEngine gameEngine;
    private final Map<Integer, QuestDescription> questIdMap = new HashMap<Integer, QuestDescription>();
    private final Map<String, QuestDescription> questCreateTalkIdMap = new HashMap<String, QuestDescription>();
    private final Map<String, QuestDescription> questSolveTalkIdMap = new HashMap<String, QuestDescription>();

    // constructors

    private QuestLogEngine() {
        super();
        fill();
        gameEngine = GameEngine.instance();
    }

    // private

    private void add(String addKey, String solveKey, int coins, String translateKey) {
        int qId = questCreateTalkIdMap.size() + 1;

        String text = TadRepo.inst().Trans().word("quest." + translateKey);
        QuestDescription questDescription = new QuestDescription(qId, coins, text);
        questIdMap.put(qId, questDescription);

        questCreateTalkIdMap.put(addKey, questDescription);
        questSolveTalkIdMap.put(solveKey, questDescription);
    }

    private void fill() {

        add("01-21", "02-70", 1000, "m1.WiseGuys");
        add("01-31", "01-37", 100, "m1.EmuEggBradfish");
        add("01-53", "01-52", 100, "m1.TransitAmpel");
        add("01-77", "03-25", 500, "m1.searchForEvidence");
        add("01-81", "01-85", 750, "m1.HarborShipLost");

        add("02-22", "02-48", 150, "m2.NoPassGrimaldi");
        add("02-39", "02-42", 500, "m2.NoPassAtStars");
        add("02-59", "04-00", 1000, "m2.findJustinCave");
        add("02-64", "02-55", 250, "m2.findRingRing");

        add("03-38", "18-23", 500, "m3.schulzTelporter");

        add("05-19", "05-50", 500, "m5.Gigantus");

        add("06-49", "06-29", 250, "m6.Waldfgee");

        add("07-22", "07-27", 500, "m7.getBook");
        add("07-29", "12-00", 1000, "m7.toTehDungeon");

        add("09-25", "10-00", 500, "m9.Justin1");
        add("09-62", "09-53", 250, "m9.Justin2");


        add("13-61", "13-02", 250, "m13.cold");
        add("13-07", "14-01", 1000, "m13.keysToDungeonCells");
        add("13-21", "13-27", 250, "m13.FridayGum");
        add("13-39", "01-74", 500, "m13.FindBeweisBunga");

        add("15-04", "15-07", 500, "m15.questMachineTalk");

        add("16-73", "16-84", 500, "m16.chcolata");

        add("18-10", "18-20", 500, "m18.lattenGame");

        add("19-39", "20-75", 1000, "m19.saltTheChiliKing");

        add("20-26", "20-28", 500, "m20.toTheOfficeRoom");

    }

    private Game game() {
        return gameEngine.game();
    }

    private void addPossiblePartyQuest(int map, int talkId) {
        QuestDescription q = byTalkId(map, talkId);
        if (is(q)) {
            TadRepo.inst().SndPlayer().playSound("smpr", 8);
            game().QuestLog().add(q, false);
            gameEngine.mainViewer().backpanelWindowController.playChickenAnimation(0);
        }
    }

    private QuestDescription byTalkId(int map, int talkId) {
        String talkKey = talkKey(map, talkId);
        return questCreateTalkIdMap.get(talkKey);
    }

    private QuestDescription getSolveQuestByTalkId(int map, int talkId) {
        String talkKey = talkKey(map, talkId);
        return questSolveTalkIdMap.get(talkKey);
    }

    private void solvePossiblePartyQuest(int map, int talkId) {
        QuestDescription sq = getSolveQuestByTalkId(map, talkId);
        if (is(sq)) {
            game().QuestLog().solve(sq);
            gameEngine.mainViewer().backpanelWindowController.playChickenAnimation(1);
            gameEngine.addCoins(sq.coins);
        }
    }

    private String talkKey(final int map, final int talkId) {
        String talkKey = "";
        if (map < 10) {
            talkKey += "0";
        }
        talkKey += map;
        talkKey += "-";
        if (talkId < 10) {
            talkKey += "0";
        }
        talkKey += talkId;
        return talkKey;
    }

    // public

    public QuestDescription byId(int id) {
        return questIdMap.get(id);
    }

    /**
     * Special quests solves without talking at a certain position.
     *
     * @param position
     */
    public void solveQuestAt(Position position) {

        int levelMap = game().LevelMap().nr();
        //
        if (levelMap == 10 && position.equals(12, 38)) {
            solvePossiblePartyQuest(10, 0);
        } else if (levelMap == 12 && position.equals(56, 46)) {
            solvePossiblePartyQuest(12, 0);
        } else if (levelMap == 12 && position.equals(77, 39)) {
            solvePossiblePartyQuest(12, 0);
        }
        // chest with code wheel
        else if (levelMap == 4 && position.equals(80, 7)) {
            solvePossiblePartyQuest(4, 0);
        }
    }

    public void updateQuestLog(int talkPageId) {
        int map = game().LevelMap().nr();
        //
        addPossiblePartyQuest(map, talkPageId);
        solvePossiblePartyQuest(map, talkPageId);
    }

}