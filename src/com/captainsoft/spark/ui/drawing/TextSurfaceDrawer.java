/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.ui.drawing;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Draws text on a surface.
 *
 * @author mathias fringes
 */
public final class TextSurfaceDrawer {

    // fields

    private final Surface s;

    private int lineCount = 0;

    // constructors

    public TextSurfaceDrawer(Surface s) {
        this.s = s;
    }

    // public

    public void textArea(Color color, String text, Point p, int width, int height) {
        if (text == null || text.length() == 0) {
            return;
        }
        Rectangle2D r = s.getFontRectangle(text);
        if ((r.getWidth() > width - (p == null ? 0 : p.x)) || text.contains("<br>")) {
            drawMultipleLines(color, text, p, width, height, r);
        } else {
            drawOneLine(color, text, p, width, height);
        }
    }

    public int lines() {
        return lineCount;
    }

    // private

    private void drawMultipleLines(Color color, String text, Point p, int width, int height, Rectangle2D r) {
        StringBuilder ot = new StringBuilder(text);
        ArrayList<String> lines = new ArrayList<String>();
        StringBuilder currentLine = new StringBuilder();

        int start = 0;
        while (true) {
            int end = ot.indexOf(" ", start);
            String word = (end == -1) ? ot.substring(start) : ot.substring(start, end);
            Rectangle2D rt = s.getFontRectangle(currentLine.toString() + word);
            if ((rt.getWidth() > width - (p == null ? 0 : p.x)) || (word.equals("<br>"))) {
                // new line
                lines.add(currentLine.toString());
                if (word.equals("<br>")) {
                    currentLine = new StringBuilder();
                } else {
                    currentLine = new StringBuilder(word);
                }
            } else {
                currentLine.append(word);
            }
            if (currentLine.length() > 0) {
                currentLine.append(" ");
            }
            if (end == -1) {
                lines.add(currentLine.toString());
                break;
            }
            start = end + 1;
        }

        // ???

        int index = 0;
        int py;
        if (p == null) {
            py = (int) ((height - lines.size() * r.getHeight()) / 2);
            index++;
        } else {
            py = p.y;
        }

        // draw all text lines

        for (String line : lines) {
            Point pr = p;
            if (p == null) {
                pr = s.getTextMiddlePos(line, width + 2, height + 2);
            }
            s.text(color, line, pr.x, (int) (py + index * r.getHeight()));
            index++;
        }

        lineCount = lines.size();
    }

    private void drawOneLine(Color color, String text, Point p, int width, int height) {
        if (p == null) {
            p = s.getTextMiddlePos(text, width + 2, height + 2);
        }
        s.text(color, text, p.x, p.y);
        lineCount = 1;
    }

}
