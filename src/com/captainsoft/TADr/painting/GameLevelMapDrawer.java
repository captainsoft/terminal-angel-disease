package com.captainsoft.TADr.painting;

import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.TADr.loader.ItemRepository;
import com.captainsoft.TADr.model.Position;
import com.captainsoft.TADr.model.map.LevelMap;
import com.captainsoft.TADr.model.map.MapTileView;
import com.captainsoft.TADr.painting.animations.MapAnimationList;
import com.captainsoft.TADr.painting.animations.VorhangAnimation;
import com.captainsoft.TADr.painting.replacer.MapTileReplacer;
import com.captainsoft.spark.render.Animation;
import com.captainsoft.spark.ui.CPos;
import com.captainsoft.spark.ui.Updater;
import com.captainsoft.spark.ui.drawing.Surface;
import com.captainsoft.spark.utils.Log;

import java.util.Set;

/**
 * Simple map drawer for use in the real game.
 * 
 * @author mathias fringes
 */
public final class GameLevelMapDrawer {

	// fields	

	public static final int Y_SPAN = 11;
	public static final int X_SPAN = 17;
	public static final int SQUARE = 40;
	public static final int PX_WIDTH = SQUARE * X_SPAN;
	public static final int PX_HEIGHT = SQUARE * Y_SPAN;	
	public static final int X_SH = (X_SPAN - 1) / 2;
	public static final int Y_SH = (Y_SPAN - 1) / 2;

	public final PaintingInfo paintingInfo;
	public final MapAnimationList mapAnimations;
	public final MapTileView tileView;
	public final MapTileReplacer replacer;
	public final TilePainter tilePainter;
	
	public CPos mapOffset = new CPos(0, 0);
	public CPos partyOffset;
	public Position partyPosition;		
	
	private final Surface backSurface;
	private final Surface surface;	
	private final TileSet tileSet;

	// constructors

	public GameLevelMapDrawer(ItemRepository itemRepo) {
		super();
		surface = new Surface(X_SPAN * SQUARE, Y_SPAN * SQUARE);
		backSurface = new Surface(X_SPAN * SQUARE, Y_SPAN * SQUARE);	
		//
		paintingInfo = new PaintingInfo();
		tileSet = new TileSet(TadRepo.inst().ImageLoader());
		tilePainter = new TilePainter(tileSet, itemRepo, paintingInfo);
		tileView = new MapTileView(X_SPAN, Y_SPAN);		
		//
		replacer = new MapTileReplacer(tileSet, TadRepo.inst().ImageLoader());
		tilePainter.setReplacer(replacer);
		//
		mapAnimations = new MapAnimationList(X_SPAN, Y_SPAN);			
	}

	// accessors	

	public void removeDanger(Position position) {
		replacer.deleteDanger(position);
		mapAnimations.removeSmoke(position);
		mapAnimations.setPosition(tileView.topLeft);
	}
	
	public void setLevelMap(LevelMap levelMap) {		
		partyPosition = null;
		tileSet.load(levelMap.tileset());
		tilePainter.setMap(levelMap);
		replacer.setup(levelMap, mapAnimations);		
	}
	
	public Set<Animation> getCurrentMapAnimation(){	
		return mapAnimations.visible();
	}

	public Surface surface() {		
		return surface;
	}

	public Position getPosAt(int x, int y) {	
		return (new Position(x / SQUARE, y / SQUARE).add(tileView.topLeft));
	}

	public Surface backSurface() {
		return backSurface;
	}
	
	public MapTileView tileView() {
		return tileView;
	}

	// public
	
	public void centerPartyPosition(Position partyPosition) {
		this.partyPosition = partyPosition;
		if (partyPosition != null) {
			setTopLeft(partyPosition.add(-X_SH, -Y_SH));
		}
	}	
	
	public void setTopLeft(Position topLeft) {		
		tileView.topLeft = topLeft;
		mapAnimations.setPosition(tileView.topLeft);
	}
	
	public void flip() {	
		surface.blit(backSurface);		
	}

	public Animation vorhangZu(Updater updater) {
		return new VorhangAnimation(updater, backSurface, this);						
	}

	public void setTo(Position newPosition) {								
		centerPartyPosition(newPosition);
		drawFullMap();					
	}
	
	public void retileParty() {
		retile(partyPosition, partyPosition.addY(-1));		
	}
	
	public void removeParty() {		
		Position oldPosition = partyPosition;
		partyPosition = null;
		retile(oldPosition.addY(-1), oldPosition);			
	}

	public boolean retile(Position ... positions) {
		boolean didUpdate = false;
		//
		for (Position position : positions) {
			if (position == null) {
				continue;
			}
			//
            if (tileView.isVisible(position)) {
                Log.trace("retile: " + position);
                Surface image;
                if (partyPosition == null) {
                    image = tilePainter.createTileImage(position);
                } else {
                    image = tilePainter.createTileImage(position, partyPosition, partyOffset);
                }
                blitAtNoFlip(image, position);
                didUpdate = true;
            }
		}
		if (didUpdate) {
			flip();
		}
		return didUpdate;
	}	


	public void setFaceDirection(Position p) {
		if (partyPosition != null) {
			paintingInfo.setFaceDirection(partyPosition.findDir(p));
		}
	}
	
	public void blitAtNoFlip(Surface image, Position position) {
		Position po2 = position.subst(tileView.topLeft);
		backSurface.blit(image, po2.x * SQUARE + mapOffset.x, po2.y * SQUARE + mapOffset.y);		
	}
	
	// 	private

	private void drawFullMap() {
		Log.info("Full map drawn with party at " + partyPosition);
		for (int y = 0; y < Y_SPAN; y++) {
			for (int x = 0; x < X_SPAN; x++) {
				Position pos = new Position(x - X_SH, y - Y_SH).add(partyPosition);
                Surface image = tilePainter.createTileImage(pos, partyPosition);
                backSurface.blit(image, x * SQUARE + mapOffset.x, y * SQUARE + mapOffset.y);
			}
		}
		flip();
	}

}

