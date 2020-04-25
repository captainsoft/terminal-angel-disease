/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.engine.commands;

import java.awt.Color;

import com.captainsoft.TADr.cuts.CutScene;
import com.captainsoft.TADr.gui.MainViewer;
import com.captainsoft.spark.render.Animation;
import com.captainsoft.spark.ui.SingleBoxUpdater;
import com.captainsoft.spark.ui.Updater;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.swing.SwingBoxUpdater;
import com.captainsoft.spark.ui_swing.SwingBoxPanel;

/**
 * Base class for showing cut scenes.
 *
 * @author mathias fringes
 */
public abstract class ShowCutSceneCommand extends GameEngineCommand {

    // fields

    private final MainViewer mv;
    private CutSceneAnimation cutSceneAnimation;

    // constructors

    public ShowCutSceneCommand(MainViewer mv) {
        super();
        this.mv = mv;
    }

    // protected

    protected void sceneIsOver() {
    }

    // public

    public void stop() {
        if (cutSceneAnimation != null) {
            cutSceneAnimation.stop();
            sceneIsOver();
        }
    }

    // abstract

    protected abstract CutScene createCutScene(Updater updater, UiBoxContainer backgroundBox);

    // Command

    public final void execute() {
        //
        // ui ...					
        UiBoxContainer backgroundBox = new UiBoxContainer(945, 617);
        backgroundBox.createSurface();
        backgroundBox.backgroundColor(Color.BLACK);
        //
        final SwingBoxPanel panel = new SwingBoxPanel(backgroundBox);
        panel.setBackground(Color.BLACK);
        mv.setBackgroundPanel(panel);
        SwingBoxUpdater updateManager = new SwingBoxUpdater();
        updateManager.registerComponent(panel, backgroundBox);
        Updater updater = new SingleBoxUpdater(backgroundBox, updateManager);
        //
        // play...
        final CutScene cutScene = createCutScene(updater, backgroundBox);
        cutSceneAnimation = new CutSceneAnimation(cutScene);

        gameEngine.stopAllAnimations();
        gameEngine.addAnimation(cutSceneAnimation);
    }

    // nested

    private final class CutSceneAnimation extends Animation {

        private final CutScene cutScene;
        private Animation current = null;

        private CutSceneAnimation(CutScene cutScene) {
            this.cutScene = cutScene;
        }

        @Override
        public int play() {
            if (current == null) {
                step++;
                current = cutScene.createAnimation(step);
                if (current == null) {
                    sceneIsOver();
                    return -1;
                }
            }
            int delay = current.play();
            if (delay < 0) {
                current = null;
            }
            return (delay < 0 ? 0 : delay);
        }

    }

}
