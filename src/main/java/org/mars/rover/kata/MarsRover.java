package org.mars.rover.kata;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarsRover {
    private Position position;

    public MarsRover(final int x, final int y, final Direction direction) {
        this.position = Position.newInstance(x, y, direction);
    }
}
