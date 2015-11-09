/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.files;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Utilities for files.
 *
 * @author mathias fringes
 */
public final class FileUtils {
	
	// constructors
	
	private FileUtils() {
		super();
	}
	
	// public
	
	public static void copyFile(String source, String dest) throws IOException {
		FileInputStream sourceStream = null;
		FileOutputStream destStream = null;
		try {
			sourceStream = new FileInputStream(source);
			destStream = new FileOutputStream(dest);
			byte[] buf = new byte[4096];
			int len;
			while ((len = sourceStream.read(buf)) > 0) {
				destStream.write(buf, 0, len);
			}			
		} finally {
			close(sourceStream);
			close(destStream);			
		}
	}
	
	public static void close(Closeable entity) {
		close(entity, null);
	}
	
	public static void close(Closeable entity, String message) {
		if (entity != null) {
			try {
				entity.close();
			} catch (IOException e) {
				throw new SparkIoException((message != null) ? "Error: " + message : "Error closing " + entity, e);
			}
		}
	}

}