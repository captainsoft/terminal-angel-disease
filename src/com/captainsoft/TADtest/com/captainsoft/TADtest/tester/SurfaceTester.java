package com.captainsoft.TADtest.tester;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.captainsoft.TADr.loader.ImageLoader;
import com.captainsoft.TADr.loader.vb.VbImageLoader;
import com.captainsoft.spark.ui.drawing.Surface;
import com.captainsoft.spark.ui_swing.ImagePanel;

/**
 * 
 *
 * @author mathias fringes
 */
public class SurfaceTester {

	
	public static void main(String[] args) {
					
		ImageLoader imageLoader = new VbImageLoader();		
		
				
		Surface interfaceSurface = imageLoader.load("ifc", 28);
								
		Surface scrollBackSurface = interfaceSurface.stamp(0, 0, 500, 200);
		
		
		interfaceSurface.text("HALLO HALLO HALLO", 40, 40);
		
		//			
		
		Frame frame = new Frame();
		frame.setLayout(new GridLayout(0, 1));
		frame.setSize(new Dimension(300, 300));
		frame.add(new ImagePanel(interfaceSurface.image()), BorderLayout.CENTER);
		frame.add(new ImagePanel(scrollBackSurface.image()), BorderLayout.CENTER);
					
		frame.addWindowListener(new WindowAdapter() {						
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}			
		});
		
		frame.setVisible(true);

	}

}
