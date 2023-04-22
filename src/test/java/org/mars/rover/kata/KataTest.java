package org.mars.rover.kata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.*;

public class KataTest {
    @Test
    public void testRoverClassExists() {
        String className = "org.mars.rover.kata.MarsRover";

        try {
            Class<?> myClass = Class.forName(className);
            Assertions.assertNotNull(myClass);
            Assertions.assertEquals(className, myClass.getName());
            System.out.println("Class " + className + " exists.");
        } catch (ClassNotFoundException e) {
            Assertions.fail("Class " + className + " does not exist.");
        }
    }

    @Test
    public void testNavigatorClassExists() {
        String className = "org.mars.rover.kata.MarsNavigator";

        try {
            Class<?> myClass = Class.forName(className);
            Assertions.assertNotNull(myClass);
            Assertions.assertEquals(className, myClass.getName());
            System.out.println("Class " + className + " exists.");
        } catch (ClassNotFoundException e) {
            Assertions.fail("Class " + className + " does not exist.");
        }
    }

    @Test
    public void testRoverReceivesStartingPointAndPosition() {
        MarsRover marsRover = new MarsRover();

        marsRover.setDirection(Direction.N);
        marsRover.setPosition(new Position(0, 1));

        Assertions.assertEquals(marsRover.getDirection(), Direction.N);
        Assertions.assertEquals(marsRover.getPosition().getX(), 0);
        Assertions.assertEquals(marsRover.getPosition().getY(), 1);
    }


}
