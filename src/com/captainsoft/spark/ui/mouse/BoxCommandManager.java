/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.ui.mouse;

import java.util.ArrayList;
import java.util.List;

import com.captainsoft.spark.control.MouseButton;
import com.captainsoft.spark.ui.box.UiBox;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.utils.Log;

import static com.captainsoft.spark.utils.Truth.is;

/**
 * Stores commands for boxes and finds them again if a mouse click occurred on a boxes.
 * Limited on one background UiBoxContainer, the "parent box".
 * Delegates execution of the command to an optional BoxCommandExecutor.
 *
 * @author mfringes
 */
public final class BoxCommandManager {

	// fields
	
	private final BoxCommandExecuter executer;
    private final BoxCommandList defaultCommandList;
    private final List<BoxCommandList> additionalCommandList;
    private final List<BoxMouseClickListener> boxMouseClickListeners = new ArrayList<BoxMouseClickListener>(3);
    private final List<BoxMouseMoveListener> boxMouseMoveListeners = new ArrayList<BoxMouseMoveListener>(3);
    private final UiBoxContainer parentBox;

	private UiBox currentBox = null;

	// constructors
	
	public BoxCommandManager(UiBoxContainer parentBox) {
		this(parentBox, new DefaultBoxCommandExecuter(), new BoxCommandList());
	}
	
	public BoxCommandManager(UiBoxContainer parentBox, BoxCommandList defaultCommandList) {
		this(parentBox, new DefaultBoxCommandExecuter(), defaultCommandList);
	}

	public BoxCommandManager(UiBoxContainer parentBox, BoxCommandExecuter executer, BoxCommandList defaultCommandList) {
		super();
		this.parentBox = parentBox;
		this.executer = executer;
		this.defaultCommandList = defaultCommandList;
		this.additionalCommandList = new ArrayList<BoxCommandList>(3);
	}
	
	// add, remove methods
	
	public void addBoxClickObserver(BoxMouseClickListener observer) {		
		boxMouseClickListeners.add(observer);
	}
	
	public boolean removeBoxClickObserver(BoxMouseClickListener observer) {		
		return boxMouseClickListeners.remove(observer);
	}
	
	public void addBoxMoveObserver(BoxMouseMoveListener observer) {		
		boxMouseMoveListeners.add(observer);
	}
	
	public boolean removeBoxMoveObserver(BoxMouseMoveListener observer) {		
		return boxMouseMoveListeners.remove(observer);
	}
	
	public void addAdditionalCommandList(BoxCommandList list) {
		additionalCommandList.add(list);
	}
	
	public void clearAllAditionalCommandLists() {
		additionalCommandList.clear();
	}
	
	// mouse methods		
	
	public void mouseEntered() {		
		for (BoxMouseMoveListener observer : boxMouseMoveListeners) {
			observer.mouseEntered(parentBox);
		}	
	}
	
	public void mouseExited() {		
		for (BoxMouseMoveListener observer : boxMouseMoveListeners) {
			observer.mouseExited(parentBox);
		}	
	}
	
	public void mouseMove(int x, int y) {		
		UiBox box = getBox(x, y);	
		if (is(box)) {
			int mx = getMx(box, x);
			int my = getMy(box, y);
			handleMouseMove(box, mx, my); 
		}
	}

	public void mousePressed(int x, int y) {
	    currentBox = getBox(x, y);
	}	

	public void mouseReleased(int x, int y, MouseButton button, boolean doubleClick) {
		UiBox box = getBox(x, y);			
		if (is(box) && box == currentBox) {
			int mx = getMx(box, x);
			int my = getMy(box, y);			
			Log.trace("Release for box: " + box);
			handleMouseClick(box, mx, my, button, doubleClick);
	    }
	}
	
	// mouse handling methods
	
	private void handleMouseClick(UiBox box, int x, int y, MouseButton button, boolean doubleClick) {
		for (BoxMouseClickListener observer : boxMouseClickListeners) {
    		observer.mouseClick(box, x, y, button);
    	}			
		
		ClickCommand cc = getCommand(box, button, doubleClick);
		if (is(cc)) {
			executer.execute(cc, box, x, y, button);
		}					
	}

	private void handleMouseMove(UiBox box, int x, int y) {
		for (BoxMouseMoveListener observer : boxMouseMoveListeners) {
			observer.mouseMoved(box, x, y);
		}
	}
	
	// private
	
	private ClickCommand getCommand(UiBox box, MouseButton button, boolean doubleClick) {
		for(BoxCommandList l : additionalCommandList) {
			ClickCommand c = l.getCommand(box, button, doubleClick);
			if (is(c)) {
				return c;
			}
		}
		return defaultCommandList.getCommand(box, button, doubleClick);
	}
	
	private UiBox getBox(int x, int y) {
		UiBox box = parentBox.getClickableBox(x, y);
		if (is(box) && box.enabled()) {
			return box;
		} else {
			return null;
		}
	}
	
	private int getMx(UiBox box, int x) {
		return (x - box.screenx());		
	}
	
	private int getMy(UiBox box, int y) {
		return (y - box.screeny());		
	}
	
}
