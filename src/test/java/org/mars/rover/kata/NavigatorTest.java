package org.mars.rover.kata;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mars.rover.kata.commands.CommandParser;
import org.mars.rover.kata.entrydata.StdinProcessor;

public class NavigatorTest {

    @Test
    public void navigatorConstructsUpperRightCoordinates() {
        // given
        String coordinatesString = "5 5";
        System.setIn(new ByteArrayInputStream(coordinatesString.getBytes()));
        var stdinProcessor = new StdinProcessor(new CommandParser());
        var commandSet = stdinProcessor.processInput(new Scanner(System.in));

        // when
        MarsNavigator marsNavigator = new MarsNavigator(commandSet);
        marsNavigator.createGrid();
        Coordinate coordinate = marsNavigator.getCoordinate(1, 3);

        // expect
        Assertions.assertEquals(1, coordinate.x());
        Assertions.assertEquals(3, coordinate.y());
    }

    @Test
    public void navigatorCreatesRoverInstances() {
        // given
        String coordinatesString = "5 5\n" + "1 2 N\n" + "LMLMLMLMM\n" + "3 3 E\n" + "MMRMMRMRRM\n";
        System.setIn(new ByteArrayInputStream(coordinatesString.getBytes()));
        var stdinProcessor = new StdinProcessor(new CommandParser());
        var commandSet = stdinProcessor.processInput(new Scanner(System.in));
        MarsNavigator marsNavigator = new MarsNavigator(commandSet);

        // when
        marsNavigator.constructRovers();

        // expect
        Assertions.assertEquals(2, marsNavigator.getMarsRovers().size());
    }

    @Test
    @Disabled("Needs work")
    public void navigatorRoverMovesToTheCoordinate() {
        // guveb
        String coordinatesString = "5 5\n" + "1 2 N\n" + "LMLMLMLMM\n" + "3 3 E\n" + "MMRMMRMRRM\n";
        System.setIn(new ByteArrayInputStream(coordinatesString.getBytes()));
        var stdinProcessor = new StdinProcessor(new CommandParser());
        var commandSet = stdinProcessor.processInput(new Scanner(System.in));

        MarsNavigator marsNavigator = new MarsNavigator(commandSet);

        // when
        marsNavigator.createGrid();
        marsNavigator.constructRovers();

        // expect
        Assertions.assertEquals(5, marsNavigator.getMarsRovers().get(0).getPosition().x());
        Assertions.assertEquals(6, marsNavigator.getMarsRovers().get(0).getPosition().y());

        Assertions.assertEquals(5, marsNavigator.getMarsRovers().get(1).getPosition().x());
        Assertions.assertEquals(1, marsNavigator.getMarsRovers().get(1).getPosition().y());
    }
}
