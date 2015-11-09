/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.loader.vb;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.captainsoft.TADn.Debugger;
import com.captainsoft.TADn.TadLang;
import com.captainsoft.TADn.engine.excp.GameDataIoException;
import com.captainsoft.TADn.engine.excp.GameStateException;
import com.captainsoft.TADn.loader.ImageLoader;
import com.captainsoft.TADn.loader.vbFile.VbFile;
import com.captainsoft.spark.files.FileUtils;
import com.captainsoft.spark.image.ImageTool;
import com.captainsoft.spark.ui.drawing.ImageSurface;
import com.captainsoft.spark.ui.drawing.Surface;

/**
 *
 * @author mathias fringes
 */
public final class VbImageLoader implements ImageLoader {
   
    // fields
    
    private final Map<String, String> filenames;
    
	private String pathPrefix = "";        
    
    // constructors
    
    public VbImageLoader() {  
    	filenames = new HashMap<String, String>();
    	String folder = TadLang.folder(); // TODO use path prefix instead!
        filenames.put("tile", folder + "res/tiles_p.vrs");
        filenames.put("ifc", folder + "res/iface_p.vrs");
        filenames.put("npc", folder + "res/npcs_p.vrs");
        filenames.put("fscr", folder + "res/fgtscr_p.vrs");
        filenames.put("meff", folder + "res/moneff.vrs");
        filenames.put("mimg", folder + "res/monimg.vrs");
        filenames.put("intr", folder + "res/intr_p.vrs");        
    }     
    
    // accessors
    
    public void setPathPrefix(String pathPrefix) {
    	this.pathPrefix = pathPrefix;
    }
    
    // ImageLoader
    
    @Override
    public Surface load(String type, int index) {
        return load(type, index, 0, 0);
    }    

    @Override
    public Surface load(String type, int index, int width, int height) {  
    	if (Debugger.inst.loadTilesDirect) {
	    	if (type.equals("tile") && index < 22) {
	    		return loadDirect(type, index, width, height);
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
            file.seekPosition((index * 8)-1);
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
        Image img = null;
        try {
        	img = ImageTool.createImage(imgData, width, height);        	
        } catch (Exception e) {
        	throw new GameStateException("Cannot creating image" + imgData, e);
        }
        //
        boolean transparent = type.equals("meff") || type.equals("mimg");
        return new ImageSurface(img, transparent);
    }	
    
    // private
    
    private Surface loadDirect(String type, int index, int width, int height) {
    	 try {
			return ImageSurface.fromImage(ImageIO.read(new File("files_source/tiles" + index + ".png")));
		} catch (IOException e) {
			throw new RuntimeException(e);
	    }
    }      
          
}
