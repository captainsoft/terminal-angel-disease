/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.painting;

import java.awt.Color;
import java.awt.Point;

import com.captainsoft.TADn.TadRepo;
import com.captainsoft.TADn.engine.GameEngineTasks;
import com.captainsoft.TADn.loader.ItemRepository;
import com.captainsoft.TADn.model.Direction;
import com.captainsoft.TADn.model.Position;
import com.captainsoft.TADn.painting.animations.AnimationList;
import com.captainsoft.TADn.painting.replacer.MapTileReplacer;
import com.captainsoft.TADn.party.LevelMap;
import com.captainsoft.spark.CommandThread;
import com.captainsoft.spark.ui.Updater;
import com.captainsoft.spark.ui.drawing.ImageSurface;
import com.captainsoft.spark.ui.drawing.Surface;
import com.captainsoft.spark.utils.Log;
import com.captainsoft.spark.utils.Sleeper;

/**
 * A simple, but scrolling drawer for maps.
 * 
 * @author mathias fringes
 */
public final class SimpleMapDrawer {
	
	// fields	
	
	public final static int scroll_steps = 4;
	
	public final int Y_S = 11;
	public final int X_S = 17;
	public final int SQUARE = 40;
	public final int PX_WIDTH = SQUARE * X_S;
	public final int PX_HEIGHT = SQUARE * Y_S;
	
	public Point offset = null;
	
	private final int X_SH = (X_S - 1) / 2;
	private final int Y_SH = (Y_S - 1) / 2;
	private final AnimationList animations;
	private final PaintingInfo paintingInfo; 
	private final Surface backSurface;
	private final Surface surface;	
	private final Surface xSurface;
	private final Surface ySurface;
	private final TilePainter tilePainter;
	private final MapTileReplacer replacer;
	private final TileSet tileSet;
		
	private CommandThread gameUpdateThread;	
	private Position partyPosition;	
	private Position topLeftPos;
	
	private boolean scrolling = false;
	
	// constructors
	
	public SimpleMapDrawer(ItemRepository itemRepo) {
		super();
		this.surface = new ImageSurface(X_S * SQUARE, Y_S * SQUARE);
		this.backSurface = new ImageSurface(X_S * SQUARE, Y_S * SQUARE);		
		this.xSurface = new ImageSurface(X_S * SQUARE, SQUARE);
		this.ySurface = new ImageSurface(SQUARE, Y_S * SQUARE);
		//
		this.paintingInfo = new PaintingInfo();
		this.tileSet = new TileSet(TadRepo.inst().ImageLoader());
		this.tilePainter = new TilePainter(tileSet, itemRepo, paintingInfo);		
		this.topLeftPos = new Position(1, 1);
		//
		this.replacer = new MapTileReplacer(tileSet);
		this.tilePainter.setReplacer(this.replacer);
		//
		this.animations = new AnimationList(X_S, Y_S);		
		gameUpdateThread = new CommandThread(150, new GameEngineTasks(this));
		gameUpdateThread.start();		
	}
	
	// accessors
	
	public synchronized boolean isScrolling() {
		return scrolling;
	}

	public synchronized void setScrolling(boolean scrolling) {
		this.scrolling = scrolling;
	}
	
	public void setLevelMap(LevelMap levelMap) {		
		this.partyPosition = null;
		tileSet.load(levelMap.getTilesetNr());
		tilePainter.setMap(levelMap);
		replacer.LevelMap(levelMap, animations);		
	}
	
	public void centerPartyPosition(Position partyPosition) {
		this.partyPosition = partyPosition;
		if (partyPosition != null) {
			topLeftPos = partyPosition.add(-X_SH, -Y_SH);
		}
		animations.setPosition(topLeftPos);
	}
	
	public void play() {
		if (!isScrolling()) {
			synchronized(this) {
				animations.play();
			}
		}
	}

	public Surface surface() {		
		return backSurface;
	}
	
	public Position getPosAt(int x, int y) {		
		return (new Position(x / SQUARE, y / SQUARE).add(topLeftPos));
    }
	
	// public
	
	public synchronized void vorhangZu(Updater updater) {
		int width = surface.width();
		int height = surface.height();
		Sleeper sl = new Sleeper(0);
		for (int i = 1; i < (width - 200) / 2; i += 10) {
			sl.sleepAndReset();			
			sl.time(25);
			surface().fill(Color.BLACK, 0, 0, i + 89, height);			
			surface().fill(Color.BLACK, width - i - 89, 0, width, height);
			//
			surface().fill(Color.BLACK, 0, 0, width, i - 10);
			surface().fill(Color.BLACK, 0, height - i - 10, width, height);
			updater.update();						
		}						
	}
	
