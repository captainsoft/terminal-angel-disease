/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.engine.loop;

import java.util.ArrayList;
import java.util.List;

import com.captainsoft.TADr.engine.GameEngine;
import com.captainsoft.TADr.model.Position;
import com.captainsoft.spark.command.Command;
import com.captainsoft.spark.render.Animation;
import com.captainsoft.spark.utils.Log;

/**
 * @author mathias fringes
 */
final class WalkAnimationStack extends Animation {

    // fields

    private final GameEngine gameEngine;

    private Animation currentAnimation;
    private Command destinationCommand;
    private List<Position> positions;
    private Position togo = null;

    // constructors

    public WalkAnimationStack(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        positions = new ArrayList<Position>(0);
    }

    // public

    public void setAnimation(Animation animation) {
        stop();
        this.currentAnimation = animation;
        restart();
    }

    public void setDestinationCommand(Command destinationCommand) {
        this.destinationCommand = destinationCommand;
    }

    public Command pullDestinationCommand() {
        Command c = destinationCommand;
        destinationCommand = null;
        return c;
    }

    public void goTo(List<Position> positions) {
        stop();
        if (positions != null) {
            this.positions = positions;
            restart();
        }
    }

    // private

    private boolean closed() {
        return (positions.isEmpty() && currentAnimation == null);
    }

    // Animation

    @Override
    public void stop() {
        positions.clear();
        destinationCommand = null;
    }

    @Override
    public int play() {
        if (closed()) {
            return -1;
        }
        if (currentAnimation == null) {
            togo = positions.get(0);
            positions.remove(0);
            currentAnimation = gameEngine.mainViewer().scrollParty(togo);
        } else {
            int time = currentAnimation.play();
            if (time < 0) {
                currentAnimation = null;
                if (togo != null) {
                    boolean action = gameEngine.arrivedAt(togo);
                    togo = null;
                    if (action) {
                        Log.log("Stopping walk cause of tileAction!");
                        stop();
                        return -1;
                    }
                }
            }
            return time > 0 ? time : 0;
        }

        return 0;
    }

}
