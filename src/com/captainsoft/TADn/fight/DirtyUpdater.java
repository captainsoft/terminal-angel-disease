/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.fight;

import java.util.HashSet;
import java.util.Set;

import com.captainsoft.TADn.gui.MainViewer;
import com.captainsoft.spark.ui.box.UiBox;
import com.captainsoft.spark.utils.Log;

/**
 * 
 *
 * @author mathias fringes
 */
final class DirtyUpdater {
	
	private MainViewer mainViewer;
	private Set<UiBox> dirtyBoxes = new HashSet<UiBox>();

	public DirtyUpdater(MainViewer mainViewer) {
		super();
		this.mainViewer = mainViewer;
	}
	
	public void dirty(UiBox ... boxes) {
		synchronized(dirtyBoxes) {
			for (UiBox box : boxes) {
				dirtyBoxes.add(box);
			}
		}
	}
	
	public void dirtyUpdate(UiBox ... boxes) {
		dirty(boxes);
		dirtyUpdate();
	}
	
	public void dirtyUpdate() {
		Log.force("dirty update");
		synchronized(dirtyBoxes) {
			if (dirtyBoxes.size() == 0) {
				Log.warn("Dirty update with no boxes!");
				return;
			}
			UiBox[] boxes = new UiBox[dirtyBoxes.size()];
			dirtyBoxes.toArray(boxes);	
			mainViewer.updateBox(boxes);
			dirtyBoxes.clear();
		}
	}

}