	public synchronized void moveTo(Position newPosition) {
		if (partyPosition == null) {							
			centerPartyPosition(newPosition);
			drawFullMap();
			return;
		}
		if (partyPosition.equals(newPosition)) {
			return;
		} else if (partyPosition.isNextTo(newPosition)) {
			drawOneTileMove(partyPosition.findDir(newPosition), newPosition);
			Position oldPosition = partyPosition;
			centerPartyPosition(newPosition);
			// 
			// retile party
			retile(oldPosition);
			retile(oldPosition.addY(-1));
			retile(newPosition);
			retile(newPosition.addY(-1));			
		} else {
			drawFullMap();
			centerPartyPosition(newPosition);
		}				
	}
	
	public synchronized void moveNoParty(Position newPosition, int step) {
		drawOneTileMove(partyPosition.findDir(newPosition), newPosition, step);	
		topLeftPos = newPosition.add(-X_SH, -Y_SH);
	}
	
	public synchronized void retileParty() {
		retile(partyPosition.addY(-1));
		retile(partyPosition);
	}
	
	synchronized void scrollParty(Direction d, int step) {
		//
		if (step < 1  || step > scroll_steps) {
			throw new IllegalArgumentException("Wrong step number: " + step);
		}
		//
		if (d == Direction.West) {
			int y = (step % 2) == 0 ? 0 : -2;
			
			Point hoffset = new Point((40 - (step +1) * 8), y);			
			Position p1 = partyPosition.add(-1, -1);
			Surface image = tilePainter.createPartyHeadImage(p1, hoffset);
			blitAt(image, p1);
			
			Position p2 = partyPosition.add(-1, 0);
			image = tilePainter.createPartyBottomImage(p2, hoffset);
			blitAt(image, p2);			
						
			offset = new Point(((step+1) * 8) * -1, y);
			retile(partyPosition.addY(-1));
			retile(partyPosition);
		}			
		else if (d == Direction.East) {
			int y = (step % 2) == 0 ? 0 : -2;
			
			Point hoffset = new Point(((step +1) * 8) - 40, y);			
			Position p1 = partyPosition.add(1, -1);
			Surface image = tilePainter.createPartyHeadImage(p1, hoffset);
			blitAt(image, p1);
			
			Position p2 = partyPosition.add(1, 0);
			image = tilePainter.createPartyBottomImage(p2, hoffset);
			blitAt(image, p2);
			
			offset = new Point(((step+1) * 8), y);	
			retile(partyPosition.addY(-1));
			retile(partyPosition);
		}
		else if (d == Direction.North) {
			int x = (step % 2) == 0 ? 0 : 2;
			Position p1 = partyPosition.add(0, -1);
			Position p2 = partyPosition.add(0, -2);
						
			Surface image;
			
			Point hoffset = new Point(x, -((step) * 8) + 40);
			image = tilePainter.createPartyHeadImage(p2, hoffset);
			blitAt(image, p2);
			
			Point moffset = new Point(x, -((step) * 8));
			image = tilePainter.createPartyHeadImage(p1, moffset, true);
			blitAt(image, p1);
						
			image = tilePainter.createPartyBottomImage(partyPosition, moffset);
			blitAt(image, partyPosition);
						
		} 
		else if (d == Direction.South) {	
			
			int x = (step % 2) == 0 ? 0 : -2;
			Position p1 = partyPosition.add(0, -1);
			Position p2 = partyPosition.add(0,  1);
						
			Surface image;
			
			Point hoffset = new Point(x, ((step) * 8));
			image = tilePainter.createPartyHeadImage(p1, hoffset);
			blitAt(image, p1);
			
			Point moffset = new Point(x, -40+((step) * 8));
			image = tilePainter.createPartyBottomImage(p2, moffset);
			blitAt(image, p2);
						
			image = tilePainter.createPartyHeadImage(partyPosition, moffset, true);
			blitAt(image, partyPosition);
			
		}		
	}
	
	public synchronized void removeParty() {		
		Position oldPosition = partyPosition;
		partyPosition = null;
		retile(oldPosition.addY(-1));
		retile(oldPosition);		
	}
		
