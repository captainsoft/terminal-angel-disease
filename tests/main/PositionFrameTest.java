/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package main;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.captainsoft.TADr.model.Position;
import com.captainsoft.TADr.model.PositionBox;
import com.captainsoft.spark.utils.PointBox;


public class PositionFrameTest {
	
	@Test
	public void testSimple() {		
		PointBox<Position> pf = new PositionBox(new Position(1,1), 4, 4);			
		int count = 0;
		
		List<Position> plist = new ArrayList<Position>();
		while(pf.hasMoreElements()) {
			Position p = pf.nextElement();
			plist.add(p);
			count++;
		}
		Assert.assertEquals(16, count);	
		Assert.assertEquals(16, plist.size());
			
		Assert.assertEquals(new Position(1,1), plist.get(0));
		Assert.assertEquals(new Position(2,1), plist.get(1));
		Assert.assertEquals(new Position(4,4), plist.get(15));
	}
	
	@Test
	public void testNonRect() {		
		PointBox<Position> pf = new PositionBox(new Position(8,3), 2, 7);			
		int count = 0;
		
		List<Position> plist = new ArrayList<Position>();
		while(pf.hasMoreElements()) {
			Position p = pf.nextElement();
			plist.add(p);
			count++;
		}
		Assert.assertEquals(14, count);	
		Assert.assertEquals(14, plist.size());
			
		Assert.assertEquals(new Position(8,3), plist.get(0));
		Assert.assertEquals(new Position(9,3), plist.get(1));
		Assert.assertEquals(new Position(9,9), plist.get(13));
	}
	
}
