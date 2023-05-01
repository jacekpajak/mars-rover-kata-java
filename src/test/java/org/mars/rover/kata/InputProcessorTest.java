package org.mars.rover.kata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class InputProcessorTest {

    @Test
    public void receivesStringInputAndConstructsGridDimensions() {
        // given
        String coordinatesString = "5 5\n" + "1 2 N\n" + "LMLMLMLMM\n" + "3 3 E\n" + "MMRMMRMRRM\n";
        System.setIn(new ByteArrayInputStream(coordinatesString.getBytes()));
        var stringInputProcessor = new StringInputProcessor(new Scanner(System.in));

        // expect
        Assertions.assertEquals(5, stringInputProcessor.getCommandSet().gridX());
        Assertions.assertEquals(5, stringInputProcessor.getCommandSet().gridY());
        Assertions.assertEquals(2, stringInputProcessor.getCommandSet().getCommands().size());
    }

    @Test
    public void transformsStringInputToASetOfCommands() {
        // given
        String coordinatesString = "5 5\n" + "1 2 N\n" + "LMLMLMLMM\n" + "3 3 E\n" + "MMRMMRMRRM\n";
        System.setIn(new ByteArrayInputStream(coordinatesString.getBytes()));
        var stringInputProcessor = new StringInputProcessor(new Scanner(System.in));

        // expect
        Assertions.assertEquals(2, stringInputProcessor.getCommandSet().getCommands().size());
    }
}
