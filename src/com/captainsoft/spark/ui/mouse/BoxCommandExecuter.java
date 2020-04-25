package com.captainsoft.spark.ui.mouse;

import com.captainsoft.spark.command.Command;
import com.captainsoft.spark.control.MouseButton;
import com.captainsoft.spark.ui.box.UiBox;

/**
 * Handles execution requests for commands.
 *
 * @author mathias fringes
 */
public interface BoxCommandExecuter {

    void execute(Command c);

    void execute(ClickCommand cc, UiBox box, int x, int y, MouseButton button);

}
