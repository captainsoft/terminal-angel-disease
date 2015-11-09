/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.loader.vbFile;

import static com.captainsoft.TADn.loader.vbFile.VbFileUtils.decodeText;
import static com.captainsoft.TADn.loader.vbFile.VbFileUtils.turnInt;
import static com.captainsoft.TADn.loader.vbFile.VbFileUtils.turnShort;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.captainsoft.spark.files.FileUtils;

/**
 * Encapsulates the old VbFile format.
 *
 * @author mathias fringes
 */
public final class VbFile implements Closeable {
	
	// fields
		
	public static final String R = "r";
	public static final String RW = "rw";
	
	private final RandomAccessFile file;
	private final String filename;	
	
	private int chunkSize = 1;
	private int recordSize = 0;
	
	// constructors
	
	public VbFile(String filename, String mode) throws FileNotFoundException {
		super();
		this.filename = filename;		
		this.file = new RandomAccessFile(filename, mode);
	}

	// accessors
	
	/**
	 * Sets the size of one single record in bytes.
	 * 
	 * @param recordSize
	 */
	public void setRecordSize(int recordSize) {		
		this.recordSize = recordSize;
	}
	
	/**
	 * Sets the count of records in one chunk.
	 * 
	 * @param chunkSize
	 */
	public void setChunkSize(int chunkSize) {
		this.chunkSize = chunkSize;
	}
	
	public long length() throws IOException {
		return file.length();
	}
	
	public int recordCount() throws IOException {
		return (int) (length() / recordSize * chunkSize);
	}
	
	// public 
		
	public void seekPosition(int position) throws IOException {
		if (recordSize > 0) {
			throw new RuntimeException("Cannot seek for position when record size is set!");
		}
		file.seek(position);
	}          	
		   
	public void seekRecord(int chunk, int id) throws IOException {
		if (recordSize < 1) {
			throw new RuntimeException("The recordSize has not yet set!");
		}
		if (chunkSize < 1) {
			throw new RuntimeException("The chunkSize has not yet set!");
		}
		int position = (chunk - 1) * (chunkSize * recordSize) + (id - 1) * recordSize;
	    file.seek(position);
	}
	
	public int readByte() throws IOException {
		return file.readUnsignedByte();
	}
	
	public byte[] readBytes(int length) throws IOException {
		byte[] data = new byte[length];                  
        file.read(data);       
        return data;
	}
	
	public int readShort() throws IOException {
		return turnShort(file.readUnsignedByte(), file.readUnsignedByte());
	}
	
	public int readInt() throws IOException {
		return turnInt(file.readUnsignedByte(), file.readUnsignedByte(), file.readUnsignedByte(), file.readUnsignedByte());	
	}
		
	public String readString(int length) throws IOException {
		StringBuffer tmp = new StringBuffer(length);
        for (int k = 0; k < length; k++) {
            tmp.append((char) file.readUnsignedByte());
        }  
        return tmp.toString().trim();
	}
	
	public String readEncodedString(int length) throws IOException {
		 return decodeText(new StringBuilder(readString(length))).trim();    
	}
	
	public void writeByte(int value) throws IOException {
		file.writeByte(value);
	}
	
	public void writeEncodedString(String text, int len) throws IOException {
		String encodedText = VbFileUtils.encodeText(fill(text, len));
		for (int i = 0; i < encodedText.length(); i++) {
			file.writeByte(encodedText.charAt(i));
		}
	}
	
	public void writeShort(int value) throws IOException {
		file.writeByte((int) (value& 0x000000FF));
		file.writeByte((int) ((value & 0x0000FF00) >> 8));		
	}
	
	public void writeString(String text, int len) throws IOException {
		String fullText = fill(text, len);
		for (int i = 0; i < fullText.length(); i++) {
			file.writeByte(fullText.charAt(i));
		}
	}
	
	// private
		
	private String fill(String text, int len) {		
		if (text.length() > len) {
			throw new IllegalArgumentException("The string is already longer. Is " + text.length() + " must: " + len);
		}
		if (text.length() == len) {
			return text;
		}
		StringBuilder sb = new StringBuilder(len);
		sb.append(text);
		while (sb.length() < len) {
			sb.append(" ");			
		}
		assert (sb.toString().length() == len); 
		return sb.toString();		
	}		
	
	// overridden
	
	@Override
	public String toString() {
		return "VbFile: " + filename;
	}

	// Closeable
	
	@Override
	public void close() throws IOException {
		FileUtils.close(file);
	}
		
}