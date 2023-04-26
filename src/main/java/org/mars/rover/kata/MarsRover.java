package org.mars.rover.kata;

import lombok.RequiredArgsConstructor;
import lombok.Getter;


@RequiredArgsConstructor
@Getter
public class MarsRover {
    private final Coordinate coordinate;

    private final Direction direction;
}
