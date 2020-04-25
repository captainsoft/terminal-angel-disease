/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.ui;

import com.captainsoft.spark.command.Command;

/**
 * A window controller base that can take a command which gets executed
 * on closing.
 *
 * @author mathias fringes
 */
public abstract class CloseWindowController extends BaseWindowController {

    // fields

    private Command closeCommand = null;

    // accessors

    public final void setCloseCommand(Command command) {
        this.closeCommand = command;
    }

    // protected

    protected final void executeCloseCommand() {
        if (this.closeCommand != null) {
            this.closeCommand.execute();
        }
    }

}