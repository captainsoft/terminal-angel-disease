/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.tools;

import com.captainsoft.TADr.TadLang;
import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.TADr.model.Position;
import com.captainsoft.TADr.model.map.LevelMap;
import com.captainsoft.TADr.painting.PaintingInfo;
import com.captainsoft.TADr.painting.TilePainter;
import com.captainsoft.TADr.painting.TileSet;
import com.captainsoft.TADr.painting.animations.MapAnimationList;
import com.captainsoft.TADr.painting.replacer.MapTileReplacer;
import com.captainsoft.spark.ui.drawing.Surface;
import com.captainsoft.spark.ui_swing.SwingSurfacePanel;
import com.captainsoft.tools.editorx.TadEditorRepo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * This saves a full scale png.image of each of the 20 maps to the file system.
 *
 * @author mathias fringes
 */
public class GameMapImageSaver extends JFrame {

    // fields

    private final static TadRepo repo = TadRepo.inst();

    // constructors

    public GameMapImageSaver(final int mapNr) {
        super();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        try {
            repo.setUp();
        } catch (IOException e) {
            e.printStackTrace();
        }


        final String MapFilename = "dat/tadmap.dat";
        LevelMap map = TadEditorRepo.inst.mapLoader.loadMap(TadLang.file(MapFilename), mapNr);

        TileSet tileset = new TileSet(repo.ImageLoader());
        tileset.load(map.tileset());


        TilePainter tilePainter = new TilePainter(tileset, repo.ItemRepo(), new PaintingInfo());
        tilePainter.setMap(map);

        MapTileReplacer m = new MapTileReplacer(tileset, repo.ImageLoader());
        m.setup(map, new MapAnimationList(10, 10));
        tilePainter.setReplacer(m);

        Surface mapImage = new Surface(99 * 40, 99 * 40);


        for (int x = 0; x < 99; x++) {
            for (int y = 0; y < 99; y++) {
                Surface tile = tilePainter.createTileImage(new Position(x + 1, y + 1));
                mapImage.blit(tile, x * 40, y * 40);
            }
        }

        this.setLayout(new BorderLayout());
        this.add(new SwingSurfacePanel(mapImage), BorderLayout.CENTER);

        File outputfile = new File("map" + map.nr() + ".png");
        System.out.println(outputfile.getAbsolutePath());
        try {
            ImageIO.write(mapImage.bufferedImage(), "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // main

    public static void main(String args[]) {
        TadLang.toGerman();
        for (int i = 1; i < 21; i++) {
            GameMapImageSaver mv = new GameMapImageSaver(i);
            // mv.setVisible(true);
        }
        System.exit(0);
    }

}