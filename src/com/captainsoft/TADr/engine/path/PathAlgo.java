/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.engine.path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.captainsoft.TADr.model.Position;
import com.captainsoft.TADr.model.map.LevelMap;
import com.captainsoft.TADr.model.map.Tile;
import com.captainsoft.spark.utils.Log;
import com.captainsoft.spark.utils.StopWatch;

/**
 * Find a way from one position on a map to another position on the same map. 
 * Is aware of the walkable-check. Uses A* algorithm.
 *
 * @author mathias fringes
 */
public final class PathAlgo {
	
	// fields
	
	private final LevelMap map;
	private final List<Knot> open = new ArrayList<Knot>();
	private final List<Knot> closed = new ArrayList<Knot>();
	private final Position startPosition;
	private final Position destPosition;
	
	// constructors
	
	public PathAlgo(LevelMap map, Position start, Position dest) {
		this.map = map;
		this.startPosition = start;
		this.destPosition = dest;
	}

	// public

    public List<Position> find() {
        return find(false);
    }

	public List<Position> find(boolean noDestination) {
		StopWatch sw = new StopWatch("FINDING A WAY");
		List<Position> f = findInternal(noDestination);
		Log.log(5, sw);
		return f;		
	}
	
	// private
	
	private List<Position> findInternal(boolean noDestination) {
		//
		if (startPosition.equals(destPosition)) {
			return new ArrayList<Position>(0);
		}
		if (!noDestination && !map.tile(destPosition).walkable()) {
			return new ArrayList<Position>(0);
		}
		//
		open.clear();
		closed.clear();

		Knot start = new Knot(startPosition.x, startPosition.y, null);
		open.add(start);
		
		List<Knot> n = around(start, noDestination);
        if (n == null) {
            return new ArrayList<Position>(0);
        };
		open.addAll(n);
		
		open.remove(start);
		closed.add(start);
		
		Knot best;
		while(true) {			
			best = findBestInOpen();
			if (best == null) {
				break;
			}
			open.remove(best);
			closed.add(best);			
			n = around(best, noDestination);
            if (n != null && n.size() > 0) {
                open.addAll(n);
            }
			if (best.x == destPosition.x && best.y == destPosition.y) {
				break;
			}
			if (open.size() == 0) {
				break;
		    }					
		}
		
		List<Position> f = new ArrayList<Position>();
		
		if (best != null && open.size() == 0 || best == null) {
			return new ArrayList<Position>(0);
		}
		
		Knot rk = best;
		while(rk.before != null) {			
			f.add(new Position(rk.x, rk.y));			
			rk = rk.before;
		}
				
		open.clear();
		closed.clear();

        if (noDestination && f.size() > 0) {
            f.remove(0);
        }
			
		Collections.reverse(f);
		return f;
	}
	
	private List<Knot> around(Knot knot, boolean noDestination) {
		List<Knot> r = null;

		for(int mx = -1; mx < 2; mx++) {
			for (int my = -1; my < 2; my++) {

                Position p = new Position(knot.x, knot.y).add(mx, my);

                boolean atDest = p.equals(destPosition);
				if ((mx + my == 1) || (mx + my == -1) || (noDestination && atDest)) {

                    if (p.x > 6 && p.y > 5 && p.x < 95 && p.y < 95) {

						Tile t = map.tile(p);
                        boolean walkable = (t.walkable() || (noDestination && atDest));
						if (walkable) {
							Knot k = new Knot(p.x, p.y, knot);
							if (!isContainedinClosed(k)) {
								k.calc(destPosition);
								if (isContainedInOpen(k)) {								
									if (k.g(knot) + knot.g < k.g) {
										k.before = knot;
										k.calc(destPosition);
									}
								} else {
                                    if (r == null) {
                                        r = new ArrayList<Knot>();
                                    }
									r.add(k);
								}
							}
						}

					}

				}

			}
		}
		
		return r;
	}
	
	private Knot findBestInOpen() {
		Knot r = null;
		for(Knot k : open) {
			if (r == null || k.f < r.f) {
				r = k;
			}
		}
		return r;
	}	
	
	private boolean isContainedInOpen(Knot knot) {
		return open.contains(knot);
	}
	
	private boolean isContainedinClosed(Knot knot) {
		return closed.contains(knot);
	}
	
	//
	// nested classes

	private static class Knot {
		
		public Knot before;
		public final int x;
		public final int y;
		public int g;
		public int h;
		public int f;		
		
		public Knot(int x, int y, Knot before) {
			this.x = x;
			this.y = y;
			this.before = before;			
		}
		
		public void calc(Position goal) {
			//
			// calc g
			if (before != null) {
				if ((before.x == x) || y == before.y) {
					g = 10;
				} else {
					g = 14;
				}
			} else {
				g = 0;
			}			
			//
			// calc h
			int xe = goal.x - x;
			int ye = goal.y - y;
			if (xe < 0) {
				xe = xe * -1;
			}
			if (ye < 0) {
				ye = ye * -1;
			}
			h = (xe + ye) *  10;
			//
			// calc f
			f = g + h;			
		}
		
		public int g(Knot k) {
			if (k.x == x || k.y == y) {
				return 10;
			} else {
				return 14;
			}
		}
		
		@Override
		public int hashCode() {
			return x ^ y;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}				
			Knot other = (Knot) obj;
			return x == other.x && y == other.y;
		}
	}
	
}
