/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.painting.animations;

import com.captainsoft.TADr.gui.boxes.main.QuestChickenBox;
import com.captainsoft.spark.render.Animation;
import com.captainsoft.spark.ui.BoxUpdater;

/**
 * Animation for the quest chicken!
 *
 * @author mathias fringes
 */
public class QuestChickenAnimation extends Animation {

    // fields

    private final BoxUpdater updater;
    private final int max = 20;
    private final QuestChickenBox questChickenBox;
    public int mode = 1;

    // constructors

    public QuestChickenAnimation(QuestChickenBox questChickenBox, BoxUpdater updater, int mode) {
        this.questChickenBox = questChickenBox;
        this.updater = updater;
        this.mode = mode;
    }

    // private

    private void update() {
        updater.update(questChickenBox);
    }

    private void playSolveAnimation() {
        switch (step % 4) {
            case 0:
                questChickenBox.imgIndex = 2;
                break;
            case 1:
                questChickenBox.imgIndex = 3;
                break;
            case 2:
                questChickenBox.imgIndex = 4;
                break;
            case 3:
                questChickenBox.imgIndex = 3;
                break;
        }
    }

    private void playNewQuestAnimation() {
        if (step % 2 == 0) {
            questChickenBox.imgIndex = 0;
        } else {
            questChickenBox.imgIndex = 1;
        }
    }

    // Animation

    @Override
    public int play() {
        step++;

        if (step > max) {
            questChickenBox.imgIndex = 0;
            update();
            return -1;
        }

        int speed = 150;

        switch (mode) {
            case 1:
                playSolveAnimation();
                break;
            default:
                playNewQuestAnimation();
                break;
        }

        update();
        return speed;
    }

}