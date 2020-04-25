/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.ui.mouse;

import java.util.HashMap;
import java.util.Map;

import com.captainsoft.spark.command.Command;
import com.captainsoft.spark.control.MouseButton;
import com.captainsoft.spark.ui.box.UiBox;

/**
 * Stores commands for boxes.
 *
 * @author mathias fringes
 */
public final class BoxCommandList {

    // fields

    private final Map<UiBox, ClickCommand> leftCommands = new HashMap<UiBox, ClickCommand>(10);
    private final Map<UiBox, ClickCommand> rightCommands = new HashMap<UiBox, ClickCommand>(10);
    private final Map<UiBox, ClickCommand> doubleClickCommands = new HashMap<UiBox, ClickCommand>(0);

    public final Map<Integer, Command> keyCommands = new HashMap<Integer, Command>(0);

    // constructors

    public BoxCommandList() {
        super();
    }

    // accessors

    /**
     * Sets the click command for a box.
     *
     * @param box
     * @param command
     */
    public void setClickCmd(UiBox box, ClickCommand command) {
        leftCommands.put(box, command);
        rightCommands.put(box, command);
    }

    /**
     * Sets the command for this box. Is by default always only left click!
     *
     * @param box
     * @param command
     */
    public void setCmd(UiBox box, Command command) {
        leftCommands.put(box, new ClickCommandAdapter(command));
    }

    public void setCmd(UiBox box, Integer key, Command command) {
        setCmd(box, command);
        keyCommands.put(key, command);
    }

    public void setCmd(UiBox box, Integer[] keys, Command command) {
        setCmd(box, command);
        for (Integer key : keys) {
            keyCommands.put(key, command);
        }
    }

    /**
     * Sets the commands for a box. One for left, one for right click.
     *
     * @param box
     * @param leftCommand
     * @param rightCommand
     */
    public void setCmds(UiBox box, Command leftCommand, Command rightCommand) {
        setCmd(box, leftCommand);
        setRightCmd(box, rightCommand);
    }

    public void setDoubleClickCmd(UiBox box, Command command) {
        doubleClickCommands.put(box, new ClickCommandAdapter(command));
    }

    /**
     * Sets the command for a box if the right button is clicked.
     *
     * @param box
     * @param command
     */
    public void setRightCmd(UiBox box, Command command) {
        rightCommands.put(box, new ClickCommandAdapter(command));
    }

    public ClickCommand getCommand(UiBox box, MouseButton button) {
        return getCommand(box, button, false);
    }

    /**
     * Gets the command for a box and a mouse button combination.
     *
     * @param box
     * @param button
     * @return
     */
    public ClickCommand getCommand(UiBox box, MouseButton button, boolean doubleClick) {
        switch (button) {
            case Left:
                ClickCommand command = null;
                if (doubleClick) {
                    command = doubleClickCommands.get(box);
                }
                if (command == null) {
                    command = leftCommands.get(box);
                }
                return command;
            case Right:
            case Unknown:
                return rightCommands.get(box);
            default:
                return null;
        }
    }

    //
    // nested classes

    private static class ClickCommandAdapter implements ClickCommand {

        private final Command command;

        public ClickCommandAdapter(Command command) {
            super();
            this.command = command;
        }

        public void click(UiBox box, int x, int y, MouseButton button) {
            command.execute();
        }

    }

}
