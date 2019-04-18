package com.olify.marsrovers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class LocationTest {

	private final int x = 12;
    private final int y = 32;
    private final Direction direction = Direction.NORTH;
    private Point max;
    private Location location;
    private List<Point> obstacles;

    @Before
    public void setup() {
        max = new Point(50, 50);
        location = new Location(new Point(x, y), direction);
        obstacles = new ArrayList<Point>();
    }

    @Test
    public void test_whenInstantiatedThenXIsStored() throws Exception {
        assertEquals(location.getX(), x);
    }

    @Test
    public void test_whenInstantiatedThenYIsStored() throws Exception{
        assertEquals(location.getY(), y);
    }

    @Test
    public void test_whenInstantiatedThenDirectionIsStored() throws Exception{
        assertEquals(location.getDirection(), direction);
    }

    @Test
    public void test_givenDirectionNorthWhenForwardThenYDecreases() throws Exception{
        location.forward(max, obstacles);
        assertEquals(location.getY(), y - 1);
    }

    @Test
    public void test_givenDirectionSouthWhenForwardThenYIncreases() throws Exception{
        location.setDirection(Direction.SOUTH);
        location.forward(max, obstacles);
        assertEquals(location.getY(), y + 1);
    }

    @Test
    public void test_givenDirectionEastWhenForwardThenXIncreases() throws Exception{
        location.setDirection(Direction.EAST);
        location.forward(max, obstacles);
        assertEquals(location.getX(), x + 1);
    }

    @Test
    public void test_givenDirectionWestWhenForwardThenXDecreases() throws Exception{
        location.setDirection(Direction.WEST);
        location.forward(max, obstacles);
        assertEquals(location.getX(), x - 1);
    }

    @Test
    public void test_givenDirectionNorthWhenBackwardThenYIncreases() throws Exception{
        location.setDirection(Direction.NORTH);
        location.backward(max, obstacles);
        assertEquals(location.getY(), y + 1);
    }

    @Test
    public void test_givenDirectionSouthWhenBackwardThenYDecreases() throws Exception{
        location.setDirection(Direction.SOUTH);
        location.backward(max, obstacles);
        assertEquals(location.getY(), y - 1);
    }

    @Test
    public void test_givenDirectionEastWhenBackwardThenXDecreases() throws Exception{
        location.setDirection(Direction.EAST);
        location.backward(max, obstacles);
        assertEquals(location.getX(), x - 1);
    }

    @Test
    public void test_givenDirectionWestWhenBackwardThenXIncreases() throws Exception{
        location.setDirection(Direction.WEST);
        location.backward(max, obstacles);
        assertEquals(location.getX(), x + 1);
    }

    @Test
    public void test_whenTurnLeftThenDirectionIsSet() throws Exception{
        location.turnLeft();
        assertEquals(location.getDirection(), Direction.WEST);
    }

    @Test
    public void test_whenTurnRightThenDirectionIsSet() throws Exception{
        location.turnRight();
        assertEquals(location.getDirection(), Direction.EAST);
    }

    @Test
    public void test_givenSameObjectsWhenEqualsThenTrue() throws Exception{
        assertTrue(location.equals(location));
    }

    @Test
    public void test_givenDifferentObjectWhenEqualsThenFalse() throws Exception{
        assertFalse(location.equals("lrfb"));
    }

    @Test
    public void test_givenDifferentXWhenEqualsThenFalse() throws Exception{
        Location locationCopy = new Location(new Point(999, location.getY()), location.getDirection());
        assertFalse(location.equals(locationCopy));
    }

    @Test
    public void test_givenDifferentYWhenEqualsThenFalse() throws Exception{
        Location locationCopy = new Location(new Point(location.getX(), 901), location.getDirection());
        assertFalse(location.equals(locationCopy));
    }

    @Test
    public void test_givenDifferentDirectionWhenEqualsThenFalse() throws Exception{
        Location locationCopy = new Location(location.getPoint(), Direction.SOUTH);
        assertFalse(location.equals(locationCopy));
    }

    @Test
    public void test_givenSameXYDirectionWhenEqualsThenTrue() throws Exception{
        Location locationCopy = new Location(location.getPoint(), location.getDirection());
        assertTrue(location.equals(locationCopy));
    }

    @Test
    public void test_whenCopyThenDifferentObject() throws Exception{
        Location copy = location.copy();
        assertNotSame(location, copy);
    }

    @Test
    public void test_whenCopyThenEquals() throws Exception{
        Location copy = location.copy();
        assertEquals(copy, location);
    }

    @Test
    public void test_givenDirectionEastAndXEqualsMaxXWhenForwardThen1() throws Exception{
        location.setDirection(Direction.EAST);
        location.getPoint().setX(max.getX());
        location.forward(max, obstacles);
        assertEquals(location.getX(), 1);
    }

    @Test
    public void test_givenDirectionWestAndXEquals1WhenForwardThenMaxX() throws Exception{
        location.setDirection(Direction.WEST);
        location.getPoint().setX(1);
        location.forward(max, obstacles);
        assertEquals(location.getX(), max.getX());
    }

    @Test
    public void test_givenDirectionNorthAndYEquals1WhenForwardThenMaxY() throws Exception{
        location.setDirection(Direction.NORTH);
        location.getPoint().setY(1);
        location.forward(max, obstacles);
        assertEquals(location.getY(), max.getY());
    }

    @Test
    public void test_givenDirectionSouthAndYEqualsMaxYWhenForwardThen1() throws Exception{
        location.setDirection(Direction.SOUTH);
        location.getPoint().setY(max.getY());
        location.forward(max, obstacles);
        assertEquals(location.getY(), 1);
    }

    @Test
    public void test_givenObstacleWhenForwardThenReturnFalse() throws Exception{
        location.setDirection(Direction.EAST);
        obstacles.add(new Point(x + 1, y));
        assertFalse(location.forward(max, obstacles));
    }
    
    @Test
    public void test_givenObstacleWhenBackwardThenReturnFalse() throws Exception{
        location.setDirection(Direction.EAST);
        obstacles.add(new Point(x - 1, y));
        assertFalse(location.backward(max, obstacles));
    }
}