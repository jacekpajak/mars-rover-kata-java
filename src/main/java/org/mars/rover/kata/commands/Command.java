package org.mars.rover.kata.commands;

import org.mars.rover.kata.Position;

public interface Command {
  Position execute(final Position position);
}
