package org.mars.rover.kata

import spock.lang.Specification
import org.mars.rover.kata.commands.CommandParser
import org.mars.rover.kata.entrydata.StdinProcessor


class StdinProcessorSpec extends Specification {
    def "receives string input and constructs grid dimensions"() {
        given:
        String coordinatesString = "5 5\n" + "1 2 N\n" + "LMLMLMLMM\n" + "3 3 E\n" + "MMRMMRMRRM\n"
        System.setIn(new ByteArrayInputStream(coordinatesString.getBytes()))
        def stringInputProcessor = new StdinProcessor(new CommandParser())

        when:
        def commandSet = stringInputProcessor.processInput(new Scanner(System.in))

        then:
        commandSet.gridX() == 5
        commandSet.gridY() == 5
        commandSet.roverInstructions().size() == 2
    }

    def "transforms string input to a set of commands"() {
        given:
        String coordinatesString = "5 5\n" + "1 2 N\n" + "LMLMLMLMM\n" + "3 3 E\n" + "MMRMMRMRRM\n"
        System.setIn(new ByteArrayInputStream(coordinatesString.getBytes()))
        def stringInputProcessor = new StdinProcessor(new CommandParser())
        def commandSet = stringInputProcessor.processInput(new Scanner(System.in))

        expect:
        commandSet.roverInstructions().size() == 2
    }
}
