/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.utils;

import java.io.*;
import java.text.*;
import java.util.*;

/**
 * Simple logger.
 *
 * @author fringes
 */
public final class Log {

    // fields

    public static boolean enabled = false;
    public static int[] no = new int[]{1, 0, 5};
    public static int[] only = new int[]{};

    private static PrintStream out = System.out;
    private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");

    // constructors

    private Log() {
        super();
    }

    // public

    public static void sys(Object object) {
        System.out.println(object);
    }

    public static void log(Object object) {
        log(9, object);
    }

    public static void log(int level, Object o) {
        if (!enabled) {
            return;
        }
        logs(level, o);
    }

    public static void call(String methodName) {
        log(1, "_call_" + methodName);
    }

    public static void debug(String text) {
        log(2, text);
    }

    public static void force(Object o) {
        log(9, o.toString());
    }

    public static void debug(Object o) {
        log(2, (o == null) ? "null" : o.toString());
    }

    public static void begin(String methodName) {
        log(1, "_begin_" + methodName);
    }

    public static void end(String methodName) {
        log(1, "_end_" + methodName);
    }

    public static void paint(Object text) {
        log(0, "_paint_" + text);
    }

    public static void trace(String text) {
        log(1, text);
    }

    public static void info(String text) {
        log(3, text);
    }

    public static void temp(String text) {
        log(0, "###### " + text + " ######");
    }

    public static void temp(char c, String text) {
        log(0, c + " " + text + " " + c);
    }

    public static void setOutputStream(PrintStream ps) {
        out = ps;
    }

    public static void warn(String warning) {
        log(4, warning);
    }

    public static boolean debug() {
        return true;
    }

    public static void notImpl(String text) {
        log(8, "NOT IMPLEMENTED " + text);
    }

    public static void error(String message, Exception e) {
        e.printStackTrace();
        log(8, message);
        log(8, e.getMessage());
        log(8, e);
    }

    public static void error(Exception e) {
        error("", e);
    }

    public static void fps(String fps) {
        log(5, fps);
    }

    // private

    private static synchronized void logs(int level, Object o) {

        if ((only.length > 0) && (!Utils.contains(only, level))) {
            return;
        }
        if (Utils.contains(no, level)) {
            return;
        }

        out.print(sdf.format(new Date()));

        String levelString = "[" + level + "]";
        out.print(" " + levelString + " ");
        out.println(o.toString());
    }

}