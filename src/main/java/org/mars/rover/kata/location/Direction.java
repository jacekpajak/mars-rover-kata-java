package org.mars.rover.kata.location;

public enum Direction {
    N, E, S, W;

    public Direction nextClockwise() {
        return values()[(ordinal() + 1) % values().length];
    }

    public Direction nextCounterclockwise() {
        int index = ordinal() - 1;
        if (index < 0) {
            index = values().length - 1;
        }
        return values()[index];
    }
}
