package com.captainsoft.tools.editorx.framw;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

/**
 * 
 * @author mathias fringes
 */
public final class Layouter {

	private GridBagLayout gbl;
	private GridBagConstraints gbc;
	private int cur_xline;
	private int cur_yline;
	private int columns;
	private int insets;	
	private Container target;

	public Layouter(Container target, int grid_width) {
		this(target, grid_width, 4);
	}

	public Layouter(Container target, int columns, int insets) {
		super();
		this.target = target;
		this.columns = columns;
		this.insets = insets;		
		gbl = new GridBagLayout();
		cur_xline = 0;
		cur_yline = 0;

		target.setLayout(gbl);
	}

	public void addLast(Component component) {
		int xspan = columns - cur_xline;

		gbc = CsToolkit.createGbc(cur_xline, cur_yline, xspan, 1, 100, 0, insets);
		gbl.setConstraints(component, gbc);

		cur_xline++;
		nextLine();
		target.add(component);
	}

	public Component add(Component component) {

		gbc = CsToolkit.createGbc(cur_xline, cur_yline, 1, 1, 0, 0, insets);
		gbl.setConstraints(component, gbc);
		target.add(component);

		cur_xline++;

		if (cur_xline >= columns)
			nextLine();

		return component;
	}
	
	public void set(Component c, int x, int y, int xspan, int yspan, int wx, int wy) {
		gbc = CsToolkit.createGbc(x, y, xspan, yspan, wx, wy, insets);
		gbl.setConstraints(c, gbc);
		target.add(c);
	}

	public void stuffEnd() {
		if (cur_xline < columns)
			addLast(new JPanel());
		else
			nextLine();
	}

	public void stuffLine(int span) {
		this.stuffLine(new JPanel(), span);
	}
	
	public void stuffLine(Component c, int span) {		
		gbc = CsToolkit.createGbc(cur_xline, cur_yline, span, 1, 100, 0, insets);
		gbl.setConstraints(c, gbc);
		target.add(c);

		cur_xline += span;

		if (cur_xline >= columns)
			nextLine();
	}

	public void nextLine() {
		cur_yline++;
		cur_xline = 0;
	}

	public void stuffFull(Component component) {
		gbc = CsToolkit.createGbc(0, cur_yline, columns, 1, 100, 100, insets);
		gbl.setConstraints(component, gbc);		
		target.add(component);
		nextLine();
	}

	public void stuffFull() {
		stuffFull(new JPanel());
	} 

}
