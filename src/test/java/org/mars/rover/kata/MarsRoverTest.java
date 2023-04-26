package org.mars.rover.kata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class MarsRoverTest {

  @Test
  public void roverHasAStartingPositionAndDirectionItFaces() {
    // when
    var marsRover = new MarsRover(new Coordinate(0, 1), Direction.N);

    // then
    Assertions.assertEquals(Direction.N, marsRover.getDirection());
    Assertions.assertEquals(0, marsRover.getCoordinate().x());
    Assertions.assertEquals(1, marsRover.getCoordinate().y());
  }
}
