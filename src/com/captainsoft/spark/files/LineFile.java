/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.files;

import static com.captainsoft.spark.files.FileUtils.close;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads and writes line text files. Simple static methods in here.
 *
 * @author mathias fringes
 */
public final class LineFile {

    // constructors

    private LineFile() {
        super();
    }

    // public

    public static List<String> load(String filename) throws IOException {
        ArrayList<String> res = new ArrayList<String>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filename));
            while (reader.ready()) {
                res.add(reader.readLine());
            }
        } finally {
            close(reader);
        }
        return res;
    }

    public static void save(String filename, Iterable<String> data) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filename));
            for (String s : data) {
                writer.write(s);
                writer.newLine();
            }
        } finally {
            close(writer);
        }
    }

}