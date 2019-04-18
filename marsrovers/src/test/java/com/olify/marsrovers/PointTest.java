package com.olify.marsrovers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PointTest {

	private Point point;
    private final int x = 10;
    private final int y = 25;

	@Before
	public void setUp() throws Exception {
		 point = new Point(x, y);
	}

	@Test
    public void test_whenInstantiatedThenXIsSet() throws Exception{
        assertEquals(point.getX(), x);
    }

	@Test
    public void test_whenInstantiatedThenYIsSet()throws Exception {
        assertEquals(point.getY(), y);
    }
}