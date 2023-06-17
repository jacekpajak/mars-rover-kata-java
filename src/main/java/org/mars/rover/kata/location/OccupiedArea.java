package org.mars.rover.kata.location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.mars.rover.kata.MarsRover;

@AllArgsConstructor
@Getter
@Setter
public class OccupiedArea {
    private Coordinate coordinate;

    private boolean hasObstacle;

    public static OccupiedArea newInstance(int width, int height) {
        return new OccupiedArea(
                new Coordinate(width, height),
                false
        );
    }

    public void enterArea(MarsRover marsRover) {
        if (this.hasObstacle) {
            var coordinate = marsRover.getPosition().coordinate();
            throw new AreaHasObstacle(
                    String.format("Tried to enter but failed: %d, %d", coordinate.x(), coordinate.y())
            );
        }
    }
}
