package org.mars.rover.kata;

import lombok.With;

@With
public record Position(Coordinate coordinate, Direction direction) {}
