package org.mars.rover.kata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mars.rover.kata.commands.CommandParser;
import org.mars.rover.kata.commands.MoveForward;
import org.mars.rover.kata.commands.TurnLeft;
import org.mars.rover.kata.commands.TurnRight;
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

        // when
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

        // when
        var commandSet = stringInputProcessor.processInput(new Scanner(System.in));

        // expect
        Assertions.assertEquals(2, commandSet.roverInstructions().size());
        Assertions.assertEquals(1, commandSet.roverInstructions().get(0).initialPosition().x());
        Assertions.assertEquals(2, commandSet.roverInstructions().get(0).initialPosition().y());
        Assertions.assertEquals(Direction.N, commandSet.roverInstructions().get(0).initialPosition().direction());
        Assertions.assertInstanceOf(TurnLeft.class, commandSet.roverInstructions().get(0).roverCommands().get(0));
        Assertions.assertInstanceOf(MoveForward.class, commandSet.roverInstructions().get(0).roverCommands().get(1));

        Assertions.assertEquals(3, commandSet.roverInstructions().get(1).initialPosition().x());
        Assertions.assertEquals(3, commandSet.roverInstructions().get(1).initialPosition().y());
        Assertions.assertEquals(Direction.E, commandSet.roverInstructions().get(1).initialPosition().direction());
        Assertions.assertInstanceOf(MoveForward.class, commandSet.roverInstructions().get(1).roverCommands().get(0));
        Assertions.assertInstanceOf(MoveForward.class, commandSet.roverInstructions().get(1).roverCommands().get(1));
        Assertions.assertInstanceOf(TurnRight.class, commandSet.roverInstructions().get(1).roverCommands().get(2));
    }
}
