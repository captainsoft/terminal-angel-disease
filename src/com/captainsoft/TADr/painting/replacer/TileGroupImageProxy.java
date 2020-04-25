/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.painting.replacer;

import com.captainsoft.spark.utils.Utils;

/**
 * Wraps a TileGroupImage in a proxy with the same interface.
 *
 * @author mathias fringes
 */
public class TileGroupImageProxy extends TileGroupImage {

    private TileGroupImage proxied;

    public TileGroupImageProxy(TileGroupImage proxied) {
        super(proxied.image());
        //
        this.proxied = proxied;
        this.image(this.proxied.image());
        //		
        if (proxied.ground()) {
            asGround();
        }
        if (proxied.overlay()) {
            asOverlay();
        }
        if (proxied.secondOverlay()) {
            asSecondOverlay();
        }
    }

    @Override
    public String toString() {
        return Utils.stringer("TigProxy", proxied.toString());
    }

}