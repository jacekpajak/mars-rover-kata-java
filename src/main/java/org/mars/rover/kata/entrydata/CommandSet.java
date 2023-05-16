package org.mars.rover.kata.entrydata;

import java.util.ArrayList;

public record CommandSet(int gridX, int gridY, ArrayList<RoverInstructions> roverInstructions) {

}
