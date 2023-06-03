package org.mars.rover.kata.location;

import lombok.With;

@With
public record Position(Coordinate coordinate, Direction direction) {
    public static Position newInstance(int x, int y, Direction direction) {
        return new Position(
                new Coordinate(x, y),
                direction
        );
    }
}
