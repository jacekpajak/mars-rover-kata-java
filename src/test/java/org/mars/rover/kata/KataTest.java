package org.mars.rover.kata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

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
    public void testRoverReceivesStartingPointAndPosition() {
        MarsRover marsRover = new MarsRover();

        marsRover.setDirection(Direction.N);
        marsRover.setCoordinate(new Coordinate(0, 1));

        Assertions.assertEquals(marsRover.getDirection(), Direction.N);
        Assertions.assertEquals(marsRover.getCoordinate().getX(), 0);
        Assertions.assertEquals(marsRover.getCoordinate().getY(), 1);
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
    public void testNavigatorConstructsUpperRightCoordinates() {
        String coordinatesString = "5 5";
        System.setIn(new ByteArrayInputStream(coordinatesString.getBytes()));
        MarsNavigator marsNavigator = new MarsNavigator(new Scanner(System.in));

        marsNavigator.loadInput();
        marsNavigator.processInput();
        Coordinate coordinate = marsNavigator.getCoordinate(1, 3);

        Assertions.assertEquals(coordinate.getX(), 1);
        Assertions.assertEquals(coordinate.getY(), 3);
    }

    @Test
    public void testNavigatorCreatesRoverInstances() {
        String coordinatesString = "5 5\n"
                + "1 2 N\n"
                + "LMLMLMLMM\n"
                + "3 3 E\n"
                + "MMRMMRMRRM\n";
        System.setIn(new ByteArrayInputStream(coordinatesString.getBytes()));
        MarsNavigator marsNavigator = new MarsNavigator(new Scanner(System.in));

        marsNavigator.loadInput();
        marsNavigator.processInput();

        Assertions.assertEquals(2, marsNavigator.getMarsRovers().size());
    }

    @Test
    @Disabled("Needs work")
    public void testNavigatorRoverMovesToTheCoordinate() {
        String coordinatesString = "5 5\n"
                + "1 2 N\n"
                + "LMLMLMLMM\n"
                + "3 3 E\n"
                + "MMRMMRMRRM\n";
        System.setIn(new ByteArrayInputStream(coordinatesString.getBytes()));
        MarsNavigator marsNavigator = new MarsNavigator(new Scanner(System.in));

        marsNavigator.loadInput();
        marsNavigator.processInput();

        Assertions.assertEquals(5, marsNavigator.getMarsRovers().get(0).getCoordinate().getX());
        Assertions.assertEquals(6, marsNavigator.getMarsRovers().get(0).getCoordinate().getY());

        Assertions.assertEquals(5, marsNavigator.getMarsRovers().get(1).getCoordinate().getX());
        Assertions.assertEquals(1, marsNavigator.getMarsRovers().get(1).getCoordinate().getY());
    }
}
