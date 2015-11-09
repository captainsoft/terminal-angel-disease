/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.painting;

import java.util.List;

import com.captainsoft.TADr.loader.ItemRepository;
import com.captainsoft.TADr.model.Direction;
import com.captainsoft.TADr.model.Position;
import com.captainsoft.TADr.model.item.Item;
import com.captainsoft.TADr.model.map.LevelMap;
import com.captainsoft.TADr.model.map.Tile;
import com.captainsoft.TADr.model.map.TileDanger;
import com.captainsoft.TADr.painting.replacer.MapTileReplacer;
import com.captainsoft.TADr.painting.replacer.TileGroupImage;
import com.captainsoft.spark.ui.CPos;
import com.captainsoft.spark.ui.drawing.Surface;

/**
 * Paints tiles from the map data. Definitively not thread-safe!
 * 
 * @author mathias fringes
 */
public class TilePainter {
	
	// fields

    private static final CPos zeroPoint = new CPos(0, 0);

    private final Surface tileImage;
	private final ItemRepository itemRepo;
	private final PaintingInfo paintingInfo;
	private final TileSet tileSet;

	private int secOverlayStart = 0;
	private LevelMap levelMap;
	private MapTileReplacer replacer;

	// constructors

	/**
	 * Constructor.
	 * 
	 * @param itemRepo
	 */
	public TilePainter(TileSet tileSet, ItemRepository itemRepo, PaintingInfo paintingInfo) {
		super();
		this.tileSet = tileSet;
		this.itemRepo = itemRepo;
		this.paintingInfo = paintingInfo;
		//
		tileImage = new Surface(40, 40);			
	}

	// accessors

	public void setMap(LevelMap levelMap) {
		this.levelMap = levelMap;		
		secOverlayStart = levelMap.getSecondOverlayStart();
	}

	public void setReplacer(MapTileReplacer replacer) {
		this.replacer = replacer;
	}

	// public

	/**
	 * Creates the complete image for a tile, not taking the party into account.
	 * 
	 * @param position
	 */
	public Surface createTileImage(Position position) {
		//
		Tile tile = tile(position);
		drawBasicBackground(position, tile);
		//
		drawSecondOverlay(tile);	
		//
		postProcess(position);
		//
		return tileImage;
	}
		
	public Surface createTileImage(Position position, Position partyPosition) {
		return createTileImage(position, partyPosition, null);
	}

	public Surface createTileImage(Position position, Position partyPosition, CPos offset) {
		if (position.equals(partyPosition)) {
			return createPartyBottomImage(position, offset);
		} else if (position.equals(partyPosition.addY(-1))) {
			return createPartyHeadTile(position, offset);
		} else {
			return createTileImage(position);
		}
	}

	public final Surface createPartyBottomImage(Position position, CPos offset) {
		//
		Tile tile = tile(position);
		drawBasicBackground(position, tile);
		//
		// paint party bottom
		if (offset == null) {
			offset = zeroPoint;
		}
		draw(tileSet.getPartyImage(getPartyMemberPic(), getFaceDirection(), 0), offset.x, offset.y);
		//
		// second overlay (always over party member)
		drawSecondOverlay(tile);	
		//
		postProcess(position);
		return tileImage;
	}

	public final Surface createPartyHeadTile(Position position, CPos offset) {
		return createPartyHeadTile(position, offset, false);
	}

	public final Surface createPartyHeadTile(Position position, CPos offset, boolean moving) {
		//
		if (offset == null) {
			offset = zeroPoint;
		}
		Tile tile = tile(position);
		drawBasicBackground(position, tile);
		//	
		// draw face behind sec overlay if it is a double sec overlay (doors, large trees)
		if (hasSecondOverlayLevel(position, moving)) {
			draw(tileSet.getPartyImage(getPartyMemberPic(), getFaceDirection(), 1), offset.x, offset.y);
			if (offset.y < 0) {
				draw(tileSet.getPartyImage(getPartyMemberPic(), getFaceDirection(), 0), offset.x, 40 - (offset.y * -1));
			}
			drawSecondOverlay(tile);
		} else {
			drawSecondOverlay(tile);			
			draw(tileSet.getPartyImage(getPartyMemberPic(), getFaceDirection(), 1), offset.x, offset.y);
			if (offset.y < 0) {
				draw(tileSet.getPartyImage(getPartyMemberPic(), getFaceDirection(), 0), offset.x, 40 - (offset.y * -1));
			}
		}
		//
		this.postProcess(position);
		return tileImage;
	}