	public synchronized boolean retile(Position position) {
		if (isVisible(position)) {
			Log.trace("retile: " + position);
			Surface image = null;
			if (partyPosition == null) {
				image = tilePainter.createTileImage(position);
			} else {
				image = tilePainter.createTileImage(position, partyPosition, offset);
			}
			blitAt(image, position);
			return true;
		} else {
			return false;
		}
	}	
		
	public synchronized void setFaceDirection(Direction d) {
		paintingInfo.setFaceDirection(d);		
	}
	
	public synchronized Direction getFaceDirection() {
		return paintingInfo.getFaceDirection();		
	}

	public synchronized void setPartyMemberPic(int nr) {
		paintingInfo.setPartyMemberPic(nr);		
	}		
		
	// 	private methods
	
	private void blitAt(Surface image, Position position) {
		Position po2 = position.subst(topLeftPos);
		surface().blit(image, po2.x * SQUARE, po2.y * SQUARE);
	}
	
	private void drawFullMap() {
		Log.info("Full map drawn with party at " + partyPosition);
		for (int y = 0; y < Y_S; y++) {
			for (int x = 0; x < X_S; x++) {
				Position pos = new Position(x - X_SH, y - Y_SH).add(partyPosition);									
				Surface image = tilePainter.createTileImage(pos, partyPosition);				
				surface.blit(image, x * SQUARE, y * SQUARE);				
			}
		}
		flip();
	}
		
	private void drawOneTileMove(Direction dir, Position newPosition) {
		drawOneTileMove(dir, newPosition, 99);
	}
	
	private void drawOneTileMove(Direction dir, Position newPosition, int step) {
		switch (dir) {
			case East:
				if (step == 0 || step == 99) {
					for (int y = 0; y < Y_S; y++) {				
						Position pos = newPosition.add(X_SH, y-Y_SH);	
						Surface image = forPos(pos);
						ySurface.blit(image, 0, y * SQUARE);
					}
				}
				int xv = SQUARE * (X_S-1);
				if (step == 99) {
					surface.blit(backSurface, -SQUARE, 0);
					surface.blit(ySurface, xv, 0);
				} else {
					surface.blit(backSurface, -10, 0);
					surface.blit(ySurface, xv + 30 - (step * 10), 0);	
				}				
				break;
			case West:
				if (step == 0 || step == 99) {
					for (int y = 0; y < Y_S; y++) {					
						Position pos = newPosition.add(-X_SH, y-Y_SH);
						Surface image = forPos(pos);
						ySurface.blit(image, 0, y * SQUARE);
					}
				}
				if (step == 99) {
					surface.blit(backSurface, SQUARE, 0);
					surface.blit(ySurface, 0, 0);
				} else {
					surface.blit(backSurface, 10, 0);
					surface.blit(ySurface, -30 + (step * 10), 0);
				}
				break;
			case North:
				if (step == 0 || step == 99) {
					for (int x = 0; x < X_S; x++) {					
						Position pos = newPosition.add(x-X_SH, -Y_SH);
						Surface image = forPos(pos);
						xSurface.blit(image, x * SQUARE, 0);
					}
				}
				if (step == 99) {
					surface.blit(backSurface, 0, SQUARE);
					surface.blit(xSurface, 0, 0);
				} else {
					surface.blit(backSurface, 0, 10);
					surface.blit(xSurface, 0, (step * 10) -30);
				}
				break;
			case South:
				if (step == 0 || step == 99) {
					for (int x = 0; x < X_S; x++) {					
						Position pos = newPosition.add(x-X_SH, Y_SH);
						Surface image = forPos(pos);
						xSurface.blit(image, x * SQUARE, 0);
					}
				}
				int yv = SQUARE * (Y_S-1);
				if (step == 99) {
					surface.blit(backSurface, 0, -SQUARE);
					surface.blit(xSurface, 0, yv);
				} else {
					surface.blit(backSurface, 0, -10);
					surface.blit(xSurface, 0, yv + 30 - (step * 10));
				}
				break;
			default:
				break;
		}
		flip();
	}		
	
	private void flip() {
		backSurface.blit(surface);
	}
	
	private Surface forPos(Position p) {		
		return tilePainter.createTileImage(p);	
	}
	
	private boolean isVisible(Position p) {
		int x1 = topLeftPos.x;	
		int x2 = topLeftPos.x + (X_S - 1);
		int y1 = topLeftPos.y;	
		int y2 = topLeftPos.y + (Y_S - 1);
		return p.inside(x1, y1, x2, y2);
	}
	
}
