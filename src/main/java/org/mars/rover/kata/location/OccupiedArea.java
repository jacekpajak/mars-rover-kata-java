package org.mars.rover.kata.location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mars.rover.kata.MarsRover;

import java.util.HashMap;

@AllArgsConstructor
@Getter
public class OccupiedArea {
    private Coordinate coordinate;

    private HashMap<String, MarsRover> mapOfRoversOccupyingThisArea;

    public static OccupiedArea newInstance(int width, int height) {
        return new OccupiedArea(
                new Coordinate(width, height),
                new HashMap<>()
        );
    }
}
