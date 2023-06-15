package org.mars.rover.kata;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class MarsRover {
    private Position position;

    public MarsRover(final int x, final int y, final Direction direction) {
        this.position = new Position(new Coordinate(x, y), direction);
    }
}
