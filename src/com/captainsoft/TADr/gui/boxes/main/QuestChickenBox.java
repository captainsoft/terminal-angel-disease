/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.gui.boxes.main;

import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.TADr.loader.ImageLoader;
import com.captainsoft.spark.ui.box.UiDrawBox;
import com.captainsoft.spark.ui.drawing.Surface;
import com.captainsoft.spark.ui.drawing.SurfaceSlicer;


/**
 * @author mathias fringes
 */
public class QuestChickenBox extends UiDrawBox {

    private Surface[] images;

    public int imgIndex = 0;

    public QuestChickenBox(int x, int y, int width, int height) {
        super(x, y, width, height);
        ImageLoader l = TadRepo.inst().ImageLoader();
        Surface b = l.load("tile", 21);

        SurfaceSlicer slicer = new SurfaceSlicer(b, 40);

        images = new Surface[5];
        images[0] = slicer.tile(9, 11);
        images[1] = slicer.tile(9, 12);

        images[2] = slicer.tile(3, 10);
        images[3] = slicer.tile(3, 11);
        images[4] = slicer.tile(3, 12);
    }

    @Override
    protected void draw(Surface s) {
        super.draw(s);
        int yoff = imgIndex == 0 ? 0 : 0;
        s.blit(images[imgIndex], 0, yoff);
    }

}