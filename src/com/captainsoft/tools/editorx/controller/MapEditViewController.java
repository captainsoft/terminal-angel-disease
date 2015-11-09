/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.tools.editorx.controller;

import com.captainsoft.TADr.TadLang;
import com.captainsoft.TADr.model.Position;
import com.captainsoft.TADr.model.map.MapTileView;
import com.captainsoft.TADr.model.map.Tile;
import com.captainsoft.tools.editorx.TadEditorRepo;
import com.captainsoft.tools.editorx.Xlog;
import com.captainsoft.tools.editorx.engine.EditorMapDrawer;
import com.captainsoft.tools.editorx.model.EditorModel;
import com.captainsoft.spark.control.MouseButton;
import com.captainsoft.spark.ui.BoxUpdater;
import com.captainsoft.spark.ui.box.UiBox;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.mouse.BoxCommandList;
import com.captainsoft.spark.ui.mouse.BoxCommandManager;
import com.captainsoft.spark.ui.mouse.ClickCommand;

/**
 * 
 * @author mathias fringes
 */
public final class MapEditViewController {	

	private UiBoxContainer mapBox;
	private BoxCommandManager mapCommandManager;
	
	private EditorModel model = new EditorModel();
	private EditorMapDrawer drawer;
	private MapTileView mtv;
	private BoxUpdater boxUpdateManager;

    private static final String MapFilename = "dat/tadmap.dat";


	public MapEditViewController(final BoxUpdater boxUpdateManager) {
		super();
		this.boxUpdateManager = boxUpdateManager;
		
		model.currentMap = TadEditorRepo.inst.mapLoader.loadMap(TadLang.file(MapFilename), 1);
		
		mtv = new MapTileView(30, 18);
		mtv.topLeft = new Position(1, 1);
		drawer = new EditorMapDrawer(model);
		drawer.map(model.currentMap);
		drawer.init(mtv);
		
		// map panel box
		mapBox = new UiBoxContainer();
		mapBox.setSurface(drawer.surface);		
		BoxCommandList mapCommandList = new BoxCommandList();
		mapCommandManager = new BoxCommandManager(mapBox, mapCommandList);
		mapCommandList.setClickCmd(mapBox, new ClickCommand() {			

			public void click(UiBox box, int x, int y, MouseButton button) {
								 	
				int tx = x / 40;
				int ty = y / 40;
								
				Position clickOn = mtv.topLeft.add(tx, ty);
                model.selPosition = clickOn;
				Xlog.l(clickOn, model.currentMap.tile(clickOn));
				
				// move map
				if (button == MouseButton.Right) {
					mtv.center(clickOn);
					sanitize(mtv);
					
					drawer.drawFullMap();
					boxUpdateManager.update(mapBox);	
				}
				
			}
		});
		
	}

    public void removeDanger() {
        Tile tile = model.currentMap.tile(model.selPosition);
        int value4 = tile.value(4);


        if (value4 == 200 || value4 == 201) {
            tile.set(4, 0);
        }

        drawer.replacer.deleteDanger(model.selPosition);
        drawer.drawFullMap();
        boxUpdateManager.update(mapBox);
    }
	
	public void loadMap(int map) {	
		if (model.currentMap.nr() == map) {
			return;
		}
		model.currentMap = TadEditorRepo.inst.mapLoader.loadMap(TadLang.file(MapFilename), map);
		//
		drawer.map(model.currentMap);		
		drawer.drawFullMap();
		boxUpdateManager.update(mapBox);
	}

    public void saveMap() {
        Xlog.l("Saved map");
        TadEditorRepo.inst.mapLoader.save(model.currentMap, MapFilename);
    }
	
	public UiBoxContainer getBox() {
		return mapBox;		
	}

	public BoxCommandManager getCommandManager() {
		return mapCommandManager;
	}	
	
	private void sanitize(MapTileView tv) {
		if (tv.topLeft.x < 1) {
			tv.topLeft = tv.topLeft.x(1);
		}		
		if (tv.topLeft.y < 1) {
			tv.topLeft = tv.topLeft.y(1);
		}
		//
		if (tv.btmRight().x > 99) {
			tv.topLeft = tv.topLeft.x(99 - tv.xspan);
		}
		if (tv.btmRight().y > 99) {
			tv.topLeft = tv.topLeft.y(99 - tv.yspan);
		}
	}

}
