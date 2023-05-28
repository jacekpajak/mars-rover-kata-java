package org.mars.rover.kata.commands;

import org.mars.rover.kata.Position;

public class TurnRight implements Command {

  @Override
  public Position execute(Position position) {
    return position.withDirection(position.direction().nextClockwise());
  }
}
