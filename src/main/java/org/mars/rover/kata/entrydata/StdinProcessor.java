package org.mars.rover.kata.entrydata;

import org.mars.rover.kata.Coordinate;
import org.mars.rover.kata.Direction;
import org.mars.rover.kata.Position;
import org.mars.rover.kata.commands.CommandParser;
import java.util.*;

public class StdinProcessor implements InputProcessor {
    public CommandSet processInput(String input) {
        var collectedInput = this.collectInput(input);

        var gridXAndY = this.parseInitRoverPosition(collectedInput.get(0));
        var roverInstructionsArrayList = this.parseRoverInstructions(collectedInput);

        return new CommandSet(gridXAndY.get(0), gridXAndY.get(1), roverInstructionsArrayList);
    }

    protected ArrayList<String> collectInput(String input) {
        var collectedInput = new ArrayList<String>();
        var tokenizer = new StringTokenizer(input, "\n");

        while (tokenizer.hasMoreTokens()) {
            collectedInput.add(tokenizer.nextToken());
        }

        return collectedInput;
    }

    protected List<Integer> parseInitRoverPosition(String xAndYParameters) {
        return Arrays.stream(xAndYParameters.split(" "))
                .map(Integer::parseInt)
                .toList();
    }

    protected ArrayList<RoverInstructions> parseRoverInstructions(ArrayList<String> collectedInput) {
        var roverInstructionsArrayList = new ArrayList<RoverInstructions>();
        var commandParser = new CommandParser();

        for (int i = 1; i < collectedInput.size(); i += 2) {
            String[] partsRover = collectedInput.get(i).split(" ");
            var roverPositionX = Integer.parseInt(partsRover[0]);
            var roverPositionY = Integer.parseInt(partsRover[1]);

            roverInstructionsArrayList.add(
                    new RoverInstructions(
                            new Position(new Coordinate(roverPositionX, roverPositionY), Direction.valueOf(partsRover[2])),
                            Arrays.stream(collectedInput.get(i + 1).split("")).map(
                                    el -> commandParser.parse(el.charAt(0))
                            ).toList()
                    )
            );
        }

        return roverInstructionsArrayList;
    }
}
