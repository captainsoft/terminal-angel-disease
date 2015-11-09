/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADtest.tester;

import java.awt.*;

import com.captainsoft.spark._sparkmuell.anim.*;
import com.captainsoft.spark._sparkmuell.anim.anims.*;
import com.captainsoft.spark.control.Command;
import com.captainsoft.spark.ui.*;
import com.captainsoft.spark.ui.box.ColorBox;
import com.captainsoft.spark.ui.swing.SwingBoxUpdater;
import com.captainsoft.spark.ui_swing.*;
import com.captainsoft.spark.utils.*;

public class AnimationTester {
	
	public static void main(String[] args) {
		
		ExitFrame fr = new ExitFrame();		
		fr.setSize(400, 300);		
		fr.setVisible(true);
	
		
		ColorBox screenBox = new ColorBox(400, 300);
		screenBox.createSurface();
		
		ColorBox b = new ColorBox(Color.RED);		
		b.size(10, 10);
		b.pos(0, 0);
		screenBox.add(b);
					
		SwingBoxPanel panel = new SwingBoxPanel(screenBox);
		fr.add(panel);
		
		SwingBoxUpdater mg = new SwingBoxUpdater();
		mg.registerComponent(panel, screenBox);
		
		Movie m = new Movie();
		Scene s = new Scene(screenBox, mg);		
		s.blur(10);
		m.addScene(s);
		m.addScene(s);
				
		
		AnimationChain ac = new AnimationChain();
				
		PosAnimation d = new PosAnimation(b);
		for (int i = 0; i < 5; i++) {
			d.setNext(new CPos((i + 1) * 20, 70));			
		}
		ac.add(d);
		ac.add(new Pause(10));
		ac.add(new VisibleAnimation(b, false));
		AnimationTable at = new AnimationTable();
		at.add(1, ac);
		at.add(3, new ColorAnimation(b, Color.BLUE));
		s.animate(at);		
		
		m.command(new Command() {			
			@Override
			public void execute() {
				Messenger.msg("and that was this movie!");
				System.exit(0);
			}
		});
		m.play();
		
		//JOptionPane.showConfirmDialog(fr, "Over!");
	}

}
