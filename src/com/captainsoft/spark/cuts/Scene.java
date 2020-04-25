/*
 * Copyright Captainsoft 2010-2012.
 * All rights reserved.
 */
package com.captainsoft.spark.cuts;

import java.util.ArrayList;
import java.util.List;

import com.captainsoft.spark.cuts.ations.FrameAnimation;
import com.captainsoft.spark.render.Animation;
import com.captainsoft.spark.ui.Updater;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.utils.Log;

/**
 * A scene with a combined set of animations. ready to play!.
 *
 * @author mathias fringes
 */
public final class Scene {

    // fields

    private final Updater updater;
    private final List<Tick> animationTicks;
    private final List<Tick> open;
    private final UiBoxContainer backgroundBox;
    private final UiBoxContainer sceneBox;

    public String name = "";

    // constructors

    public Scene(Updater updater, UiBoxContainer backgroundBox) {
        super();
        this.updater = updater;
        this.backgroundBox = backgroundBox;
        //
        sceneBox = new UiBoxContainer(0, 0, backgroundBox.width, backgroundBox.height);
        animationTicks = new ArrayList<Tick>();
        open = new ArrayList<Tick>();
    }

    // accessors

    public UiBoxContainer sceneBox() {
        return sceneBox;
    }

    // package

    List<Tick> getAnimationTickList() {
        return animationTicks;
    }

    // public

    public SceneDSL createDsl() {
        return new SceneDSL(this);
    }

    public void addAnimation(float frame, FrameAnimation a) {
        addAnimation(new FrameMx(frame), a);
    }

    public void addAnimation(FrameMx idx, FrameAnimation a) {
        animationTicks.add(new Tick(idx, a));
    }

    public Animation createAnimation() {
        return new Animation() {

            FrameMx last = null;
            FrameMx next = null;

            @Override
            public int play() {

                if (step == 0) {
                    Log.info("Starting playing scene " + name);
                    backgroundBox.removeAll();
                    backgroundBox.add(sceneBox);
                    resetOpenList();
                    next = null;
                    last = FrameMx.Start;
                } else {
                    List<Tick> nowPlaying = allOpenFor(next);
                    for (Tick t : nowPlaying) {
                        t.play();
                    }
                    //
                    removeFromOpenList(nowPlaying);
                }

                updater.update();

                next = smallestFrameInOpen();
                if (next == null) {
                    return -1;
                }

                long sleepValue = next.subtr(last).value();

                last = next;
                step++;
                return (int) sleepValue;
            }
        };
    }

    // private

    private List<Tick> allOpenFor(FrameMx frame) {
        List<Tick> result = new ArrayList<Tick>();
        for (Tick t : open) {
            if (t.next().isEqualTo(frame)) {
                result.add(t);
            }
        }
        return result;
    }

    private void removeFromOpenList(List<Tick> events) {
        for (Tick t : events) {
            if (t.isOver()) {
                open.remove(t);
            }
        }
    }

    private void resetOpenList() {
        open.clear();
        open.addAll(animationTicks);
        for (Tick t : open) {
            t.reset();
        }
    }

    private FrameMx smallestFrameInOpen() {
        FrameMx f = null;
        for (Tick t : open) {
            if (f == null || t.next().isLessThan(f)) {
                f = t.next();
            }
        }
        return f;
    }

}
