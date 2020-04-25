package com.captainsoft.tools.editorx.framw;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.UIManager;

public final class CsToolkit {

    private static final Font headFont = new Font("SansSerif", Font.BOLD | Font.ITALIC, 10);
    private static final Font smlFont = new Font("SansSerif", Font.PLAIN, 9);

    private static final Insets oneInsets = new Insets(1, 1, 1, 1);
    private static final Insets zeroInsets = new Insets(0, 0, 0, 0);

    private static final Dimension btnSize = new Dimension(64, 20);

    @SuppressWarnings("serial")
    private static final MediaTracker mt = new MediaTracker(new Component() {
    });
    private static final Toolkit tk = Toolkit.getDefaultToolkit();

    private CsToolkit() {
        ;
    }

    public static void addFullCenter(Container container, Component component) {
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = createGbc(0, 0, 1, 1, 100, 100, 1);
        gbl.setConstraints(component, gbc);
        container.setLayout(gbl);
        container.add(component);
    }

    public static GridBagConstraints createGbc(int x, int y, int width,
                                               int height, int xweight, int yweight) {
        return createGbc(x, y, width, height, xweight, yweight, 0);
    }

    public static GridBagConstraints createGbc(int x, int y, int width, int height,
                                               int xweight, int yweight, int insets) {

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        gbc.weightx = xweight;
        gbc.weighty = yweight;

        if (insets == 0) {
            gbc.insets = zeroInsets;
        } else if (insets == 1) {
            gbc.insets = oneInsets;
        } else {
            gbc.insets = new Insets(insets, insets, insets, insets);
        }

        gbc.anchor = GridBagConstraints.NORTHWEST;
        if (xweight == 100) {
            gbc.fill = GridBagConstraints.HORIZONTAL;
        }
        if (yweight == 100) {
            gbc.fill = GridBagConstraints.VERTICAL;
        }
        if (xweight == 100 && yweight == 100) {
            gbc.fill = GridBagConstraints.BOTH;
        }
        return gbc;
    }

    public static void setDefaultLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println();
        }
    }

    public static JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(smlFont);
        btn.setMinimumSize(btnSize);
        btn.setPreferredSize(btnSize);
        return btn;
    }

    public static JLabel createLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(smlFont);
        return lbl;
    }

    public static Image loadImage(String filename) {
        Image image = tk.getImage(filename);
        mt.addImage(image, 0);
        try {
            mt.waitForID(0);
        } catch (Exception e) {
            System.out.println("CsToolkit::loadImage could not load: "
                    + filename + " " + e);
            image = null;
        }
        return image;
    }

}
