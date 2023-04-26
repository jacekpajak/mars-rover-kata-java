package org.mars.rover.kata;

import java.util.ArrayList;
import java.util.Scanner;

public class MarsNavigator {
    Scanner scanner;
    ArrayList<String> providedInput;

    ArrayList<ArrayList<Coordinate>> grid;

    ArrayList<MarsRover> marsRovers;

    public MarsNavigator(Scanner scanner) {
        this.scanner = scanner;
        this.providedInput = new ArrayList<>();
        this.grid = new ArrayList<>();
        this.marsRovers = new ArrayList<>();
    }

    public void loadInput() {
        // get the input from the scanner
        while (this.scanner.hasNext()) {
            this.providedInput.add(this.scanner.nextLine());
        }
    }

    public void processInput() {
        this.constructCoordinates();
        this.constructRovers();
    }

    protected void constructCoordinates() {
        // get first input so we can construct the coordinates
        String[] parts = this.providedInput.get(0).split(" ");

        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);

        for (int i = 0; i < x; i++) {
            ArrayList<Coordinate> row = new ArrayList<>();

            for (int z = 0; z < y; z++) {
                row.add(new Coordinate(i, z));
            }

            grid.add(row);
        }
    }

    public void constructRovers() {
        for (int i = 1; i < providedInput.size(); i += 2) {
            try {
                String[] partsRover = providedInput.get(i).split(" ");
                int positionX = Integer.parseInt(partsRover[0]);
                int positionY = Integer.parseInt(partsRover[1]);

                MarsRover marsRover = new MarsRover(new Coordinate(positionX, positionY),
                        Direction.valueOf(partsRover[2]));

                // TODO: Fix
                // marsRovers.add(new MarsRover());
                marsRovers.add(marsRover);
                // providedInput.get(i + 1); todo implement
            } catch (IndexOutOfBoundsException ignored) {

            }
        }
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
