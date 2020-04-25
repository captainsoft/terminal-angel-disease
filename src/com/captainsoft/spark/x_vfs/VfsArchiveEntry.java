/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.x_vfs;

/**
 * @author mathias fringes
 */
public final class VfsArchiveEntry {

    public final int position;
    public final int size;
    public final String name;

    public VfsArchiveEntry(VfsFile file, int position) {
        this(file.getName(), position, file.getSize());
    }

    public VfsArchiveEntry(String name, int position, int size) {
        super();
        this.name = name;
        this.position = position;
        this.size = size;
    }

}