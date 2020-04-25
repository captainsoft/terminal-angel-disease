/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr;

import java.io.File;

/**
 * Specifies the language for the game.
 * Determined by the folder where to find the resource files.
 *
 * @author mathias fringes
 */
public final class TadLang {

    // fields

    private static String folder = "";

    // constructors

    static {
        estimateLanguage();
        estimateDemo();
    }

    private TadLang() {
        super();
    }

    // public

    public static String folder() {
        return folder + "/";
    }


    public static void folder(String folder) {
        TadLang.folder = folder;
    }

    public static String file(String fileLocation) {
        return folder() + fileLocation;
    }

    public static void toEnglish() {
        folder = "files_en";
    }

    public static void toGerman() {
        folder = "files";
    }

    public static boolean isEnglish() {
        return folder.contains("en");
    }

    // private

    private static void estimateDemo() {
        if (new File(folder + "_DEMO").isDirectory()) {
            folder += "_DEMO";
        }
    }

    private static void estimateLanguage() {
        if (englishFolderExists()) {
            toEnglish();
        } else {
            toGerman();
        }
    }

    private static boolean englishFolderExists() {
        return (new File("files_en").isDirectory() || new File("files_en_DEMO").isDirectory());
    }

}