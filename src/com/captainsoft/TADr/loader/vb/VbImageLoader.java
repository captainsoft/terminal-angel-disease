/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.loader.vb;

import com.captainsoft.TADr.TadLang;
import com.captainsoft.TADr.engine.debug.Debugger;
import com.captainsoft.TADr.engine.excp.GameDataIoException;
import com.captainsoft.TADr.engine.excp.GameStateException;
import com.captainsoft.TADr.loader.ImageLoader;
import com.captainsoft.TADr.loader.vbFile.VbFile;
import com.captainsoft.spark.files.FileUtils;
import com.captainsoft.spark.image.ImageTool;
import com.captainsoft.spark.ui.drawing.Surface;
import com.captainsoft.spark.utils.Log;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Image loader for the old VB file format.
 *
 * @author mathias fringes
 */
public final class VbImageLoader implements ImageLoader {
   
    // fields
    
    private final Map<String, String> filenames;
	private final String pathPrefix;        
    
    // constructors
    
    public VbImageLoader() {  
    	filenames = new HashMap<String, String>();
    	pathPrefix = TadLang.folder();
        filenames.put("tile", "res/tiles_p.vrs");
        filenames.put("ifc",  "res/iface_p.vrs");
        filenames.put("npc",  "res/npcs_p.vrs");
        filenames.put("fscr", "res/fgtscr_p.vrs");
        filenames.put("meff", "res/moneff.vrs");
        filenames.put("mimg", "res/monimg.vrs");
        filenames.put("intr", "res/intr_p.vrs");        
    }         
    
    // ImageLoader

    public Surface load(String type, int index) {
        return load(type, index, 0, 0);
    }    

    public Surface load(String type, int index, int width, int height) {
        Log.log("Loading image [" + type + "/" + index + "]");

        if (Debugger.Inst.loadTilesDirect) {
	    	if (type.equals("tile")) {
	    		return loadDirect(index);
	    	}
    	}
    	
        String filename = filenames.get(type); 
        if (filename == null) {
        	throw new IllegalArgumentException("Filedatabase not found with: " + type);
        }
        filename = pathPrefix + filename; 
        //
        byte[] imgData = null;
        VbFile file = null;
        try {
            file = new VbFile(filename, VbFile.R);
            file.seekPosition((index * 8) - 1);
            //
            int filePos = file.readInt(); 
            int fileLen = file.readInt();          
            //            
            file.seekPosition(filePos - 1);
            imgData = file.readBytes(fileLen);
        } catch (Exception e) {
        	throw new GameDataIoException("Cannot load image data for " + type + " " + index, e);
        } finally {        	        	
        	FileUtils.close(file);			
        }
        //
        Image img;
        try {
        	img = ImageTool.createImage(imgData, width, height);        	
        } catch (Exception e) {
        	throw new GameStateException("Cannot creating image " + type + " " + index, e);
        }
        //
        boolean transparent = type.equals("meff") || type.equals("mimg");
        return new Surface(img, transparent);
    }	
    
    // private
    
    private Surface loadDirect(int index) {
        File file;
        String sfx = TadLang.isEnglish() ? "_en" : "";
        //
        String basePath = "../_working/src_files/files_source";
        //
        if (index == 25) {
            file = new File(basePath + sfx + "/items_high_noborder.png");
        } else if (index == 21) {
            file = new File(basePath + sfx + "/java_fake_plastic_trees.png");
        } else if (index == 22) {
            file = new File(basePath + sfx + "/party.png");
        } else {
            file = new File(basePath + sfx + "/tiles" + index + ".png");
        }
    	 try {
			return Surface.fromImage(ImageIO.read(file));
		} catch (IOException e) {
			throw new RuntimeException("Cannot load: " + file, e);
	    }
    }      
          
}
