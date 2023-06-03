package org.mars.rover.kata

import org.mars.rover.kata.location.Direction
import org.mars.rover.kata.location.Position
import spock.lang.Specification

class MarsRoverSpec extends Specification {

    def "rover has a starting position and direction it faces"() {
        expect:
        new MarsRover(0, 1, Direction.N).getPosition() == Position.newInstance(0, 1, Direction.N)
    }

    def "rover has a unique id that is different from any other"() {
        expect:
        new MarsRover(0, 1, Direction.N).getUniqueId() != new MarsRover(1, 1, Direction.S).getUniqueId()
    }
}