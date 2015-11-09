/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.painting;

import java.awt.Point;
import java.util.List;

import com.captainsoft.TADn.loader.ItemRepository;
import com.captainsoft.TADn.model.Direction;
import com.captainsoft.TADn.model.Position;
import com.captainsoft.TADn.painting.replacer.TileGroupImage;
import com.captainsoft.TADn.painting.replacer.MapTileReplacer;
import com.captainsoft.TADn.party.Item;
import com.captainsoft.TADn.party.LevelMap;
import com.captainsoft.TADn.party.Tile;
import com.captainsoft.TADn.party.TileDanger;
import com.captainsoft.spark.ui.drawing.ImageSurface;
import com.captainsoft.spark.ui.drawing.Surface;

/**
 * Paints tiles from the map data. Definitively not thread-safe!
 * 
 * @author mathias fringes
 */
final class TilePainter {

	// TODO check if replacer can be null and what happens then...
	
	// fields
	
	private static final Point nPoint = new Point(0, 0);
	
	private final ImageSurface tileImage;
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
		this.itemRepo = itemRepo;
		this.paintingInfo = paintingInfo;
		//
		this.tileSet = tileSet;
		tileImage = new ImageSurface(40, 40);
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

	public Surface createTileImage(Position position, Position partyPosition, Point offset) {
		if (position.equals(partyPosition)) {
			return createPartyBottomImage(position, offset);
		} else if (position.equals(partyPosition.addY(-1))) {
			return createPartyHeadImage(position, offset);
		} else {
			return createTileImage(position);
		}
	}

	public Surface createPartyBottomImage(Position position, Point offset) {
		//
		Tile tile = tile(position);
		drawBasicBackground(position, tile);
		//
		// paint party bottom
		if (offset == null) {
			offset = nPoint;
		}
		draw(tileSet.getPartyImage(getPartyMemberPic(), getFaceDirection(), 0), offset.x, offset.y);
		//
		// second overlay (always over party member)
		drawSecondOverlay(tile);	
		//
		postProcess(position);
		return tileImage;
	}

	public Surface createPartyHeadImage(Position position, Point offset) {
		return createPartyHeadImage(position, offset, false);
	}

	public Surface createPartyHeadImage(Position position, Point offset, boolean moving) {
		//
		if (offset == null) {
			offset = nPoint;
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

	//
	// private methods
		
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
			if (didAlreadyDraw == false) {
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
		Tile tile = tile(position);
		switch (tile.danger()) {
			case High:
				// tileImage.color(Color.RED);
				// g2.draw(new Ellipse2D.Double(1, 1, 39, 39));
				break;
			case Low:			
			case None:
			default:
				break;
		}			
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
		tileImage.blit(image);
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