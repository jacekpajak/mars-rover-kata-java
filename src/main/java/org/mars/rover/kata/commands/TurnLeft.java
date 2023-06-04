package org.mars.rover.kata.commands;

import org.mars.rover.kata.location.Position;

public class TurnLeft implements Command {

  @Override
  public Position execute(Position position) {
    return position.withDirection(position.direction().nextCounterclockwise());
  }
}
