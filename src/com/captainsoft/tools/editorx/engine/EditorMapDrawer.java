/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.tools.editorx.engine;

import com.captainsoft.TADr.model.Position;
import com.captainsoft.TADr.model.map.LevelMap;
import com.captainsoft.TADr.model.map.MapTileView;
import com.captainsoft.TADr.painting.PaintingInfo;
import com.captainsoft.TADr.painting.TilePainter;
import com.captainsoft.TADr.painting.TileSet;
import com.captainsoft.TADr.painting.animations.MapAnimationList;
import com.captainsoft.TADr.painting.replacer.MapTileReplacer;
import com.captainsoft.tools.editorx.TadEditorRepo;
import com.captainsoft.spark.ui.drawing.Surface;
import com.captainsoft.tools.editorx.model.EditorModel;

import java.awt.*;

/**
 * 
 * @author mathias fringes
 */
public final class EditorMapDrawer {
	
	private final int PQ = 40;
	
	public Surface surface;	
	
	private TilePainter tilePainter;
	private MapTileView tileView;
	public final MapTileReplacer replacer;
	private TileSet tileSet;
    private final EditorModel model;

    public EditorMapDrawer(EditorModel model) {
		super();
        this.model = model;
		
		tileSet = new TileSet(TadEditorRepo.inst.imageLoader);
		tilePainter = new TilePainter(tileSet, TadEditorRepo.inst.itemRepo, new PaintingInfo());
		
		replacer = new MapTileReplacer(tileSet, TadEditorRepo.inst.imageLoader);
		tilePainter.setReplacer(replacer);
	}
	
	public void init(MapTileView tileView) {
		this.tileView = tileView;
		if (surface != null) {
			surface.release();
		}
		surface = new Surface(tileView.xspan * PQ, tileView.yspan * PQ);
		
		// full paint
		drawFullMap();
	}
	
	public void drawFullMap() {		
		for (int y = 0; y < tileView.yspan; y++) {
			for (int x = 0; x < tileView.xspan; x++) {
				Position pos = tileView.topLeft.add(x, y);									
				Surface image = tilePainter.createTileImage(pos);

				surface.blit(image, x * PQ, y * PQ);

                if (pos.equals(model.selPosition.x, model.selPosition.y)) {
                    surface.rect(Color.RED, x * PQ, y * PQ, PQ, PQ);
                }
			}
		}
	}

	public void map(LevelMap currentMap) {					
		tileSet.load(currentMap.tileset());
		tilePainter.setMap(currentMap);	
		replacer.setup(currentMap, new MapAnimationList(10, 10));
	}
	
}