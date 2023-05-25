package org.mars.rover.kata.entrydata;

import java.util.List;

public record CommandSet(int gridX, int gridY, List<RoverInstructions> roverInstructions) {

}
