package org.mars.rover.kata.entrydata;

import org.mars.rover.kata.Direction;
import org.mars.rover.kata.Position;
import org.mars.rover.kata.commands.CommandParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class StdinProcessor implements InputProcessorInterface {
    CommandParser commandParser;
    ArrayList<String> collectedInput;

    public StdinProcessor(CommandParser commandParser, Scanner inputProvider) {
        this.commandParser = commandParser;
        this.collectedInput = this.collectInput(inputProvider);
    }


    public CommandSet processInput() {
        var parts = this.parseGridParameter();
        var roverInstructionsArrayList = this.parseRoverInstructions();

        return new CommandSet(parts.get(0), parts.get(1), roverInstructionsArrayList);
    }

    protected ArrayList<String> collectInput(Scanner inputProvider) {
        var collectedInput = new ArrayList<String>();

        while (inputProvider.hasNext()) {
            collectedInput.add(inputProvider.nextLine());
        }

        return collectedInput;
    }

    protected List<Integer> parseGridParameter() {
        return Arrays.stream(this.collectedInput.get(0).split(" "))
                .map(Integer::parseInt)
                .toList();
    }

    protected ArrayList<RoverInstructions> parseRoverInstructions() {
        var roverInstructionsArrayList = new ArrayList<RoverInstructions>();

        for (int i = 1; i < this.collectedInput.size(); i += 2) {
            String[] partsRover = this.collectedInput.get(i).split(" ");
            var roverPositionX = Integer.parseInt(partsRover[0]);
            var roverPositionY = Integer.parseInt(partsRover[1]);

            roverInstructionsArrayList.add(
                    new RoverInstructions(
                            new Position(roverPositionX, roverPositionY, Direction.valueOf(partsRover[2])),
                            Arrays.stream(this.collectedInput.get(i + 1).split("")).map(
                                    el -> this.commandParser.parse(el.charAt(0))
                            ).toList()
                    )
            );
        }

        return roverInstructionsArrayList;
    }
}
