/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.tools.editorx;

import com.captainsoft.TADr.loader.vb.VbImageLoader;
import com.captainsoft.TADr.loader.vb.VbItemRepository;
import com.captainsoft.TADr.loader.vb.VbMapLoader;

/**
 * @author mathias fringes
 */
public final class TadEditorRepo {

    public static final TadEditorRepo inst;

    static {
        inst = new TadEditorRepo();
    }

    public VbItemRepository itemRepo;
    public VbImageLoader imageLoader;
    public VbMapLoader mapLoader;

    private TadEditorRepo() {
        super();
        setup();
    }

    private void setup() {
        imageLoader = new VbImageLoader();
        itemRepo = new VbItemRepository(imageLoader);
        itemRepo.load();
        mapLoader = new VbMapLoader();
    }

}
