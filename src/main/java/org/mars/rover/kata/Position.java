package org.mars.rover.kata;

import lombok.With;

@With
public record Position(int x, int y, Direction direction) { }
