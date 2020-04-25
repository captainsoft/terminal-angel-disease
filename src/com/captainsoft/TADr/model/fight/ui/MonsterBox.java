/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model.fight.ui;

import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.TADr.loader.ImageLoader;
import com.captainsoft.TADr.model.fight.Monster;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.drawing.Surface;

/**
 * UiBox for monsters.
 *
 * @author mathias fringes
 */
public final class MonsterBox extends UiBoxContainer {

    // fields

    private boolean isHops = false;
    private Surface monsterImage;

    // constructors

    MonsterBox(int width, int height, Monster monster) {
        super();
        this.size(width, height);
        this.name = "MonsterMox [" + monster.name + "]";
        ImageLoader loader = TadRepo.inst().ImageLoader();
        monsterImage = loader.load("mimg", monster.image, width, height);
    }

    // public

    public void hops() {
        if (isHops) {
            return;
        }
        y -= 10;
        isHops = true;
    }

    public void rehops() {
        if (!isHops) {
            return;
        }
        y += 10;
        isHops = false;
    }

    // UiBoxContainer

    @Override
    protected void draw(Surface s) {
        s.blit(monsterImage);
    }

}
