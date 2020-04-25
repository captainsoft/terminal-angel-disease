/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.ui;

import com.captainsoft.spark.ui.box.UiBox;

/**
 * An updater that operators only on a single specified box.
 *
 * @author mathias fringes
 */
public final class SingleBoxUpdater implements Updater {

    // fields

    private final UiBox box;
    private final BoxUpdater updater;

    // constructors

    public SingleBoxUpdater(UiBox box, BoxUpdater updater) {
        super();
        this.box = box;
        this.updater = updater;
    }

    // Updater

    @Override
    public void update() {
        updater.update(box);
    }

}