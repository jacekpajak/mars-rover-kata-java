package org.mars.rover.kata;

import org.mars.rover.kata.entrydata.CommandSet;
import org.mars.rover.kata.entrydata.RoverInstructions;
import org.mars.rover.kata.entrydata.StdinProcessor;
import org.mars.rover.kata.location.Coordinate;
import org.mars.rover.kata.location.Grid;
import org.mars.rover.kata.location.OccupiedArea;
import org.mars.rover.kata.location.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class MarsNavigator {
    CommandSet providedInput;

    Grid grid;

    List<MarsRover> marsRovers;

    public MarsNavigator(CommandSet providedInput) {
        this.providedInput = providedInput;
        this.grid = Grid.createGrid(providedInput.gridX(), providedInput.gridY());
        this.marsRovers = new ArrayList<>();
    }

    public void processCommandSet() {
        this.marsRovers = this.constructRovers(this.providedInput.roverInstructions());
        this.processRoverCommands(this.providedInput.roverInstructions());
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
                            instruction -> {
                                currentRover.setPosition(
                                        this.wrapEdges(instruction.execute(currentRover.getPosition()))
                                );
                            }
                    );
                });
    }

    protected Position wrapEdges(Position newPosition) {
        int numRows = providedInput.gridX();
        int numCols = providedInput.gridY();

        int wrappedX = (newPosition.coordinate().x() + numRows) % numRows;
        int wrappedY = (newPosition.coordinate().y() + numCols) % numCols;

        return newPosition.withCoordinate(new Coordinate(wrappedX, wrappedY));
    }

    public static MarsNavigator fromString(String inputString) {
        var commandSet = new StdinProcessor().processInput(inputString);

        return new MarsNavigator(commandSet);
    }

    public List<MarsRover> getMarsRovers() {
        return marsRovers;
    }

    public OccupiedArea getCoordinate(int x, int y) {
        return this.grid.getAreasOccupiedByRovers().get(x).get(y);
    }
}
