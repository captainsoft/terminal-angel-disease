/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.tools;

import java.io.IOException;
import java.util.Map;

import com.captainsoft.TADr.TadLang;
import com.captainsoft.TADr.loader.vb.VbPuzzleLoader;
import com.captainsoft.TADr.loader.vbFile.VbFile;
import com.captainsoft.TADr.model.puzzle.book.Book;
import com.captainsoft.tools.patch.PatchData;
import com.captainsoft.tools.patch.de.BookPatchGerman;
import com.captainsoft.tools.patch.en.BookPatchEnglish;

/**
 * Patch the book data for German and English books.
 *
 * @author mathias fringes
 */
public class BookPatcher {

	// fields
	
	private VbPuzzleLoader puzzleLoader;
	
	// constructors
	
	public BookPatcher() {
		super();
		puzzleLoader = new VbPuzzleLoader();
	}
	
	// public
	
	public void displayBookStructure() throws IOException {
		VbFile bookFile = puzzleLoader.createBookFile(VbFile.R);
		int recordCount = bookFile.recordCount();
		say("RECORD SIZE: " + recordCount);
		//
		for (int i = 1; i <= recordCount; i++) {
			Book book = puzzleLoader.loadBook(i);
			String text = book.text.replace("\r\n", " <br> ").replace("\"", "\\\""); 
			say("p.put(\"" + book.startPage + "\", \"" + text + "\");");				
		}
	}
	
	public void patch(PatchData patchData) throws IOException {
		Map<String, String> data = patchData.data();		
		//
		VbFile bookFile = puzzleLoader.createBookFile(VbFile.RW);
		for (int i = 1; i <= bookFile.recordCount(); i++) {
			Book book = puzzleLoader.loadBook(i);
			book.text = data.get(book.startPage + "");				
			puzzleLoader.saveBook(book);
		}
	}
	
	// private 
	
	public void say(Object text) {
		System.out.println(text.toString());
	}
	
	// main 
	
	public static void main(String[] args) {
		BookPatcher mp = new BookPatcher();			
		try {
			mp.displayBookStructure();

            // patch german
            TadLang.toGerman();
			mp.patch(new BookPatchGerman());

            // patch English
			TadLang.toEnglish();
			mp.patch(new BookPatchEnglish());

            // Done!
			mp.say("THROUGH WITH PATCHING!!! Vielen Dank ;)");

		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
}
