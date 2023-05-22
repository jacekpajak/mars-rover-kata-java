package org.mars.rover.kata

import org.mars.rover.kata.commands.Command
import org.mars.rover.kata.commands.CommandParser
import org.mars.rover.kata.commands.UnknownCommand
import org.mars.rover.kata.commands.TurnLeft;
import spock.lang.Specification
import spock.lang.Unroll

class CommandParserSpec extends Specification {

    def "throws UnknownCommand for unknown input character"() {
        given:
        char unknownCommand = 'U'

        when:
        (new CommandParser()).parse(unknownCommand)

        then:
        thrown UnknownCommand
    }

    @Unroll
    def "recognizes supported commands:"() {
        expect:
        new CommandParser().parse(commandChar as char) instanceof Command

        where:
        commandChar | expectedCommand
        'L'         | TurnLeft.class
    }
}