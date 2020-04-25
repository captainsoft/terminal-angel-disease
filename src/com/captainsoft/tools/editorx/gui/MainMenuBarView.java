package com.captainsoft.tools.editorx.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import com.captainsoft.tools.editorx.controller.MainMenuBarController;

/**
 * @author mathias fringes
 */
@SuppressWarnings("serial")
public final class MainMenuBarView extends JPanel {

    public MainMenuBarView(final MainMenuBarController controller) {

        // init interface
        setBackground(Color.YELLOW);
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        JButton b = new JButton();
        b.setText("MAP");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.showMapPropertyWindow();
            }
        });
        add(b);

        b = new JButton();

        add(b);
        add(new JSeparator());

        b = new JButton();
        add(b);
    }

}