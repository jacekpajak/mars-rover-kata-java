package org.mars.rover.kata;

import lombok.Getter;
import lombok.Setter;
import org.mars.rover.kata.location.Direction;
import org.mars.rover.kata.location.Position;

import java.util.UUID;

@Getter
@Setter
public class MarsRover {
    private Position position;
    private String uniqueId;

    public MarsRover(final int x, final int y, final Direction direction) {
        this.position = Position.newInstance(x, y, direction);
        this.uniqueId = UUID.randomUUID().toString();
    }
}
