/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.render;

import java.util.ArrayList;
import java.util.List;

/**
 * Classic chain for Animation, where the Animations gets played one after the other.
 *
 * @author mathias fringes
 */
public final class AnimationChain extends Animation {

    // fields

    private List<Animation> animations = new ArrayList<Animation>();
    
    private Animation current;

    // constructors

    public void add(Animation a) {
        animations.add(a);
    }

    // Animation

    @Override
    public final int play() {
        if (current == null && animations.size() == 0) {
            return -1;
        }
        //
        if (current == null) {
            current = animations.remove(0);            
        }
        //
        int play = current.play();
        if (play < 0) {
            current = null;
            play = 0;
        }
        return play;
    }

}