	// private
		
	/**
	 * Draws everything from ground -> overlay -> item. 
	 * 
	 * @param p
	 * @param tile
	 */
	private void drawBasicBackground(Position p, Tile tile) {		
		//
		// ground
		drawGround(p, tile);		
		//
		// overlay, not 2nd
		int overlay = determineOverlay(tile);
		if (isOverlay(overlay)) {		
			draw(overlay);
		}
		postProcessOverlay(p);
		//
		// item
		Item item = itemRepo.item(tile.item());
		if (item != null) {
			if (overlay > 0 && overlay < secOverlayStart) {
				draw(itemRepo.getImage(item), 10, 8);
			} else {
				draw(itemRepo.getImage(item), 10, 10);
			}
		}
	}

	/**
	 * Draws only the ground for the tile.
	 * 
	 * @param p
	 * @param tile
	 */
	private void drawGround(Position p, Tile tile) {
		int ground = tile.ground();
		if (shouldDraw(ground)) {
			//
			draw(ground);
		} else {
			List<TileGroupImage> reps = replacer.getReplaceImages(p);
			boolean didAlreadyDraw = false;
			for (TileGroupImage r : reps) {
				if (r.ground()) {
					draw(r.image());
					didAlreadyDraw = true;
					break;
				}
			}
			if (!didAlreadyDraw) {
				// g2.fillRect(0, 0, 40, 40);
				// draw the tile anyway if no replacement was found!
				draw(ground);
			}
		}
		postProcessGround(p);
	}
	
	private void drawSecondOverlay(Tile tile) {
		int overlay = determineOverlay(tile);
		if (isSecondOverlay(overlay)) {
			draw(overlay);			
		}		
	}
		
	private void postProcessGround(Position position) {
		//
		// danger
		/*
		Tile tile = tile(position);
		switch (tile.danger()) {
			case High:
				tileImage.color(Color.RED);
				g2.draw(new Ellipse2D.Double(1, 1, 39, 39));
				break;
			case Low:			
			case None:
			default:
				break;
		}*/	
	}	
	
	private void postProcessOverlay(Position position) {		
		// 		
		for (TileGroupImage r : replacer.getReplaceImages(position)) {			
			if (r.overlay()) {
				draw(r.image());
			}
		}				
	}

	private void postProcess(Position position) {		
		//
		for (TileGroupImage r : replacer.getReplaceImages(position)) {		
			if (r.secondOverlay()) {				
				draw(r.image());
			}
		}
	}
	
	private void draw(int imageId) {
		draw(tileSet.getTileImage(imageId));
	}
	
	private void draw(Surface image) {
		if (image != null) {
			tileImage.blit(image);
		}
	}
	
	private void draw(Surface image, int x, int y) {
		tileImage.blit(image, x, y);
	}

	private boolean shouldDraw(int ground) {
		return (replacer == null) || (replacer.shouldDraw(ground));
	}
	
	private boolean shouldDrawOverlay(int overlay) {
		return ((replacer == null && overlay != 0) 
				|| ((replacer != null) && (replacer.shouldDraw(overlay))));
	}

	private int determineOverlay(Tile tile) {
		int overlay = tile.overlay();		
		return (shouldDrawOverlay(overlay) ? tile.overlay() : 0);
	}
	
	private boolean isOverlay(int overlay) {
		return (overlay > 0 && overlay < secOverlayStart);
	}
	
	private boolean isSecondOverlay(int overlay) {
		return (overlay >= secOverlayStart);
	}
	
	private Direction getFaceDirection() {
		return paintingInfo.getFaceDirection();
	}

	private int getPartyMemberPic() {
		return paintingInfo.getPartyMemberPic();
	}
	
	private boolean hasSecondOverlayLevel(Position position, boolean moving) {
		return ((levelMap.getSecondOverlay(position.addY(1)) > 0)
					|| (moving && levelMap.getSecondOverlay(position.addY(-1)) > 0))	
			|| (tile(position.addY(1)).danger() == TileDanger.High)
			|| (tile(position.addY(2)).danger() == TileDanger.High);	
	}	

	private Tile tile(Position p) {
		return levelMap.tile(p);
	}	

}