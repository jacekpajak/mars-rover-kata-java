package org.mars.rover.kata

import spock.lang.Specification

class NavigatorSpec extends Specification {

    def "navigator constructs upper right coordinates"() {
        given:
        def coordinatesString = "5 5"
        System.setIn(new ByteArrayInputStream(coordinatesString.getBytes()))
        def marsNavigator = new MarsNavigator(new Scanner(System.in))

        when:
        marsNavigator.loadInput()
        marsNavigator.processInput()
        def coordinate = marsNavigator.getCoordinate(1, 3)

        then:
        coordinate.x() == 1
        coordinate.y() == 3
    }

    def "navigator creates rover instances"() {
        given:
        def coordinatesString = "5 5\n" + "1 2 N\n" + "LMLMLMLMM\n" + "3 3 E\n" + "MMRMMRMRRM\n"
        System.setIn(new ByteArrayInputStream(coordinatesString.getBytes()))
        def marsNavigator = new MarsNavigator(new Scanner(System.in))

        when:
        marsNavigator.loadInput()
        marsNavigator.processInput()

        then:
        marsNavigator.getMarsRovers().size() == 2
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
