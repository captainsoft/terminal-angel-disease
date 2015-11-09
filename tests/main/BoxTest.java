package main;


import org.junit.Assert;
import org.junit.Test;

import com.captainsoft.spark.ui.box.Box;


public class BoxTest {
	
	@Test
	public void testCtors() {	
		//
		Box b = new Box(20, 50, 100, 370);
		Assert.assertEquals(b.x, 20);
		Assert.assertEquals(b.y, 50);		
		Assert.assertEquals(b.width, 100);
		Assert.assertEquals(b.height, 370);
	}
	
	@Test
	public void testInside() {
		Box b = new Box(20, 50, 100, 370);		
		Assert.assertTrue(b.contains(20, 50));
		Assert.assertTrue(b.contains(119, 419));
		Assert.assertTrue(b.contains(100, 200));	
		//
		Assert.assertFalse(b.contains(120, 420));
		Assert.assertFalse(b.contains(30, 500));
		Assert.assertFalse(b.contains(10, 10));
		Assert.assertFalse(b.contains(130, 100));	
		Assert.assertFalse(b.contains(121, 100));
	}
	
	@Test
	public void testInsideZero() {
		Box b = new Box(0, 0, 5, 5);
		Assert.assertTrue(b.contains(0, 0));
		Assert.assertTrue(b.contains(4, 4));
		//
		Assert.assertFalse(b.contains(5, 5));
		Assert.assertFalse(b.contains(4, 5));
		Assert.assertFalse(b.contains(5, 4));
	}

}
