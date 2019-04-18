package com.olify.marsrovers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.olify.marsrovers.Direction;

public class DirectionTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	 public void test_whenGetFromShortNameNThenReturnDirectionN() throws Exception{
	        Direction direction = Direction.getFromShortName('N');
	        assertEquals(direction, Direction.NORTH);
	    }

	@Test
	 public void test_whenGetFromShortNameWThenReturnDirectionW() throws Exception{
	        Direction direction = Direction.getFromShortName('W');
	        assertEquals(direction, Direction.WEST);
	    }

	@Test
	public void test_whenGetFromShortNameBThenReturnNone() throws Exception{
	        Direction direction = Direction.getFromShortName('B');
	        assertEquals(direction, Direction.NONE);
	    }

	@Test
	public void test_givenSouthWesthenLeftThenEast() throws Exception{
	        assertEquals(Direction.SOUTH.turnLeft(), Direction.EAST);
	    }

	@Test
	public void test_givenNorthWesthenLeftThenWest() throws Exception{
	        assertEquals(Direction.NORTH.turnLeft(), Direction.WEST);
	    }

	@Test
	public void test_givenSouthWesthenRightThenWest() throws Exception{
	        assertEquals(Direction.SOUTH.turnRight(), Direction.WEST);
	    }

	@Test
	 public void test_givenWestWhenRightThenNorth()throws Exception {
	        assertEquals(Direction.WEST.turnRight(), Direction.NORTH);
	    }
}