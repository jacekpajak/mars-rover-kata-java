package org.mars.rover.kata;

import lombok.Getter;


@Getter
public class MarsRover {
    private final Position position;

    public MarsRover(final int x, final int y, final Direction direction) {
        this.position = new Position(x, y, direction);
    }
}
