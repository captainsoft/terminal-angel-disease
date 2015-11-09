package main;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.captainsoft.spark.collect.Clist;
import com.captainsoft.spark.utils.Utils;

public class UtilsTest {
	
	@Test
	public void randomSelectorTests() {		
		List<String> list = new ArrayList<String>();
		list.add("one");
		Assert.assertTrue("one".equals(Utils.randomSelect(list)));
		
		list.add("two");
		Assert.assertTrue(Utils.randomSelect(list) != null);
    }
	
	@Test
	public void testRandom() {
		Assert.assertEquals(0, Utils.random(1));			
	}
	
	@Test
	public void testRandomParam() {
		Assert.assertEquals(0, Utils.random(0, 1));		
		Assert.assertEquals(5, Utils.random(5, 6));
		
		Clist<Integer> c = new Clist<Integer>(5, 6);		
		Assert.assertTrue(c.contains(Utils.random(5, 7)));
	}
	
}