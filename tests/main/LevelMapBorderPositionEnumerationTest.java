/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package main;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.captainsoft.TADr.model.Position;
import com.captainsoft.TADr.model.map.LevelMapBorderPositionEnumeration;


/**
 * 
 *
 * @author mathias fringes
 */
public final class LevelMapBorderPositionEnumerationTest {
	
	@Test
	public void test() {
		Enumeration<Position> e = new LevelMapBorderPositionEnumeration();		
		List<Position> p = new ArrayList<Position>();		
		while (e.hasMoreElements()) {
			p.add(e.nextElement());
		}		
		Assert.assertEquals(392, p.size());		
	}

}