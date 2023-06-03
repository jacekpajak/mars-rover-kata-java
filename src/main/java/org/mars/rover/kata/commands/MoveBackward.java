package org.mars.rover.kata.commands;

import org.mars.rover.kata.Coordinate;
import org.mars.rover.kata.Position;

public class MoveBackward implements Command {

  @Override
  public Position execute(Position position) {
    int newX = position.coordinate().x();
    int newY = position.coordinate().y();

    switch (position.direction()) {
      case N -> newY -= 1;
      case E -> newX -= 1;
      case S -> newY += 1;
      case W -> newX += 1;
    }

    return position.withCoordinate(new Coordinate(newX, newY));
  }
}
