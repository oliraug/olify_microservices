package com.olify.marsrovers;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import com.olify.marsrovers.Fibonacci;

public class FibonacciTest {
    private Fibonacci fibonacci;

    @Before
    public void setUp() {
        fibonacci = new Fibonacci();
    }

    @Test
    public void test_whenCalculate0ThenReturn1()  throws Exception{
        int testIndex = 0;
        int expectedResult = 1;

        assertEquals(expectedResult, fibonacci.calculate(testIndex));
    }

    @Test
    public void test_whenCalculate1ThenReturn1() throws Exception{
        int testIndex = 1;
        int expectedResult = 1;

        assertEquals(expectedResult, fibonacci.calculate(testIndex));
    }

    @Test
    public void test_whenCalculate2ThenReturn2() throws Exception{
        int testIndex = 2;
        int expectedResult = 2;

        assertEquals(expectedResult, fibonacci.calculate(testIndex));
    }

    @Test
    public void test_whenCalculate3ThenReturn3() throws Exception{
        int testIndex = 3;
        int expectedResult = 3;

        assertEquals(expectedResult, fibonacci.calculate(testIndex));
    }

    @Test
    public void test_whenCalculate4ThenReturn5() throws Exception{
        int testIndex = 4;
        int expectedResult = 5;

        assertEquals(expectedResult, fibonacci.calculate(testIndex));
    }

    @Test
    public void test_whenCalculate5ThenReturn8() throws Exception{
        int testIndex = 5;
        int expectedResult = 8;

        assertEquals(expectedResult, fibonacci.calculate(testIndex));
    }

}