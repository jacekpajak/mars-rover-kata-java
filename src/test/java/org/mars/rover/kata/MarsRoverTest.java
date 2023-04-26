package org.mars.rover.kata;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;


public class MarsRoverTest {

  @Test
  public void roverHasAStartingPositionAndDirectionItFaces() {
    // given
    var startingPosition = new Coordinate(0, 1);
    var startingDirection = Direction.N;

    // when
    var marsRover = new MarsRover(startingPosition, startingDirection);

    // then
    assertThat(marsRover.getCoordinate()).isEqualTo(startingPosition);
    assertThat(marsRover.getDirection()).isEqualTo(startingDirection);
  }
}
