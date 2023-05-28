package org.mars.rover.kata

import spock.lang.Specification

class MarsRoverSpec extends Specification {

    def "rover has a starting position and direction it faces"() {
        expect:
        new MarsRover(0, 1, Direction.N).getPosition() == Position.newInstance(0, 1, Direction.N)
    }
}