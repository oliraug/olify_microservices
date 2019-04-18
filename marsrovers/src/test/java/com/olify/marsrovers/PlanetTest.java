package com.olify.marsrovers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PlanetTest {

	private Planet planet;
    private final Point max = new Point(50, 50);
    private List<Point> obstacles;

    @Before
    public void setUp() throws Exception {
        obstacles = new ArrayList<Point>();
        obstacles.add(new Point(10, 11));
        obstacles.add(new Point(15, 29));
        planet = new Planet(max, obstacles);
    }

    @Test
    public void test_whenInstantiatedThenMaxIsSet() throws Exception {
        assertEquals(planet.getMax(), max);
    }

    @Test
    public void test_whenInstantiatedThenObstaclesAreSet() throws Exception {
        assertEquals(planet.getObstacles(), obstacles);
    }
}