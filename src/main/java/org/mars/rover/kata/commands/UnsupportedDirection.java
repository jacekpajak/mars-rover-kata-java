package org.mars.rover.kata.commands;

import org.mars.rover.kata.Direction;

public class UnsupportedDirection extends RuntimeException {

  public UnsupportedDirection(Direction direction) {
    super("Unsupported direction: " + direction);
  }
}
