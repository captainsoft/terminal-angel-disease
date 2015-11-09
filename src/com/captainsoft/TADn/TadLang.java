/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn;

import java.io.File;

/**
 * Determines the folder where to find the resource files.
 * 
 * @author mathias fringes
 */
public final class TadLang {
	
	// fields
		
	private static String folder = "";
	
	// constructors
	
	static {
		estimateLanguage();
	}
	
	private TadLang() {
		super();		
	}
	
	// public	
	
	public static String folder() {
		return folder;
	}
	
	public static String file(String fileLocation) {
		return folder + "/" + fileLocation;
	}
	
	public static void toEnglish() {
		folder = "files_en/";
	}	
	
	public static void toGerman() {
		folder = "files/";
	}
	
	// private
	
	private static void estimateLanguage() {
		if (englishFolder()) {
			toEnglish(); 
		} else {				
			toGerman();		
		}
	}
	
	private static boolean englishFolder() { 
		return new File("files_en").isDirectory();
	}

}
