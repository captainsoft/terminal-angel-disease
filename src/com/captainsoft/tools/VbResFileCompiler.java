/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.tools;

import java.io.*;
import java.util.*;

/**
 * A resource file that can contain many other files.
 * The file is only ultimately created when calling "compileResourceFile".
 * 
 * @author mathias fringes
 */
final class VbResFileCompiler {

	// fields

	private final String filename;
	private final Map<Integer, String> files;

	// constructors

	/**
	 * Create a new ResFile. The file is not yet physically created.
	 * 
	 * @param filename
	 */
	public VbResFileCompiler(String filename) {
		super();
		this.filename = filename;
		this.files = new HashMap<Integer, String>();
	}

	// public

	/**
	 * Adds a new file to this ResFile.
	 * 
	 * @param id the id of the file. mUst be unique.
	 * @param filename the filename. Must exists of course!
	 */
	public void addFile(Integer id, String filename) {
		if (files.containsKey(id)) {
			throw new IllegalArgumentException("Id " + id + " already contained!");
		}
		if (new File(filename).exists()) {
			files.put(id, filename);
		} else {
			throw new RuntimeException("File does not exists!: " + filename);
		}
	}

	/**
	 * Compiles the ResFile. Physically creates the file, deletes a former existing file!
	 * 
	 * @throws IOException
	 */
	public void compileResourceFile() throws IOException {		
		makeNewResFile();
		for (Map.Entry<Integer, String> file : this.files.entrySet()) {
			try {
				compileFile(file.getKey(), file.getValue());
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error for " + file);
			}
		}		
	}

	// private methods

	private void makeNewResFile() throws IOException {
        File file = new File(filename);
		file.delete();
        file.getParentFile().mkdirs();
		//
		// write 2000 bytes as header
		RandomAccessFile sf = new RandomAccessFile(filename, "rw");
		sf.writeByte('J');
		sf.writeByte('A');
		sf.writeByte('V');
		sf.writeByte('A');
		for (int i = 0; i < 1999 - 4; i++) {
			sf.writeByte(0);
		}
		sf.writeByte(200);
		sf.close();
	}
	
	private void compileFile(int id, String sourceFileName) throws IOException {
		//
		// prepare res file...
		if(!new File(sourceFileName).exists()) {
			throw new RuntimeException("Cannot find file: " + sourceFileName);
		} 
		System.out.println("compileFile: " + sourceFileName);
		RandomAccessFile sf = new RandomAccessFile(this.filename, "rw");
		long len = sf.length();
		// jump to the end (thats where the source file is added)
		sf.seek(len);
		long filePosAtEnd = sf.getFilePointer();
		//
		// write bytes to the end of file!
		byte[] bytes = readBytesFromFile(sourceFileName);
		int newFileLenght = bytes.length;
		sf.write(bytes, 0, bytes.length);
		//
		// write header information
		sf.seek(id * 8 - 1);
		// file pos
		filePosAtEnd++;
		sf.writeByte((int) (filePosAtEnd & 0x000000FF));
		sf.writeByte((int) ((filePosAtEnd & 0x0000FF00) >> 8));
		sf.writeByte((int) ((filePosAtEnd & 0x00FF0000) >> 16));
		sf.writeByte((int) ((filePosAtEnd & 0xFF000000) >> 24));
		// file length
		sf.writeByte((int) (newFileLenght & 0x000000FF));
		sf.writeByte((int) ((newFileLenght & 0x0000FF00) >> 8));
		sf.writeByte((int) ((newFileLenght & 0x00FF0000) >> 16));
		sf.writeByte((int) ((newFileLenght & 0xFF000000) >> 24));
		//
		sf.close();
	}
	
	private byte[] readBytesFromFile(String filename) throws IOException {
		RandomAccessFile f = new RandomAccessFile(filename, "r");
		long len = f.length();
		byte[] content = new byte[(int) len]; // great eh?
		f.read(content);
		f.close();
		return content;
	}
	
	// overridden
	
	@Override
	public String toString() {
		return "ResFile [" + filename + "]";
	}

}
