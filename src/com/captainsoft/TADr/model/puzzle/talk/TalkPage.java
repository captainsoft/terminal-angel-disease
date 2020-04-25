/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model.puzzle.talk;

/**
 * A talk page (the actual text).
 *
 * @author mathias fringes
 */
public final class TalkPage {

    public int id;
    public int eventId1;
    public int eventId2;
    public int itemId;
    public int nextTalkPageNr;
    public int portraitId;
    public int showBullet;
    public String name;
    public String text;

    public TalkPage() {
        super();
    }

}