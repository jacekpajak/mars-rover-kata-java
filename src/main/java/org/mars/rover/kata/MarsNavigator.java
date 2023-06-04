package org.mars.rover.kata;

import org.mars.rover.kata.entrydata.CommandSet;
import org.mars.rover.kata.entrydata.RoverInstructions;
import org.mars.rover.kata.entrydata.StdinProcessor;
import org.mars.rover.kata.location.*;

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

                    try {
                        instructionsForThisRover.roverCommands().forEach(
                                instruction -> {
                                    var oldRoverCoordinate = currentRover.getPosition().coordinate();
                                    this.leaveOldArea(oldRoverCoordinate, currentRover);

                                    var newRoverPosition = this.wrapEdges(instruction.execute(currentRover.getPosition()));
                                    this.enterNewArea(newRoverPosition.coordinate(), currentRover);

                                    currentRover.setPosition(
                                            this.wrapEdges(instruction.execute(currentRover.getPosition()))
                                    );
                                }
                        );
                    } catch (AreaHasObstacle ignored) {
                        // the rover stops moving
                    }
                });
    }

    protected Position wrapEdges(Position newPosition) {
        int numRows = providedInput.gridX();
        int numCols = providedInput.gridY();

        int wrappedX = (newPosition.coordinate().x() + numRows) % numRows;
        int wrappedY = (newPosition.coordinate().y() + numCols) % numCols;

        return newPosition.withCoordinate(new Coordinate(wrappedX, wrappedY));
    }

    protected void leaveOldArea(Coordinate coordinate, MarsRover marsRover) {
        this.grid.getAreasOccupiedByRovers()
                .get(coordinate.x())
                .get(coordinate.y())
                .leaveArea(marsRover);
    }

    protected void enterNewArea(Coordinate coordinate, MarsRover marsRover) {
        this.grid.getAreasOccupiedByRovers()
                .get(coordinate.x())
                .get(coordinate.y())
                .enterArea(marsRover);
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
