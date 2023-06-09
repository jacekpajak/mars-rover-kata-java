package org.mars.rover.kata

import spock.lang.Specification
import org.mars.rover.kata.commands.CommandParser
import org.mars.rover.kata.entrydata.StdinProcessor


class StdinProcessorSpec extends Specification {
    def "receives string input and constructs grid dimensions"() {
        when:
        def commandSet = new StdinProcessor().processInput(
            "5 5\n" + "1 2 N\n" + "LMLMLMLMM\n" + "3 3 E\n" + "MMRMMRMRRM\n"
        )

        then:
        with(commandSet) {
            gridX() == 5
            gridY() == 5
            roverInstructions().size() == 2

            commandSet.roverInstructions().get(0).initialPosition().x() == 1
            commandSet.roverInstructions().get(0).initialPosition().y() == 2
            commandSet.roverInstructions().get(0).initialPosition().direction() == Direction.N

            commandSet.roverInstructions().get(1).initialPosition().x() == 3
            commandSet.roverInstructions().get(1).initialPosition().y() == 3
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
