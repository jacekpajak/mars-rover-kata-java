package org.mars.rover.kata;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class KataTest {

    @Test
    public void testNavigatorConstructsUpperRightCoordinates() {
        String coordinatesString = "5 5";
        System.setIn(new ByteArrayInputStream(coordinatesString.getBytes()));
        MarsNavigator marsNavigator = new MarsNavigator(new Scanner(System.in));

        marsNavigator.loadInput();
        marsNavigator.processInput();
        Coordinate coordinate = marsNavigator.getCoordinate(1, 3);

        Assertions.assertEquals(coordinate.x(), 1);
        Assertions.assertEquals(coordinate.y(), 3);
    }

    @Test
    public void testNavigatorCreatesRoverInstances() {
        String coordinatesString = "5 5\n" + "1 2 N\n" + "LMLMLMLMM\n" + "3 3 E\n" + "MMRMMRMRRM\n";
        System.setIn(new ByteArrayInputStream(coordinatesString.getBytes()));
        MarsNavigator marsNavigator = new MarsNavigator(new Scanner(System.in));

        marsNavigator.loadInput();
        marsNavigator.processInput();

        Assertions.assertEquals(2, marsNavigator.getMarsRovers().size());
    }

    @Test
    @Disabled("Needs work")
    public void testNavigatorRoverMovesToTheCoordinate() {
        String coordinatesString = "5 5\n" + "1 2 N\n" + "LMLMLMLMM\n" + "3 3 E\n" + "MMRMMRMRRM\n";
        System.setIn(new ByteArrayInputStream(coordinatesString.getBytes()));
        MarsNavigator marsNavigator = new MarsNavigator(new Scanner(System.in));

        marsNavigator.loadInput();
        marsNavigator.processInput();

        Assertions.assertEquals(5, marsNavigator.getMarsRovers().get(0).getCoordinate().x());
        Assertions.assertEquals(6, marsNavigator.getMarsRovers().get(0).getCoordinate().y());

        Assertions.assertEquals(5, marsNavigator.getMarsRovers().get(1).getCoordinate().x());
        Assertions.assertEquals(1, marsNavigator.getMarsRovers().get(1).getCoordinate().y());
    }
}
