/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.x_vfs;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * A virtual file.
 *
 * @author mathias fringes
 */
public final class VfsFile {

	// fields
	
	private String name;
	private int size = 0;
	
	// constructors	
	
	public VfsFile() {
		super();
	}

	// accessors

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getSize() {
		return size;
	}
	
	// public
	
	public byte[] read(File file) throws IOException {		
		RandomAccessFile f = new RandomAccessFile(file, "r");
		long len = f.length();
		byte[] content = new byte[(int) len]; // great eh?
		f.read(content);
		f.close();
		return content;				
	}
	
}