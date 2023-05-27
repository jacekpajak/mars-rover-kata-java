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
        with (coordinate) {
            x() == 1
            y() == 3
        }
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

        def firstRover = marsNavigator.getMarsRovers().get(0).getPosition()
        def secondRover = marsNavigator.getMarsRovers().get(1).getPosition()

        then:
        with (firstRover) {
            x() == 3
            y() == 1
            direction() == Direction.N
        }

        with (secondRover) {
            x() == 5
            y() == 1
            direction() == Direction.E
        }
    }

    /*@IgnoreRest() todo refactor or delete
    def "navigator rover moves to the coordinate"() {
        given:
        def coordinatesString = "5 5\n" + "1 2 N\n" + "LMLMLMLMM\n" + "3 3 E\n" + "MMRMMRMRRM\n"
        System.setIn(new ByteArrayInputStream(coordinatesString.getBytes()))
        def marsNavigator = new MarsNavigator(new Scanner(System.in))

        when:
        marsNavigator.loadInput()
        marsNavigator.processInput()

        then:
        marsNavigator.getMarsRovers().get(0).getPosition().x() == 5
        marsNavigator.getMarsRovers().get(0).getPosition().y() == 6

        marsNavigator.getMarsRovers().get(1).getPosition().x() == 5
        marsNavigator.getMarsRovers().get(1).getPosition().y() == 1
    }*/
}
