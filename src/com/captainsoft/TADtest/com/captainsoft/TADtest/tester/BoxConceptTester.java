package com.captainsoft.TADtest.tester;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.captainsoft.spark.*;
import com.captainsoft.spark.control.MouseButton;
import com.captainsoft.spark.ui.*;
import com.captainsoft.spark.ui.box.UiBox;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.drawing.*;
import com.captainsoft.spark.ui.mouse.*;
import com.captainsoft.spark.ui.mouse.awt.*;
import com.captainsoft.spark.ui.swing.SwingBoxUpdater;
import com.captainsoft.spark.ui_swing.*;

/**
 * The interface should do
 * 
 *   1. A click inside the container should enable or disable the icon box.
 *      The state is reflected in a different color.
 *   2. a mouse move in the container (but not the icon box) should print (or label) 
 *      the current coordinates.
 *   3. A click inside the icon box (if enabled) should shoe an message box
 *
 * @author mathias fringes
 */
public class BoxConceptTester implements BoxMouseClickListener, BoxMouseMoveListener {
		
	// statics
	
	public static void main(String[] args) {
		new BoxConceptTester();	
	}
	
	// fields
		
	private SwingBoxUpdater boxUpdateManager = new SwingBoxUpdater();
	private Color iconColor = Color.YELLOW;
	private Color inWindColor = Color.ORANGE;
	public int inWndState = 0;
	
	private UiBox iconBox;	
	private UiBoxContainer containerBox;
	private UiBoxContainer windowBox;
	private UiBox inWindowBox;
	private UiBox firstWndBox;
	private UiBoxContainer secondWndBox;
	
	// constructors
	
	public BoxConceptTester() {
		super();
		
		// frame, awt stuff
		Frame frame = new Frame();				
		frame.addWindowListener(new WindowAdapter() {						
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}			
		});
		frame.setLayout(new BorderLayout());
		frame.setSize(600, 500);				
		
		// private inits
		createTheBoxes();
		 
		 
		BoxCommandManager boxCommandManager = new BoxCommandManager(containerBox);
		boxCommandManager.addBoxClickObserver(this);
		boxCommandManager.addBoxMoveObserver(this);
		
		SwingBoxPanel awtBoxPanel;
		
		awtBoxPanel = new SwingBoxPanel(containerBox);
		awtBoxPanel.setBackground(Color.RED);					
		frame.add(awtBoxPanel, BorderLayout.CENTER);
		
		awtBoxPanel.addMouseListener(new AwtBoxMouseClickAdapter(boxCommandManager));
		awtBoxPanel.addMouseMotionListener(new AwtBoxMouseMoveAdapter(boxCommandManager));
		
		boxUpdateManager.registerComponent(awtBoxPanel, containerBox);
		
		frame.setVisible(true);			
	}

	// private methods
	
	private void createTheBoxes() {
		
		containerBox = new UiBoxContainer("container", 25, 35, 400, 400) {
			@Override protected void draw(Surface s) {
				s.color(Color.GREEN);
				s.fill(0, 0, 399, 300);		
			}
		};
		containerBox.createSurface();
					
		iconBox = new UiBox("icon", 20, 20, 100, 25) {
			@Override protected void draw(Surface s) {
				s.color(iconColor);
				s.fill(0, 0, 100, 25);				
			}		
		};		
		
		windowBox = new UiBoxContainer("window", 100, 80, 250, 180) {
			@Override protected void draw(Surface s) {
				s.color(Color.BLUE);
				s.fill(0, 0, width, height);				
			}
		};
		
		inWindowBox = new UiBox("inWindowBox" , 10, 10, 40, 40) {						
			@Override protected void draw(Surface s) {
				s.color(inWindColor);
				s.fill(0, 0, width, height);				
				s.text(Color.BLACK, "ST: " + inWndState, 5, 10);
			}
		};
		
		firstWndBox = new UiBox("firstWndBox", 60, 70, 50, 40) {
			@Override protected void draw(Surface s) {
				s.color(Color.MAGENTA);
				s.fill(0, 0, width, height);
			}
		};
		
		secondWndBox = new UiBoxContainer("secondWndBox", 70, 50, 65, 90) {
			@Override protected void draw(Surface s) {
				s.color(Color.WHITE);
				s.fill(0, 0, width, height);
			}
		};
		secondWndBox.add(new UiBoxContainer("smallInsideBox", 5, 5, 10, 10) {
			@Override protected void draw(Surface s) {
				s.color(Color.RED);
				s.fill(0, 0, width, height);
			}
		});
		secondWndBox.visible(false);
		
		windowBox.add(inWindowBox, firstWndBox, secondWndBox);
		
			
		containerBox.add(iconBox, windowBox);		
		containerBox.update(); 
								
	}

	// implementations
	
	@Override
	public void mouseClick(UiBox box, int x, int y, MouseButton button) {
		
	   if (box == iconBox) {		   
		   System.out.println("[ICON] click at (" + x + " / " + y + "); button: " + button);		    		 
		   JOptionPane.showMessageDialog(null, "sdsd", "sdsd", JOptionPane.INFORMATION_MESSAGE);
		   
	   } else if (box == containerBox) {		   
		    System.out.println("[PARENT] click at (" + x + " / " + y + "); button: " + button);
		   		 
			iconColor = (iconColor == Color.YELLOW) ? Color.DARK_GRAY : Color.YELLOW;	
			iconBox.enabled(iconColor != Color.DARK_GRAY);
			boxUpdateManager.updateBoxDrawing(iconBox);
								   
	   } else if (box == inWindowBox) {
		   inWndState++;
		   // boxUpdateManager.updateBoxDrawing(inWindowBox);
		   
		   
		   // warum updateParentBox -> vill. weil sonst zweimal geupdatet wird!?
		   // in update werden graphics operation ausgeführt -> villeicht nicht sinnvoll wenn nur 
		   // ...neu gezeichnet werden muss. vill doublebuffer mit backgroundimage einführen?
		   // ->unterschied zwischen update und draw...
		   firstWndBox.visible(!firstWndBox.visible());
		   secondWndBox.visible(!secondWndBox.visible());
		   boxUpdateManager.updateBoxDrawing(windowBox);
		   
	   } else if (box == null) {
		   System.out.println("[-no box found-] click at (" + x + " / " + y + "); button: " + button);
	   
	   } else {
		   System.out.println("[BOX] " + box.name + " click at (" + x + " / " + y + "); button: " + button);
	   }
	}

	@Override
	public void mouseEntered(UiBox box) {		
	}

	@Override
	public void mouseExited(UiBox box) {	
	}

	@Override
	public void mouseMoved(UiBox box, int x, int y) {
	  if (box == containerBox) {
		  System.out.println("[PARENT] move at (" + x + " / " + y + ")");
	  } else if (box == iconBox) {
		  System.out.println("[iconbox] move at (" + x + " / " + y + ")");
	  }
	}
	
}
