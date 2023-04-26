package org.mars.rover.kata;

public class UnknownCommand extends RuntimeException {
  public UnknownCommand(char commandChar) {
    super("Failed parsing command: " + commandChar);
  }
}
