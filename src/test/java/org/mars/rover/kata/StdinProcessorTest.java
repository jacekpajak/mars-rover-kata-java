package org.mars.rover.kata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mars.rover.kata.commands.CommandParser;
import org.mars.rover.kata.entrydata.StdinProcessor;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class StdinProcessorTest {

    @Test
    public void receivesStringInputAndConstructsGridDimensions() {
        // given
        String coordinatesString = "5 5\n" + "1 2 N\n" + "LMLMLMLMM\n" + "3 3 E\n" + "MMRMMRMRRM\n";
        System.setIn(new ByteArrayInputStream(coordinatesString.getBytes()));
        var stringInputProcessor = new StdinProcessor(new CommandParser());

        var commandSet = stringInputProcessor.processInput(new Scanner(System.in));

        // expect
        Assertions.assertEquals(5, commandSet.gridX());
        Assertions.assertEquals(5, commandSet.gridY());
        Assertions.assertEquals(2, commandSet.roverInstructions().size());
    }

    @Test
    public void transformsStringInputToASetOfCommands() {
        // given
        String coordinatesString = "5 5\n" + "1 2 N\n" + "LMLMLMLMM\n" + "3 3 E\n" + "MMRMMRMRRM\n";
        System.setIn(new ByteArrayInputStream(coordinatesString.getBytes()));
        var stringInputProcessor = new StdinProcessor(new CommandParser());
        var commandSet = stringInputProcessor.processInput(new Scanner(System.in));

        // expect
        Assertions.assertEquals(2, commandSet.roverInstructions().size());
    }
}
