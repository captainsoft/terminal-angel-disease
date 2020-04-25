/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.cuts;

import java.util.ArrayList;
import java.util.List;

import com.captainsoft.spark.render.Animation;
import com.captainsoft.spark.render.AnimationChain;

/**
 * A list of scenes make up a ... MultiScene!
 *
 * @author mathias fringes
 */
public final class MultiScene {

    // fields

    private List<Scene> scenes = new ArrayList<Scene>();

    // constructors

    public MultiScene() {
        super();
    }

    // public

    public void add(Scene scene) {
        scenes.add(scene);
    }

    public Animation createAnimation() {
        AnimationChain animationChain = new AnimationChain();
        for (Scene scene : scenes) {
            animationChain.add(scene.createAnimation());
        }
        return animationChain;
    }

}