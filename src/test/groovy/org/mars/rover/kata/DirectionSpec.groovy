package org.mars.rover.kata

import spock.lang.Specification
import spock.lang.Unroll

class DirectionSpec extends Specification {
    @Unroll
    def "returns clockwise directions"() {
        expect:
        (initialDirection as Direction).nextClockwise() == rotatedDirection

        where:
        initialDirection | rotatedDirection
        Direction.E | Direction.S
        Direction.S | Direction.W
        Direction.W | Direction.N
        Direction.N | Direction.E
    }

    @Unroll
    def "returns counter clockwise directions"() {
        expect:
        (initialDirection as Direction).nextCounterclockwise() == rotatedDirection

        where:
        initialDirection | rotatedDirection
        Direction.E | Direction.N
        Direction.N | Direction.W
        Direction.W | Direction.S
        Direction.S | Direction.E

    }
}
