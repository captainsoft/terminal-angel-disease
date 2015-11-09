package main.renderEngineTests;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.captainsoft.spark.utils.Utils;

/**
 * 
 * @author mathias
 */
public class RenderEngineTests {
	
	// constzructors
	
	public RenderEngineTests() {
		super();
	}
	
	// protected
	
	protected void play() {
		JFrame frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		EgPanel panel = new EgPanel();
		frame.add(panel);
		
		
		//
		final CommandDispatcher f = new CommandDispatcher();
		Thread t = new Thread(f);
		t.start();
		f.p = panel;
		
		panel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				f.add(e);			
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
			
		});
				
		frame.setSize(800, 200);		
		frame.setVisible(true);				
	}	
	
	// main
	
	public static void main(String args[]) {
		final RenderEngineTests t = new RenderEngineTests();
		
		SwingUtilities.invokeLater(new Runnable() {			
			@Override
			public void run() {
				t.play();
			}
		});			
		System.out.println("DONE!");
	}
	
	// classes

	@SuppressWarnings("serial")
	private class EgPanel extends JPanel {
		public EgPanel() {		
			this.setBackground(Color.RED);
		}
		@Override
		public void paint(Graphics g) {		
			super.paint(g);
			g.setColor(Utils.rndColor());
			g.fillRect(0, 0, 200, 200);
			System.out.println("PAINT ME");
		}
	}
	
	private class CommandDispatcher implements Runnable {

		public EgPanel p;
		
		private final List<MouseEvent> events = new ArrayList<MouseEvent>();
		
		public synchronized void add(MouseEvent e) {
			this.events.add(e);
		}
		
		
	    // private long ms = System.nanoTime();
		private long ms = System.currentTimeMillis();
		private boolean dirty = false;
		
		private void status() {			  
			if(events.size() > 0) {											
				MouseEvent e = events.get(0);
				System.out.println("NEW E: " + e);
				System.out.println(SwingUtilities.isEventDispatchThread());
				events.clear();
				dirty = true;
			}	
			
			// measure time
			// long e =  System.nanoTime() - ms;
			long e = System.currentTimeMillis() -ms;
			// if (e > 1000000000) { // 1 second
			if (e > 100) { // 1/10 second
				System.out.println(new Date() + "elapsed =" + e  + " ");
				// ms = System.nanoTime();
				ms = System.currentTimeMillis();
			}
		}
		
		private void render() {
			if (!dirty) {
				return;
			}
			if (SwingUtilities.isEventDispatchThread() == false) {
				SwingUtilities.invokeLater(new Runnable() {				
					@Override
					public void run() {
						p.repaint();																
					}
				});
			} 			
			dirty = false;
		}
		
		@Override
		public void run() {
			while(true) {
				synchronized(this) {
					status();
					render();
				}
			}
			
		}
		
		
	}
}

