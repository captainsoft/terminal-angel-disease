/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.spark._sparkmuell;

import java.util.*;

import com.captainsoft.spark.control.Command;
import com.captainsoft.spark.ui.*;
import com.captainsoft.spark.ui.swing.SwingBoxUpdater;
import com.captainsoft.spark.utils.*;

public class X_UiWindowManager {
	
	
	public static X_UiWindowManager inst = new X_UiWindowManager();
	
	public final Map<X_UiBoxWindow, Command> closeCommands = new HashMap<X_UiBoxWindow, Command>();
	
	private SwingBoxUpdater boxUpdateManager;
	
	private X_UiWindowManager() {
		super();
	}
	
	public void addWindow() {
		
	}
	
	public void showWindow(X_UiBoxWindow window) {
	//this.windows.add(window)
	}
	
	public void setCloseCommand(X_UiBoxWindow window, Command closeCommand) {
		assert window != null;
		assert closeCommand != null;
		closeCommands.put(window, closeCommand);
	}
	
	public void close(X_UiBoxWindow xUiBoxWindow) {
		Command c = closeCommands.remove(xUiBoxWindow);
		if (c != null) {
			c.execute();
		}		
		//boxUpdateManager.
	}

}
