/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package eventDispatcherTest;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui_swing.SwingBoxPanel;

/**
 * 
 *
 * @author mathias fringes
 */
public class Test {
	
	GameEventsQueue events;
	UiBoxContainer sb;
	private SwingBoxPanel pnl;
		
	public Test() {
		super();
		events = new GameEventsQueue();
	}
		
	public void play() {
		JFrame frame = new JFrame();
		frame.setSize(300, 400);					
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing (WindowEvent e) {
				System.out.println("goodbye " + new Date());
				System.exit(0);
			}
		});
		frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {			
				
					System.out.println("mouse pressed: " + e.getX() + "/" + e.getY());
					events.put("MAP("+e.getX()+"/"+e.getY()+")");									
			}
		});
		sb = new UiBoxContainer(200, 200);
		sb.createSurface();
		sb.surface().clear(Color.RED);
		sb.update();
		pnl = new SwingBoxPanel(sb);
		frame.getContentPane().add(pnl);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		Test t = new Test();				
		t.new GameEventEaterThread(t.events).start();
		t.play();		
	}
	
	//
	//
	
	private class GameEventEaterThread extends Thread {
		private GameEventsQueue queue;
		GameEventEaterThread(GameEventsQueue queue) {
			this.queue = queue;
		}
		@Override
		public void run() {		
			super.run();
			while(true) {				
					try {					
						String eat = "";											
						eat = queue.get();
					
						if (eat != null)  {
							System.out.println("EATING:" + eat + " IN QUEUE" + queue.size());
							for (int i = 255; i > 0; i-=20) {
								Thread.sleep(100);
								sb.surface().clear(new Color(i,i,i));
								sb.update();
								SwingUtilities.invokeLater(new Runnable() {
									@Override
									public void run() {
										//pnl.repaint();	
										pnl.paintImmediately(0, 0, 400, 500);
									}
								});								
							}
							System.out.println("EATING:" + eat + "OVER");
						} else {
							System.out.println("++++ queue is leer!");
						}
					} catch (InterruptedException e) {					
							e.printStackTrace();
					}							
			}
		}		
	}

}