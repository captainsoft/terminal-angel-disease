package main;

 
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.captainsoft.spark.ui.box.UiBox;
import com.captainsoft.spark.ui.box.UiBoxContainer;


public class UiBoxContainerTest {
	
	private UiBoxContainer c;
	private UiBox b1;

	@Before
	public void setup() {
		c = new UiBoxContainer();
		c.name = "container";
		c.move(100, 50);
		c.scale(320, 200);
		
		b1 = new UiBox("b1", 50, 40, 30, 20); 
		c.add(b1);
	}

	@Test
	public void testActiveBox() {
		
		Assert.assertEquals(c, c.getBox(100, 50));
		
		Assert.assertEquals(null, c.getBox(30, 200));
		Assert.assertEquals(c, c.getBox(100, 50));
		Assert.assertEquals(c, c.getBox(110, 70));		
		Assert.assertEquals(b1, c.getBox(150, 90));
	
		b1.enabled(false);
		Assert.assertEquals(b1, c.getBox(150, 90));		
		
		b1.visible(false);
		Assert.assertEquals(c, c.getBox(150, 90));
	}
	
	@Test
	public void testScreenCoordinates() {
		Assert.assertEquals(150, b1.screenx());
		Assert.assertEquals(90, b1.screeny());
	}
}
