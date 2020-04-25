/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.x_vfs;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.captainsoft.spark.utils.ExcUtils;

/**
 * @author mathias fringes
 */
public final class VfsArchive {

    // fields

    private Map<String, VfsFile> list = new HashMap<String, VfsFile>();
    // private Map<String, VfsArchiveEntry> entries = new HashMap<String, VfsArchiveEntry>();
    private String name;

    // constructors

    public VfsArchive() {
        super();
    }

    // accessors

    public String getName() {
        return name;
    }

    public void setName(String name) {
        ExcUtils.argNotEmpty(name);
        this.name = name;
    }

    // methods

    public void addFile(VfsFile file) {
        ExcUtils.argNotNull("file", file);
        ExcUtils.argNotEmpty(file.getName());
        ExcUtils.argPlusPositive("file.size", file.getSize());
        //
        list.put(file.getName(), file);
        //entries.put(file.getName(), new VfsArchiveEntry(file));
    }


    //

    public void compilerTo(File file) throws IOException {
        ExcUtils.argNotNull("file", file);
        file.delete();
        // RandomAccessFile sf = new RandomAccessFile(file, "rw");
    }

}