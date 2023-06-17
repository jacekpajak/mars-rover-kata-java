package org.mars.rover.kata.entrydata;

import java.util.List;

public record CommandSet(int width, int height, List<RoverInstructions> roverInstructions) {

}
