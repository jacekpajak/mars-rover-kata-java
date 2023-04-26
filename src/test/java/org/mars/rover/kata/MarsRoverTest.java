package org.mars.rover.kata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class MarsRoverTest {

  @Test
  public void roverHasAStartingPositionAndDirectionItFaces() {
    // when
    var marsRover = new MarsRover(new Coordinate(0, 1), Direction.N);

    // then
    Assertions.assertEquals(marsRover.getDirection(), Direction.N);
    Assertions.assertEquals(marsRover.getCoordinate().getX(), 0);
    Assertions.assertEquals(marsRover.getCoordinate().getY(), 1);
  }
}
