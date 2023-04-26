package org.mars.rover.kata.commands;

import static java.util.Optional.ofNullable;

import java.util.Map;
import java.util.function.Supplier;

public class CommandParser {

  private Map<Character, Supplier<Command>> recognizedCommands = Map.of(
    'L', () -> new TurnLeft()
  );

  public Command parse(char input) {
    return ofNullable(recognizedCommands.get(input))
        .map(Supplier::get)
        .orElseThrow(() -> new UnknownCommand(input));
  }
}
