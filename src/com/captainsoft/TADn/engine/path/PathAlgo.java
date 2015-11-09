/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.engine.path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.captainsoft.TADn.model.Position;
import com.captainsoft.TADn.party.LevelMap;
import com.captainsoft.TADn.party.Tile;
import com.captainsoft.spark.utils.StopWatch;

/**
 * Find a way from one position on a map to another position on the same map. 
 * Is aware of the walkable-check.
 *
 * @author mathias fringes
 */
public final class PathAlgo {
	
	// fields
	
	private LevelMap map;
	private List<Knot> open = new ArrayList<Knot>();
	private List<Knot> closed = new ArrayList<Knot>();
	private Position sp;
	private Position tp;
	
	// constructors
	
	public PathAlgo(LevelMap map, Position sp, Position gp) {
		this.map = map;
		this.sp = sp;
		this.tp = gp;
	}

	// public
	
	public List<Position> find() {
		StopWatch sw = new StopWatch("FINDING A WAY");
		List<Position> f = findInternal();
		sw.print();
		return f;		
	}
	
	// private
	
	private List<Position> findInternal() {	
		//
		if (sp.equals(tp)) {
			return new ArrayList<Position>();
		}
		if (!map.tile(tp).walkable()) {
			return new ArrayList<Position>();
		}
		//
		open.clear();
		closed.clear();

		Knot start = new Knot(sp.x, sp.y, null);
		open.add(start);
		
		List<Knot> n = around(start);
		open.addAll(n);
		
		open.remove(start);
		closed.add(start);
		
		Knot best = null;	
		while(true) {			
			best = findBestInOpen();										
			open.remove(best);
			closed.add(best);			
			n = around(best);
			open.addAll(n);			
			if (best.x == tp.x && best.y == tp.y) {
				break;
			}
			if (open.size() == 0) {
				break;
		    }					
		}
		
		List<Position> f = new ArrayList<Position>();
		
		if (best != null && open.size() == 0) {
			return new ArrayList<Position>();
		}
		
		Knot rk = best;
		while(rk.before != null) {			
			f.add(new Position(rk.x, rk.y));			
			rk = rk.before;
		}
				
		open.clear();
		closed.clear();
			
		Collections.reverse(f);
		return f;
	}
	
	private List<Knot> around(Knot knot) {
		List<Knot> r = new ArrayList<Knot>();		
		for(int mx = -1; mx < 2; mx++) {
			for (int my = -1; my < 2; my++) {							
				if ((mx + my == 1) || (mx + my == -1)) {
					Position p = new Position(knot.x, knot.y).add(mx, my);
					if (p.x > 6 && p.y > 6 && p.x < 95 && p.y < 95) {
						Tile t = map.tile(p);
						if (t.walkable()) {
							Knot k = new Knot(p.x, p.y, knot);
							if (!isContainedinClosed(k)) {
								k.calc(tp);
								if (isContainedInOpen(k)) {								
									if (k.g(knot) + knot.g < k.g) {
										k.before = knot;
										k.calc(tp);
									}
								} else {
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
	// private classes

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
