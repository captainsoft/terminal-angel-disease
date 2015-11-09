/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.menu.swing;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.captainsoft.TADn.menu.GamesList;
import com.captainsoft.TADn.menu.MenuController;
import com.captainsoft.TADn.party.Game;
import com.captainsoft.spark.ui.Fonts;

/**
 * Creates the panels for the menu.
 * 
 * @author mathias fringes
 */
final class MenuPanelCreator {

	// static

	final static Dimension PanelSize = new Dimension(240, 185);
	private final static Font listFont = new Font(Fonts.Verdana, Font.BOLD, 11);

	private final MenuController menuController;

	// constructors

	public MenuPanelCreator(MenuController menuController) {
		this.menuController = menuController;
	}

	// public methods

	/**
	 * Creates an empty menu frame panel, already with the correct size.
	 * 
	 * @return an empty panel.
	 */
	public Container createEmptyPanel() {
		JPanel pnl = new JPanel();
		pnl.setLayout(null);
		pnl.setSize(MenuPanelCreator.PanelSize);
		return pnl;
	}

	public Container createMainMenuPanel(Game game) {
		Container pnl = createEmptyPanel();
		int btnWidth = 240;
		int btnHeight = 25;
		int btnBtmInset = 7;
		int btnLeftInset = 1;
		int btnCount = 0;
		// start new game
		final JButton btnNewGame = new JButton(
				menuController.trans.word("menu.main.new"));
		btnNewGame.setBounds(btnLeftInset, (btnBtmInset + btnHeight)
				* btnCount++, btnWidth, btnHeight);
		pnl.add(btnNewGame);
		// load game
		final JButton btnLoadGame = new JButton(
				menuController.trans.word("menu.main.load"));
		btnLoadGame.setBounds(btnLeftInset, (btnBtmInset + btnHeight)
				* btnCount++, btnWidth, btnHeight);
		pnl.add(btnLoadGame);
		// save game
		final JButton btnSaveGame = new JButton(
				menuController.trans.word("menu.main.save"));
		btnSaveGame.setBounds(btnLeftInset, (btnBtmInset + btnHeight)
				* btnCount++, btnWidth, btnHeight);
		pnl.add(btnSaveGame);
		// back to the game
		final JButton btnBack = new JButton(
				menuController.trans.word("menu.main.back"));
		btnBack.setBounds(btnLeftInset, (btnBtmInset + btnHeight) * btnCount++,
				btnWidth, btnHeight);
		pnl.add(btnBack);
		// ... this is enabled only if there is a game going on...
		if (game == null) {
			btnBack.setEnabled(false);
			btnSaveGame.setEnabled(false);
		} else {
			btnSaveGame.setEnabled(true);
			btnBack.setEnabled(true);
		}
		// Settings
		final JButton btnSettings = new JButton(
				menuController.trans.word("menu.main.settings"));
		btnSettings.setBounds(btnLeftInset, (btnBtmInset + btnHeight)
				* btnCount++, btnWidth, btnHeight);
		pnl.add(btnSettings);
		// quit the application!
		final JButton btnExit = new JButton(
				menuController.trans.word("menu.main.quit"));
		btnExit.setBounds(btnLeftInset, (btnBtmInset + btnHeight) * btnCount++,
				btnWidth, btnHeight);
		pnl.add(btnExit);
		//
		// create and add the action listener
		ActionListener actList = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object source = e.getSource();
				// new adventure
				if (source == btnNewGame) {
					MenuPanelCreator.this.menuController.startNewGame();
				}
				// settings
				else if (source == btnSettings) {
					MenuPanelCreator.this.menuController.showSettingsView();
				}
				// load game
				else if (source == btnLoadGame) {
					MenuPanelCreator.this.menuController.showLoadGameView();
				}
				// save game
				else if (source == btnSaveGame) {
					MenuPanelCreator.this.menuController.showSaveGameView();
				}
				// back to the game
				else if (source == btnBack) {
					MenuPanelCreator.this.menuController.close();
				}
				// quit game
				else if (source == btnExit) {
					MenuPanelCreator.this.menuController.showQuitGameFrame();
				}
			}
		};
		// ...register the action listeners
		btnNewGame.addActionListener(actList);
		btnExit.addActionListener(actList);
		btnBack.addActionListener(actList);
		btnLoadGame.addActionListener(actList);
		btnSaveGame.addActionListener(actList);
		btnSettings.addActionListener(actList);
		//
		return pnl;
	}

	/**
	 * Creates the panel for the save-a-game view. Comes complete with behavior.
	 * 
	 * @return the panel
	 */
	public Container createSaveGamePanel(Game game) {
		Container pnl = createEmptyPanel();
		pnl.setLayout(null);
		//
		// label
		addHeadLine(pnl, menuController.trans.word("menu.save.caption"));
		//
		// game list
		final JList<String> gameList = addGameList(pnl);
		//
		// buttons
		final JButton btnSave = addCommandButton(pnl,
				menuController.trans.word("menu.save.button"));
		btnSave.setEnabled(false);
		addBackButton(pnl, menuController);
		//
		// input text area
		final JTextField text = new JTextField();
		text.setBounds(92, pnl.getHeight() - 25, 95, 21);
		pnl.add(text);
		//
		// list behavior
		ListSelectionListener itemListener = new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				handleListButton(e, gameList, btnSave);
				text.setText(gameList.getSelectedValue());
			}
		};
		gameList.addListSelectionListener(itemListener);
		//
		// button behavior(save)
		ActionListener actList = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnSave) {
					DefaultListModel<String> dml = (DefaultListModel<String>) gameList.getModel();
					Object[] onames = dml.toArray();
					String[] names = new String[onames.length];
					for (int i = 0; i < names.length; i++) {
						names[i] = onames[i].toString();
					}
					int index = gameList.getSelectedIndex(); 
					names[index] = text.getText();
					MenuPanelCreator.this.menuController.saveGame(index, names);
				}
			}
		};
		btnSave.addActionListener(actList);
		//
		// text behavior
		KeyListener textList = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String name = text.getText();
				btnSave.setEnabled(GamesList.isGoodGameName(name));
			}
		};
		text.addKeyListener(textList);
		//
		// .. set list selection if this is already a saved game
		if (game != null) {
			int savegameNo = game.number();
			if (savegameNo >= 0) {
				gameList.setSelectedIndex(savegameNo);
			}
		}
		//
		return pnl;
	}

	/**
	 * Creates the panel for the load-a-game view. Comes complete with behavior.
	 * 
	 * @return the panel
	 */
	public Container createLoadGamePanel() {
		Container pnl = this.createEmptyPanel();
		pnl.setLayout(null);
		//
		// label
		addHeadLine(pnl, menuController.trans.word("menu.load.caption"));
		//
		// game list
		final JList<String> gameList = addGameList(pnl);
		//
		// buttons
		final JButton btnLoad = addCommandButton(pnl,
				menuController.trans.word("menu.load.button"));
		btnLoad.setEnabled(false);
		addBackButton(pnl, menuController);
		//
		// list behavior
		ListSelectionListener itemListener = new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				handleListButton(e, gameList, btnLoad);
			}
		};
		gameList.addListSelectionListener(itemListener);
		//
		// button behavior
		ActionListener actList = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnLoad) {
					int index = gameList.getSelectedIndex();
					String name = gameList.getSelectedValue();
					MenuPanelCreator.this.menuController.loadGame(index, name);
				}
			}
		};
		btnLoad.addActionListener(actList);
		//
		return pnl;
	}

	// private

	private JList<String> addGameList(Container panel) {
		DefaultListModel<String> lm = new DefaultListModel<String>();
		final JList<String> gameList = new JList<String>(lm);
		gameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		gameList.setBounds(2, 25, panel.getWidth() - 4, 130);
		gameList.setFont(listFont);
		gameList.setFixedCellHeight(14);
		gameList.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		//
		java.util.List<String> games = menuController.loadGameList();
		for (String gameName : games) {
			lm.addElement(gameName);
		}
		panel.add(gameList);
		return gameList;
	}

	private void handleListButton(ListSelectionEvent e, JList<String> gameList, JButton button) {
		if (e.getValueIsAdjusting() == false) {
			String name = gameList.getSelectedValue();
			boolean enabled = ((gameList.getSelectedIndex() != -1) && (GamesList.isGoodGameName(name)));
			button.setEnabled(enabled);
		}
	}

	// static helper methods

	public static Container createSeparator(int x, int y, int width) {
		JPanel p = new JPanel();
		p.setBackground(Color.BLACK);
		p.setBounds(x, y, width, 1);
		return p;
	}

	public static JButton addCommandButton(Container panel, String text) {
		JButton btn = new JButton(text);
		btn.setBounds(0, panel.getHeight() - 25, 90, 22);
		panel.add(btn);
		return btn;
	}

	public static JButton addBackButton(Container panel, final MenuController menuController) {
		final JButton btnBack = new JButton("<<");
		btnBack.setBounds(panel.getWidth() - 51, panel.getHeight() - 25, 50, 22);
		panel.add(btnBack);
		//
		ActionListener actList = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnBack) {
					menuController.showMainMenuView();
				}
			}
		};
		btnBack.addActionListener(actList);
		//
		return btnBack;
	}

	public static void addHeadLine(Container panel, String text) {
		JLabel lbl = new JLabel(text);
		lbl.setBounds(2, 0, 200, 20);
		panel.add(lbl);
		panel.add(MenuPanelCreator.createSeparator(2, 20, panel.getWidth() - 4));
	}

}