package org.mars.rover.kata;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;


public class MarsRoverTest {

  @Test
  public void roverHasAStartingPositionAndDirectionItFaces() {
    // when
    var marsRover = new MarsRover(0, 1, Direction.N);

    // then
    assertThat(marsRover.getPosition())
      .isEqualTo(new Position(0, 1, Direction.N));
  }
}
