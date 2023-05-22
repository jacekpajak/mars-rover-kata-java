package org.mars.rover.kata.entrydata;

import lombok.AllArgsConstructor;
import org.mars.rover.kata.Direction;
import org.mars.rover.kata.Position;
import org.mars.rover.kata.commands.CommandParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@AllArgsConstructor()
public class StdinProcessor implements InputProcessor {
    CommandParser commandParser;

    public CommandSet processInput(Scanner inputProvider) {
        var collectedInput = this.collectInput(inputProvider);

        var gridXAndY = this.parseXAndYParametersFromStringInput(collectedInput.get(0));
        var roverInstructionsArrayList = this.parseRoverInstructions(collectedInput);

        return new CommandSet(gridXAndY.get(0), gridXAndY.get(1), roverInstructionsArrayList);
    }

    protected ArrayList<String> collectInput(Scanner inputProvider) {
        var collectedInput = new ArrayList<String>();

        while (inputProvider.hasNext()) {
            collectedInput.add(inputProvider.nextLine());
        }

        return collectedInput;
    }

    protected List<Integer> parseXAndYParametersFromStringInput(String xAndYParameters) {
        return Arrays.stream(xAndYParameters.split(" "))
                .map(Integer::parseInt)
                .toList();
    }

    protected ArrayList<RoverInstructions> parseRoverInstructions(ArrayList<String> collectedInput) {
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

        return roverInstructionsArrayList;
    }
}
