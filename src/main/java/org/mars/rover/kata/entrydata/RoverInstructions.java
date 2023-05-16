package org.mars.rover.kata.entrydata;

import org.mars.rover.kata.Position;
import org.mars.rover.kata.commands.Command;

import java.util.List;

public record RoverInstructions (Position initialPosition, List<Command> roverCommands) {
}
