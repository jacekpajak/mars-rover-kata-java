package org.mars.rover.kata;

import org.mars.rover.kata.entrydata.CommandSet;
import org.mars.rover.kata.entrydata.RoverInstructions;
import org.mars.rover.kata.entrydata.StdinProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class MarsNavigator {
    CommandSet providedInput;

    ArrayList<ArrayList<Coordinate>> grid;

    List<MarsRover> marsRovers;

    public MarsNavigator(CommandSet providedInput) {
        this.providedInput = providedInput;
        this.grid = new ArrayList<>();
        this.marsRovers = new ArrayList<>();
    }


    public void processCommandSet() {
        this.createGrid();
        this.marsRovers = this.constructRovers(this.providedInput.roverInstructions());
        this.processRoverCommands(this.providedInput.roverInstructions());
    }

    protected void createGrid() {
        int width = this.providedInput.gridX();
        int height = this.providedInput.gridY();

        for (int i = 0; i < width; i++) {
            ArrayList<Coordinate> row = new ArrayList<>();

            for (int j = 0; j < height; j++) {
                row.add(new Coordinate(i, j));
            }

            grid.add(row);
        }
    }

    public List<MarsRover> constructRovers(List<RoverInstructions> roverInstructions) {
        return roverInstructions.stream()
                .map(RoverInstructions::initialPosition)
                .map(initialPosition -> new MarsRover(
                        initialPosition.coordinate().x(),
                        initialPosition.coordinate().y(),
                        initialPosition.direction()
                ))
                .toList();
    }

    public void processRoverCommands(List<RoverInstructions> roverInstructions) {
        IntStream.range(0, this.marsRovers.size())
                .forEach(index -> {
                    var currentRover = this.marsRovers.get(index);
                    var instructionsForThisRover = roverInstructions.get(index);

                    instructionsForThisRover.roverCommands().forEach(
                            instruction -> currentRover.setPosition(instruction.execute(currentRover.getPosition()))
                    );
                });
    }

    public static MarsNavigator fromString(String inputString) {
        var commandSet = new StdinProcessor().processInput(inputString);

        return new MarsNavigator(commandSet);
    }

    public List<MarsRover> getMarsRovers() {
        return marsRovers;
    }

    public Coordinate getCoordinate(int x, int y) {
        return this.grid.get(x).get(y);
    }
}
