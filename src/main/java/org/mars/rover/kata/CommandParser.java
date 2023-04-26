package org.mars.rover.kata;

public class CommandParser {

  public Command parse(char input) {
    throw new UnknownCommand(input);
  }
}
