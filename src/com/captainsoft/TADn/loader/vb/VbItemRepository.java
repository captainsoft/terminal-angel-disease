/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.loader.vb;

import java.util.ArrayList;

import com.captainsoft.TADn.TadLang;
import com.captainsoft.TADn.engine.excp.GameDataIoException;
import com.captainsoft.TADn.loader.ImageLoader;
import com.captainsoft.TADn.loader.ItemRepository;
import com.captainsoft.TADn.loader.vbFile.VbFile;
import com.captainsoft.TADn.party.Item;
import com.captainsoft.TADn.party.ItemInstance;
import com.captainsoft.spark.files.FileUtils;
import com.captainsoft.spark.image.ImageTool;
import com.captainsoft.spark.ui.drawing.ImageSurface;
import com.captainsoft.spark.ui.drawing.Surface;
import com.captainsoft.spark.utils.Log;
import com.captainsoft.spark.utils.TransFilter;

/**
 * Stored Item repository. Saves and loads and creates Item instances.
 * 
 * @author mathias fringes
 */
public class VbItemRepository implements ItemRepository {

	/**
	 * items haben indexe 1-250, 0 ist unbenutzt!!
	 */
	public static final int MAX = 251;
	
	private final String fileLocation = "dat/items.dat";	
	private final ArrayList<Item> items;
	private final ItemImages images;
	
	// constructors
	
	public VbItemRepository(ImageLoader imageLoader) {
		super();
		if (imageLoader != null) {
			this.images = new ItemImages(imageLoader);
		} else {
			this.images = null;
		}
		this.items = new ArrayList<Item>(MAX);
		this.items.add(null); // add one fake thingy		
	}
	
	// public
	
	public void load() {
		VbFile file = null;
		try {
            file = new VbFile(TadLang.file(fileLocation), VbFile.R);
            for (int i = 1; i < MAX; i++) {                
                //
                // create the item
                Item item = new Item();
                item.id(i);
                item.name(file.readString(30));
                item.typeId(file.readByte());               
                item.value(file.readByte());
                item.coins(file.readShort());
                item.weight(file.readByte());
                item.suit(file.readByte());
                items.add(item); 
                Log.debug(item);
            }          
        } catch (Exception e) {
        	throw new GameDataIoException("Error loading item repository", e);            
        } finally { 
        	FileUtils.close(file);
        }
		items.trimToSize();
	}
	
	public void save(Item item) {
		VbFile file = null;
		try {
            file = new VbFile(TadLang.file(fileLocation), VbFile.RW);
            file.setRecordSize(36);
            file.setChunkSize(1);
            file.seekRecord(1, item.id());
            //
            file.writeString(item.name(), 30);
            file.writeByte(item.typeId());
            file.writeByte(item.value());
            file.writeShort(item.coins());
            file.writeByte(item.weight());
            file.writeByte(item.suit());                                                    
        } catch (Exception e) {
        	throw new GameDataIoException("Error saving item", e);            
        } finally { 
        	FileUtils.close(file);
        }		
	}
	
	// ItemRepository

	@Override
	public Surface getImage(int index) {
		return this.images.getItemImage(index);
	}

	@Override
	public Surface getImage(Item item) {
		return this.images.getItemImage(item.id());
	}
	
	@Override
	public Surface getCursorImage(Item item) {
	    return images.getItemImage(item.id());
	}
	
	@Override
	public Item item(int index) {
		if (index == 0) {
			return null;
		}
		return items.get(index);		
	}
	
	@Override
	public Item item(ItemInstance itemInstance) { 
		return item(itemInstance.id());
	}	
	
	// nested classes
	
	final private class ItemImages {
					
		private Surface images[];		
							
		public ItemImages(ImageLoader imageLoader) {
			super();
			ImageSurface bimg = new ImageSurface(ImageTool.createFiltered(
					imageLoader.load("tile", 25).image(), TransFilter.BLACK), true);
																			
			images = new Surface[MAX];
		
			int itemIdx = 1;
			for (int y = 0; y < 9; y++) {
				for (int x = 0; x < 30; x++) {			
									
					Surface simg = bimg.stamp(x*21+1, y*21+1, 20, 20);
					images[itemIdx] = new ImageSurface(simg, true);
					
					itemIdx++;
					if (itemIdx >= MAX) {
						break;
					}									
					
				}
			}
		}		
					
		public Surface getItemImage(int nr) {
			return images[nr];	
		}
							
	}
		
}