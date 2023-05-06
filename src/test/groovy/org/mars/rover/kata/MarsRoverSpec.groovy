package org.mars.rover.kata

import spock.lang.Specification

class MarsRoverSpec extends Specification {

    def "rover has a starting position and direction it faces"() {
        when:
        def marsRover = new MarsRover(0, 1, Direction.N)

        then:
        marsRover.getPosition() == new Position(0, 1, Direction.N)
    }
}