/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model.i18n;

import com.captainsoft.TADr.engine.GameEngine;
import com.captainsoft.TADr.model.i18n.GuiMessages;
import com.captainsoft.spark.i18n.MapTranslator;

/**
 * Translator that can deal with TAD specific place holders.
 *
 * @author mathias fringes
 */
public final class TadTranslator extends MapTranslator {

    // public fields

    /**
     * String token for "Name of Player".
     */
    public static final String np = "$np";

    /**
     * String token for "Name of Party".
     */
    public static final String prt = "$prt";

    // private fields

    private GameEngine gameEngine;

    // constructors

    public TadTranslator(GuiMessages messages) {
        super();
        fill(messages);
    }

    // public

    public void setup(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    // private

    private void fill(GuiMessages messages) {
        clear();
        String[] data = messages.data();
        for (int i = 0; i < data.length; i += 2) {
            put(data[i], data[i + 1]);
        }
    }

    // overridden

    @Override
    public String word(String key) {
        String word = super.word(key);
        if (gameEngine.game() != null) {
            word = word.replace(np, gameEngine.game().player());
            word = word.replace(prt, gameEngine.game().party().name());
        }
        return word;
    }

}