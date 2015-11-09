/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package main;

import org.junit.Assert;
import org.junit.Test;

import com.captainsoft.TADr.model.Position;
import com.captainsoft.TADr.model.map.MapTileView;

/**
 * 
 * @author mathias fringes
 */
public class MapTileViewTest {
	
	@Test
	public void testCenter() {
		MapTileView m = new MapTileView(10, 10);
		m.topLeft = new Position(78, 12);
		Assert.assertEquals(m.center(), new Position(78 + 5, 12 + 5));
	}
	
	@Test
	public void testCenterUneven() {
		MapTileView m = new MapTileView(11, 5);
		m.topLeft = new Position(1, 1);
		Assert.assertEquals(new Position(6, 3), m.center());
	}
	
	public void testSetmiddle() {		
		MapTileView m = new MapTileView(20, 30);
		m.center(new Position(37, 42));
		
		Assert.assertEquals(new Position(37, 42), m.center());
		Assert.assertEquals(new Position(27, 28), m.topLeft);
	}

}
