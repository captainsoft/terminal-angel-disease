package com.captainsoft.TADr.gui.controller;

import com.captainsoft.TADr.engine.GameEngine;
import com.captainsoft.TADr.engine.controller.ActionWorldMouseController;
import com.captainsoft.TADr.gui.boxes.main.SimpleMapView;
import com.captainsoft.TADr.model.Position;
import com.captainsoft.TADr.painting.GameLevelMapDrawer;
import com.captainsoft.spark.command.AbstractCommand;
import com.captainsoft.spark.control.MouseButton;
import com.captainsoft.spark.ui.BaseWindowController;
import com.captainsoft.spark.ui.box.UiBox;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.mouse.BoxCommandList;
import com.captainsoft.spark.ui.mouse.ClickCommand;

/**
 * Controller for the map view (action world).
 *
 * @author mathias
 */
public final class MapWindowController extends BaseWindowController {

    // fields

    private final GameEngine gameEngine;
    private final ActionWorldMouseController actionWorldMouseController;
    private final SimpleMapView mapBox;
    private final GameLevelMapDrawer mapDrawer;

    // constructors

    public MapWindowController(GameEngine gameEngine, GameLevelMapDrawer mapDrawer) {
        super();
        this.gameEngine = gameEngine;
        this.mapDrawer = mapDrawer;
        this.actionWorldMouseController = new ActionWorldMouseController(gameEngine);
        //
        mapBox = new SimpleMapView(mapDrawer);
        mapBox.createSurface();
    }

    // WindowController

    public UiBoxContainer createWindow(BoxCommandList commandList) {

        commandList.setClickCmd(mapBox, new ClickCommand() {

            public void click(final UiBox box, final int x, final int y, final MouseButton button) {

                gameEngine.nextCommand(new AbstractCommand("click inside the action world (" + x + "/" + y + ")") {

                    public void execute() {
                        final Position p = mapDrawer.getPosAt(x, y);
                        actionWorldMouseController.clickMapPosition(p, button);
                    }
                });
            }
        });

        return mapBox;
    }

}