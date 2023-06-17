package org.mars.rover.kata;

import org.mars.rover.kata.commands.Command;
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
        this.grid = Grid.createGrid(providedInput.width(), providedInput.height());
        this.marsRovers = new ArrayList<>();
    }

    public void processCommandSet() {
        this.marsRovers = this.constructRovers(this.providedInput.roverInstructions());
        this.processRoverCommands(this.providedInput.roverInstructions(), this.marsRovers.size());
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

    public void processRoverCommands(List<RoverInstructions> roverInstructions, int marsRoversSize) {
        IntStream.range(0, marsRoversSize).forEach(index -> processRover(roverInstructions, index));
    }

    private void processRover(List<RoverInstructions> roverInstructions, int index) {
        var currentRover = this.marsRovers.get(index);
        var instructionsForThisRover = roverInstructions.get(index);

        try {
            instructionsForThisRover.roverCommands().forEach(
                    instruction -> this.processInstruction(currentRover, instruction)
            );
        } catch (AreaHasObstacle ignored) {
            // rover keeps moving
        }
    }

    private void processInstruction(MarsRover currentRover, Command instruction) {
        var newRoverPosition = instruction.execute(currentRover.getPosition());
        this.grid.enterNewArea(currentRover, newRoverPosition);
    }

    public static MarsNavigator fromString(String inputString) {
        var commandSet = new StdinProcessor().processInput(inputString);

        return new MarsNavigator(commandSet);
    }

    public List<MarsRover> getMarsRovers() {
        return marsRovers;
    }

    public OccupiedArea getOccupiedArea(int x, int y) {
        return this.grid.getAreasOccupiedByRovers().get(x).get(y);
    }
}
