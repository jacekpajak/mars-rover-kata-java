package org.mars.rover.kata.entrydata;

import org.mars.rover.kata.Direction;
import org.mars.rover.kata.Position;
import org.mars.rover.kata.commands.CommandParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class StdinProcessor implements InputProcessorInterface {
    CommandParser commandParser;

    public StdinProcessor(CommandParser commandParser) {
        this.commandParser = commandParser;
    }


    public CommandSet processInput(Iterator<String> stringIterator) {
        // get first input so we can construct the coordinates
        var collectedInput = new ArrayList<String>();

        while (stringIterator.hasNext()) {
            collectedInput.add(((Scanner) stringIterator).nextLine());
        }

        String[] parts = collectedInput.get(0).split(" ");

        var gridX = Integer.parseInt(parts[0]);
        var gridY = Integer.parseInt(parts[1]);
        var roverInstructionsArrayList = new ArrayList<RoverInstructions>();

        for (int i = 1; i < collectedInput.size(); i += 2) {
            String[] partsRover = collectedInput.get(i).split(" ");
            var roverPositionX = Integer.parseInt(partsRover[0]);
            var roverPositionY = Integer.parseInt(partsRover[1]);

            roverInstructionsArrayList.add(
                    new RoverInstructions(
                            new Position(roverPositionX, roverPositionY, Direction.valueOf(partsRover[2])),
                            Arrays.stream(collectedInput.get(i + 1).split("")).map(
                                    el -> this.commandParser.parse(el.charAt(0))
                            ).toList()
                    )
            );

        }

        return new CommandSet(gridX, gridY, roverInstructionsArrayList);
    }
}
