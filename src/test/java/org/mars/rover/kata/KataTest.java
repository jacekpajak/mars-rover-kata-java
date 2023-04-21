package org.mars.rover.kata;

import org.junit.Test;
import static org.junit.Assert.*;

public class KataTest {
    @Test
    public void roverClassExists() {
        String className = "org.mars.rover.kata.MarsRover";

        try {
            Class<?> myClass = Class.forName(className);
            assertNotNull(myClass);
            assertEquals(className, myClass.getName());
            System.out.println("Class " + className + " exists.");
        } catch (ClassNotFoundException e) {
            fail("Class " + className + " does not exist.");
        }
    }
}
