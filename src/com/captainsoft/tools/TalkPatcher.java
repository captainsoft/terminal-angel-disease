/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.tools;

import java.io.IOException;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import com.captainsoft.TADr.loader.vb.VbPuzzleLoader;
import com.captainsoft.TADr.model.puzzle.talk.TalkPage;
import com.captainsoft.tools.patch.de.NpcNamePatchGerman;
import com.captainsoft.tools.patch.de.TalkPatchGerman;
import com.captainsoft.tools.patch.en.NpcNamePatchEnglish;
import com.captainsoft.tools.patch.en.TalkPatchEnglish;

/**
 * Run this, if you want to update the talk data with the
 * talks from the "TalkPatch***"-classes (will also do the English translation!)
 *  
 *
 * @author mathias fringes
 */
public class TalkPatcher {
	
	// fields

    private static final String OriginalFile = "../_working/src_files/files_source/data/talk_orig.dat";

    private VbPuzzleLoader puzzleLoader;
    private String FileFolderName = "";

	// constructor
	
	public TalkPatcher() throws IOException {
		super();
		puzzleLoader = new VbPuzzleLoader();		
	}
	
	// methods
	
	public TalkPage loadTalk(int map, int id) throws IOException {
		return puzzleLoader.loadTalkPage(OriginalFile, map, id);
	}
	
	private void saveTalk(int map, TalkPage talk) throws IOException {
		puzzleLoader.saveTalkPage(FileFolderName + "/dat/talk.dat", map, talk);
	}
	
	public void patchMe(Map<String, String> talks, Map<String, String> names) throws IOException {		
		for (int m = 1; m < 21; m++) {
			for (int i = 1; i < 100; i++) {
				TalkPage t = loadTalk(m, i);											
				
				String ms = m < 10 ? "0" + m : m + "";
				String is = i < 10 ? "0" + i : i + "";
				String key = ms + "-" + is;
				String patched = talks.get(key); 
				
				t.text = patched;				
				t.name = names.containsKey(t.name) ? names.get(t.name) : t.name; 
				
				saveTalk(m, t);
				System.out.println("patched " + key);					
			}
		}
	}
	
	public void displayNpcNames() throws IOException {		
		SortedSet<String> names = new TreeSet<String>();
		for (int m = 1; m < 21; m++) {
			for (int i = 1; i < 100; i++) {
				TalkPage t = loadTalk(m, i);
				String name = t.name;
				if (!name.startsWith(">>") && t.portraitId > 4) {
					names.add(name);																	
				}
			}
		}
		//
		for(String name : names) {
			System.out.println("p.put(\"" + name + "\", \"" + name + "\");");
		}
	}
	
	// static main
	
	public static void main(String[] args) {
        //
		try {

			TalkPatcher tp = new TalkPatcher();
            //
            // german patch
            System.out.println("Patching German data!");
            tp.FileFolderName  = "files";
			tp.patchMe(new TalkPatchGerman().data(), new NpcNamePatchGerman().data());

            //
            // english patch
            System.out.println("Patching English data!");
			tp.FileFolderName  = "files_en";
			tp.patchMe(new TalkPatchEnglish().data(), new NpcNamePatchEnglish().data());

            System.out.println("**** DONE! Goodbye! ****");

		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
}
