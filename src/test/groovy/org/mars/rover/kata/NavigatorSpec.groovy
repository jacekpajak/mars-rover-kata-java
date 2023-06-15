package org.mars.rover.kata

import spock.lang.Specification

class NavigatorSpec extends Specification {

    def "navigator parses stdin input to coordinate"() {
        given:
        def marsNavigator = MarsNavigator.fromString("5 5")

        when:
        marsNavigator.processCommandSet()
        def coordinate = marsNavigator.getCoordinate(1, 3)

        then:
        coordinate == new Coordinate(1, 3)
    }

    def "navigator creates rover instances"() {
        given:
        def marsNavigator = MarsNavigator.fromString(
                "5 5\n" + "1 2 N\n" + "LMLMLMLMM\n" + "3 3 E\n" + "MMRMMRMRRM\n"
        )

        when:
        marsNavigator.processCommandSet()

        then:
        marsNavigator.getMarsRovers().size() == 2
    }

    def "navigator translates rover commands into final position"() {
        given:
        def marsNavigator = MarsNavigator.fromString(
                "5 5\n" + "1 2 N\n" + "LMLMLMLMM\n" + "3 3 E\n" + "MMRMMRMRRM\n"
        )

        when:
        marsNavigator.processCommandSet()

        then:
        marsNavigator.getMarsRovers() == [
                new MarsRover(1, 3, Direction.N),
                new MarsRover(5, 1, Direction.E)
        ]

    }
}
