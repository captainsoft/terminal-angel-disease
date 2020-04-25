/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.ui.box;

import java.awt.*;
import java.util.*;

import com.captainsoft.spark.ui.drawing.*;

/**
 * An UiBox with container functionality and with its own surface.
 *
 * @author mathias fringes
 */
public class UiBoxContainer extends UiBox implements Bitimage {

    // fields

    private Color backgroundColor = null;
    private ArrayList<UiBox> boxes;
    private Surface backgroundImage;
    private Surface surface;

    // constructors

    public UiBoxContainer() {
        this(0, 0, 0, 0);
    }

    public UiBoxContainer(String name) {
        this(name, 0, 0, 0, 0);
    }

    public UiBoxContainer(int width, int height) {
        this("", 0, 0, width, height);
    }

    public UiBoxContainer(int x, int y, int width, int height) {
        this("", x, y, width, height);
    }

    public UiBoxContainer(String name, int x, int y, int width, int height) {
        super(name, x, y, width, height);
        boxes = new ArrayList<UiBox>(0);
    }

    // public

    public final void initBackground(Surface background) {
        this.backgroundImage = background;
        this.width = background.width();
        this.height = background.height();
        this.createSurface();
    }

    public final Surface background() {
        return backgroundImage;
    }


    public final void backgroundColor(Color color) {
        this.backgroundColor = color;
    }

    // surface methods

    public final void createSurface() {
        if (surface == null) {
            surface = new Surface(width, height);
        } else {
            throw new RuntimeException("Surface for this UiBoxContainer has already been created!");
        }
    }

    public final void setSurface(Surface surface) {
        this.surface = surface;
        width = surface.width();
        height = surface.height();
    }

    // -----------------------------------------------------------------------------------------------------
    // container methods

    public final void add(UiBox... box) {
        for (UiBox b : box) {
            if (!boxes.contains(box)) {
                boxes.add(b);
                if (b.parent() != this) {
                    b.parent(this);
                }
            }
        }
        boxes.trimToSize();
    }

    public final UiBox createChild(int x, int y, int width, int height) {
        UiBox box = new UiBox(x, y, width, height);
        box.parent(this);
        return box;
    }

    public final boolean remove(UiBox box) {
        if (boxes.remove(box)) {
            box.parent(null);
            boxes.trimToSize();
            return true;
        }
        return false;
    }

    public final void remove(Collection<UiBox> boxes) {
        UiBox[] boxes_a = boxes.toArray(new UiBox[0]);
        for (int i = 0; i < boxes_a.length; i++) {
            remove(boxes_a[i]);
        }
    }

    public final void removeAll() {
        remove(this.boxes);
    }

    public final void removeTop() {
        if (boxes.size() > 0) {
            remove(getTopBox());
        }
    }

    public final UiBox getTopBox() {
        if (boxes.size() > 0) {
            return boxes.get(boxes.size() - 1);
        } else {
            return this;
        }
    }

    public final void setToTop(UiBox box) {
        if (!boxes.contains(box)) {
            throw new IllegalArgumentException("This box is not contained in this BoxContainer!");
        }
        boxes.remove(box);
        boxes.add(box);
    }

    public final int size() {
        return boxes.size();
    }

    //	private

    private UiBox getBox(int x, int y, boolean checkClickable) {
        if (!visible(x, y)) {
            return null;
        }
        UiBox activeBox = this;
        for (int i = boxes.size() - 1; i >= 0; i--) {
            UiBox b = boxes.get(i);
            if ((!checkClickable || b.clickable()) && b.visible(x - this.x, y - this.y)) {
                if (checkClickable) {
                    activeBox = b.getClickableBox(x - this.x, y - this.y);
                } else {
                    activeBox = b.getBox(x - this.x, y - this.y);
                }
                if (activeBox == null) {
                    activeBox = b;
                }
                break;
            }
        }
        return activeBox;
    }

    // -----------------------------------------------------------------------------------------------------

    // overridden methods

    @Override
    public final void update() {
        if (!visible()) {
            return;
        }
        super.update();
        for (UiBox box : boxes) {
            box.update();
        }
    }

    @Override
    protected void draw(Surface s) {
        if (backgroundColor != null) {
            s.fill(backgroundColor, 0, 0, width, height);
        }
        if (backgroundImage != null) {
            s.blit(backgroundImage, 0, 0);
        }
    }

    @Override
    public final UiBox getBox(int x, int y) {
        return getBox(x, y, false);
    }

    @Override
    public final UiBox getClickableBox(int x, int y) {
        return getBox(x, y, true);
    }

    @Override
    public final Surface surface() {
        return ((parent() == null) ? surface : parent().surface());
    }

    // Bitimage

    public final Image image() {
        return surface.image();
    }

}