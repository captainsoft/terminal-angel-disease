package com.captainsoft.TADr.painting.animations;

import static com.captainsoft.TADr.model.Direction.East;

import com.captainsoft.TADr.model.Direction;
import com.captainsoft.TADr.model.Position;
import com.captainsoft.TADr.painting.GameLevelMapDrawer;
import com.captainsoft.TADr.painting.TilePainter;
import com.captainsoft.spark.ui.CPos;
import com.captainsoft.spark.ui.drawing.Surface;

/**
 * Scroll the party from one tile to a neighbour tile.
 * 
 * @author mathias fringes
 */
final class PartyScroller {
	
	// fields
	
	private static final int ScrollSteps = 4;
	
	private final GameLevelMapDrawer mapDrawer;
	
	// constructors
	
	public PartyScroller(GameLevelMapDrawer mapDrawer) {
		this.mapDrawer = mapDrawer;
	}
	
	// public

    /**
     * Draws one step of a walking animation for the party.
     *
     * @param newPosition
     * @param step
     */
	public void scrollParty(Position newPosition, int step) {

		if (step < 1  || step > ScrollSteps) {
			throw new IllegalArgumentException("Wrong step number: " + step);
		}
		
		Direction dir = partyPosition().findDir(newPosition);					
		int step_w = step * 10;
		
		switch(dir) {
		
			case West:
			case East:

				int turn = (dir == East) ? 1 : -1;				
				int x = (dir == East) ? (-40 + step_w) : (40 - step_w);
				int y = (step % 2) == 0 ? 0 : -2;

                CPos hoffset = new CPos(x, y);
				Position p1 = partyPosition().add(turn, -1);
				Surface image = tilePainter().createPartyHeadTile(p1, hoffset);
				mapDrawer.blitAtNoFlip(image, p1);

				Position p2 = partyPosition().add(turn, 0);
				image = tilePainter().createPartyBottomImage(p2, hoffset);
				mapDrawer.blitAtNoFlip(image, p2);			

				mapDrawer.partyOffset = new CPos(step_w * turn, y);
				mapDrawer.retile(partyPosition().addY(-1), partyPosition());			
				break;
				
			case North:
				
				x = (step % 2) == 0 ? 0 : 3;
				
				p1 = partyPosition().add(0, -1);
				p2 = partyPosition().add(0, -2);
				
				hoffset = new CPos(x, -step_w + 40);
				image = tilePainter().createPartyHeadTile(p2, hoffset);
				mapDrawer.blitAtNoFlip(image, p2);

                CPos moffset = new CPos(x, -step_w);
				image = tilePainter().createPartyHeadTile(p1, moffset, true);
				mapDrawer.blitAtNoFlip(image, p1);

				image = tilePainter().createPartyBottomImage(partyPosition(), moffset);
				mapDrawer.blitAtNoFlip(image, partyPosition());
				break; 
				
			case South:
				
				x = (step % 2) == 0 ? 0 : -3;
				p1 = partyPosition().add(0, -1);
				p2 = partyPosition().add(0,  1);
				
				hoffset = new CPos(x, step_w);
				image = tilePainter().createPartyHeadTile(p1, hoffset);
				mapDrawer.blitAtNoFlip(image, p1);

				moffset = new CPos(x, -40 + step_w);
				image = tilePainter().createPartyHeadTile(partyPosition(), moffset, true);
				mapDrawer.blitAtNoFlip(image, partyPosition());
				
				image = tilePainter().createPartyBottomImage(p2, moffset);
	    		mapDrawer.blitAtNoFlip(image, p2);		    		
				break;
				
			default:
				break;
	    }				
		
		mapDrawer.flip();
	}

	// private
	
	private Position partyPosition() {
		return mapDrawer.partyPosition;
	}
	
	private TilePainter tilePainter() {
		return mapDrawer.tilePainter;
	}
	
}
