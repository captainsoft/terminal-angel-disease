/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.menu.swing;

import com.captainsoft.spark.ui.Fonts;
import com.captainsoft.spark.utils.Log;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

/**
 * A JTextPane with a slightly easier interface. No offence, Oracle.
 *
 * @author mathias fringes
 */
final class EasyTextArea extends JTextPane {

    private final static Font TextFont = new Font(Fonts.Verdana, Font.PLAIN, 10);

    private final SimpleAttributeSet center;
    private final StyledDocument doc;

    public EasyTextArea(int align) {
        setFont(TextFont);
        setEditable(false);
        doc = getStyledDocument();
        center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, align);
    }

    @Override
    public void setText(String text) {
        super.setText("");
        try {
            doc.insertString(0, text, null);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);
        } catch (BadLocationException e) {
            Log.force("Error displaying text in EasyTextArea: " + e);
        }
    }

}