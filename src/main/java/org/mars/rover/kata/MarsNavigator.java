package org.mars.rover.kata;

import org.mars.rover.kata.entrydata.CommandSet;
import org.mars.rover.kata.entrydata.RoverInstructions;

import java.util.ArrayList;

public class MarsNavigator {
    CommandSet providedInput;

    ArrayList<ArrayList<Coordinate>> grid;

    ArrayList<MarsRover> marsRovers;

    public MarsNavigator(CommandSet providedInput) {
        this.providedInput = providedInput;
        this.grid = new ArrayList<>();
        this.marsRovers = new ArrayList<>();
    }


    public void processCommandSet() {
        this.createGrid();
        this.constructRovers();
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

    public void constructRovers() {
        providedInput.roverInstructions().forEach((RoverInstructions instructions) -> {
            var initialPosition = instructions.initialPosition();

            MarsRover marsRover = new MarsRover(
                    initialPosition.x(),
                    initialPosition.y(),
                    initialPosition.direction()
            );

            this.marsRovers.add(marsRover);
        });
    }

    public ArrayList<MarsRover> getMarsRovers() {
        return marsRovers;
    }

    public void setMarsRovers(ArrayList<MarsRover> marsRovers) {
        this.marsRovers = marsRovers;
    }

    public Coordinate getCoordinate(int x, int y) {
        return this.grid.get(x).get(y);
    }
}
