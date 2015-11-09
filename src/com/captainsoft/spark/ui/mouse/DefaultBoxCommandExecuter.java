package com.captainsoft.spark.ui.mouse;

import com.captainsoft.spark.command.Command;
import com.captainsoft.spark.control.MouseButton;
import com.captainsoft.spark.ui.box.UiBox;

/**
 * A simple box command executor, as used by the BoxCommandManager, that immediately
 * executes commands.
 *
 * @author mathias fringes
 */
public final class DefaultBoxCommandExecuter implements BoxCommandExecuter {

    // BoxCommandExecuter

	public void execute(Command command) {
		command.execute();		
	}

	public void execute(ClickCommand clickCommand, UiBox box, int x, int y, MouseButton button) {
		clickCommand.click(box, x, y, button);
	}

}
