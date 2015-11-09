/*
 * Copyright Captainsoft 2010 - 2014.
 * All rights reserved.
 */
package main;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.captainsoft.spark.collect.Cmap;

/**
 * @author mathias fringes
 */
public class CmapTest {

    @Test
    public void testRetain() {
        Cmap<Integer, String> map = new Cmap<Integer, String>(1, "a", 2, "b", 3, "c");
        map.retain(3);
        //
        assertEquals(1, map.size());
        assertEquals("c", map.get(3));
    }
    
    @Test
    public void testRemove() {
        Cmap<Integer, String> map = new Cmap<Integer, String>(1, "a", 2, "b", 3, "c");
        map.remove(1, 2);
        //
        assertEquals(1, map.size());
        assertEquals("c", map.get(3));
    }      

}
