/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.gui.windows;

import com.captainsoft.TADr.TAD;
import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.TADr.model.i18n.GuiMessages;
import com.captainsoft.menux.MxSeparator;
import com.captainsoft.menux.MxTopDownFlexLayout;
import com.captainsoft.menux.MxWindow;
import com.captainsoft.menux.WindowColors;
import com.captainsoft.spark.command.Command;
import com.captainsoft.spark.control.key.KeyCodes;
import com.captainsoft.spark.ui.BaseWindowController;
import com.captainsoft.spark.ui.Fonts;
import com.captainsoft.spark.ui.box.TextBox;
import com.captainsoft.spark.ui.box.UiBox;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.mouse.BoxCommandList;

import java.awt.*;

/**
 * Captainsoft Info Window!
 * 
 * @author mathias
 */
public final class CaptainsoftInfoWindow extends BaseWindowController {

    // constructors

    public CaptainsoftInfoWindow() {
        super();
    }

    // WindowController

	public UiBoxContainer createWindow(BoxCommandList mg) {

		CaptainsoftInfoWindowBox windowBox = new CaptainsoftInfoWindowBox();
		mg.setCmd(windowBox, new Integer[] {KeyCodes.F3, KeyCodes.Space, KeyCodes.Enter}, new Command() {
			public void execute() {
				TadRepo.inst().GameEngine().closeWindows();				
			}
		});
		return windowBox;
	}

	@Override
	public boolean isLenientModal() {
		return true;
	}

	//
	// nested classes
	
	private final class CaptainsoftInfoWindowBox extends MxWindow {
		
		public CaptainsoftInfoWindowBox() {
			super();
			set(155, 35, 410, 340);
			createSurface();
			
			title = "Information";						
			//
			Font f = new Font(Fonts.Verdana, Font.PLAIN, 9);
			Font fc = new Font(Fonts.Courier, Font.PLAIN, 12);
					
			String text = TadRepo.inst().Trans().word("infoWindow", TAD.Type, TAD.Version); 
			String[] texts = text.split(GuiMessages.sep);
			
			MxTopDownFlexLayout grid = new MxTopDownFlexLayout(this);
			grid.side_margin = 10;
			grid.def_gap = 4;
			grid.next_y = 20;
			grid.gap(10);
						
			for (String s : texts) {
				
				boolean courier = s.startsWith("*");
				if (courier) {
					s = s.substring(1);
				}
				
				TextBox b = new TextBox();
				b.text = s;
				int c = s.split(GuiMessages.br).length;
								
				b.font = courier ? fc : f;
								
				b.color(WindowColors.txt);				
				b.size(width - 20, 15 * c);
				b.height += courier ? 5 : 0; 
				b.alignCenter();
				
				grid.add(b);
				if (courier) {
					UiBox ms = new MxSeparator(30);
					ms.height = 20;
					grid.add(ms);
				}							
			}
		}
		
	}

}

