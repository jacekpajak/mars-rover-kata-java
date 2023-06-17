package org.mars.rover.kata

import org.mars.rover.kata.location.Direction
import spock.lang.Specification
import org.mars.rover.kata.entrydata.StdinProcessor


class StdinProcessorSpec extends Specification {
    def "receives string input and constructs grid dimensions"() {
        when:
        def commandSet = new StdinProcessor().processInput(
                "5 5\n" + "1 2 N\n" + "LMLMLMLMM\n" + "3 3 E\n" + "MMRMMRMRRM\n"
        )

        then:
        with(commandSet) {
            width() == 5
            height() == 5
            roverInstructions().size() == 2

            commandSet.roverInstructions().get(0).initialPosition().coordinate().x() == 1
            commandSet.roverInstructions().get(0).initialPosition().coordinate().y() == 2
            commandSet.roverInstructions().get(0).initialPosition().direction() == Direction.N

            commandSet.roverInstructions().get(1).initialPosition().coordinate().x() == 3
            commandSet.roverInstructions().get(1).initialPosition().coordinate().y() == 3
            commandSet.roverInstructions().get(1).initialPosition().direction() == Direction.E
        }
    }

    def "transforms string input to a set of commands"() {
        given:
        def commandSet = new StdinProcessor().processInput(
                "5 5\n" + "1 2 N\n" + "LMLMLMLMM\n" + "3 3 E\n" + "MMRMMRMRRM\n"
        )

        expect:
        commandSet.roverInstructions().size() == 2
    }
}
