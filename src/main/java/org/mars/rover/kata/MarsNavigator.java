package org.mars.rover.kata;

import org.mars.rover.kata.entrydata.CommandSet;
import org.mars.rover.kata.entrydata.RoverInstructions;

import java.util.ArrayList;
import java.util.List;

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
        this.marsRovers = this.constructRovers(this.providedInput.roverInstructions()); // todo write test
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
                        initialPosition.x(),
                        initialPosition.y(),
                        initialPosition.direction()
                ))
                .toList();
    }

    public List<MarsRover> getMarsRovers() {
        return marsRovers;
    }

    public void setMarsRovers(ArrayList<MarsRover> marsRovers) {
        this.marsRovers = marsRovers;
    }

    public Coordinate getCoordinate(int x, int y) {
        return this.grid.get(x).get(y);
    }
}
