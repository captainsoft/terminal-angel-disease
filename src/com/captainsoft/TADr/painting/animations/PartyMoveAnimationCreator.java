/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.painting.animations;

import com.captainsoft.TADr.engine.GameEngine;
import com.captainsoft.TADr.model.Direction;
import com.captainsoft.TADr.model.Position;
import com.captainsoft.TADr.painting.GameLevelMapDrawer;
import com.captainsoft.spark.render.Animation;
import com.captainsoft.spark.ui.CPos;
import com.captainsoft.spark.ui.Updater;

/**
 * Creates Animation instances for party and map movement. 
 * 
 * @author mathias fringes
 */
public final class PartyMoveAnimationCreator {

	// fields

	private final GameEngine gameEngine;
    private final GameLevelMapDrawer mapDrawer;
    private final MapScroller mapScroller;
    private final PartyScroller partyScroller;
	private final Updater updater;
		
	// constructors

	public PartyMoveAnimationCreator(GameEngine gameEngine, GameLevelMapDrawer mapDrawer, Updater updater) {
		super();
		this.gameEngine = gameEngine;
		this.mapDrawer = mapDrawer;
		this.updater = updater;
		
		mapScroller = new MapScroller(mapDrawer);
		partyScroller = new PartyScroller(mapDrawer);
	}

	// public

    /**
     * Combines the party and map scroller to provide tile movement animations.
     *
     * @param newPosition
     * @return
     */
	public Animation createScrollAnimation(final Position newPosition) {
					
		return new Animation() {
			
			private boolean mapIsScrolling = false;
            private Direction direction;
            private int partySpeed;
            private Position topLeft;

            @Override
			public int play() {

                step++;

                //
                // settings

                if (step == 1) {
                    if (mapDrawer.tileView.onBorders(newPosition, 5, 4)) {
                        mapIsScrolling = true;
                    }
                    float factor = gameEngine.settings().scrollFactor;
                    partySpeed = (int)(50 * factor);
                    direction = mapDrawer.partyPosition.findDir(newPosition);
                    topLeft = mapDrawer.tileView.topLeft.apply(direction);
                }

				boolean doScrollMap = gameEngine.settings().scrollMap;
				boolean doScrollParty = gameEngine.settings().scrollParty;

                //
                // animation steps

				switch(step) {
					case 1:
						mapDrawer.setFaceDirection(newPosition);					
						mapDrawer.retileParty();
                        updateBox();
						return 0;						
					case 2:
					case 3:
					case 4:
					case 5:		
						if (!mapIsScrolling) {
                            return 0;
                        }
                        if(doScrollMap) {
                            if (step == 2) {
                                mapDrawer.setTopLeft(topLeft);
                            }
                            mapScroller.moveMapView(direction, step - 2);
                            partyScroller.scrollParty(newPosition, (step - 1));
                            updateBox();
                            return partySpeed;
                        } else {
                            if (step == 5) {
                                mapDrawer.setTopLeft(topLeft);
                                mapScroller.moveMapViewDontScroll(direction);
                                updateBox();
                            }
                            return 0;
                        }
					case 6:
					case 7:
					case 8:
					case 9:
                        if (mapIsScrolling) {
                            return 0;
                        }
						if (!doScrollParty) {
                            return 0;
                        }
                        partyScroller.scrollParty(newPosition, (step - 5));
                        updateBox();
                        return partySpeed;
					case 10:			
						// finished						
						mapDrawer.removeParty();
						mapDrawer.partyOffset = null;																					
						mapDrawer.partyPosition = newPosition;
						mapDrawer.retileParty();
						updateBox();
						return -1;
					default:
						return -1;							
				}				
			}			
		};
	}

    /**
     * Creates the notorious "I-cannot-walk-I-shake-my-head" animation.
     *
     * @return
     */
	public Animation createCannotWalkAnimation() {
		
		return new Animation() {
			
			@Override
			public int play() {		
				
				step++;				
				int turn = mapDrawer.paintingInfo.getFaceDirection() == Direction.West ? -1 : 1;
				
				switch(step) {
					case 1:
						mapDrawer.partyOffset = new CPos(-2 * turn, -5);
						mapDrawer.paintingInfo.turn();
						mapDrawer.retileParty();
						updateBox();
						return 160;
					case 2:
						mapDrawer.partyOffset = new CPos( turn, 0);
					case 3:
						mapDrawer.partyOffset = new CPos(-turn, 0);
					case 4:										
						if (step == 4) {
							mapDrawer.partyOffset = null;
						}
						mapDrawer.paintingInfo.turn();
						mapDrawer.retileParty();
						updateBox();
						if (step != 4) {
							return 90;
						} else {
							return -1;
						}
					default:						 
						return -1;
				}				
			}
		};
	}

	// private
	
	private void updateBox() {
		updater.update();
	}
	
}
