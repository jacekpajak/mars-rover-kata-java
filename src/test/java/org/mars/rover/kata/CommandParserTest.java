package org.mars.rover.kata;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mars.rover.kata.commands.Command;
import org.mars.rover.kata.commands.CommandParser;
import org.mars.rover.kata.commands.TurnLeft;
import org.mars.rover.kata.commands.UnknownCommand;

public class CommandParserTest {

  @Test
  void throwsUnknownCommandForUnknownInputCharacter() {
    // given
    var unknownCommand = 'U';

    // expect
    assertThatThrownBy(() -> new CommandParser().parse(unknownCommand))
      .isInstanceOf(UnknownCommand.class)
      .hasMessage("Failed parsing command: %s", unknownCommand);
  }

  private static Stream<Arguments> supportedCommands() {
    return Stream.of(
      arguments('L', TurnLeft.class)
    );
  }

  @ParameterizedTest
  @MethodSource("supportedCommands")
  void recognizesSupportedCommands(
    char commandChar, Class<Command> commandClass)
  {
    assertThat(new CommandParser().parse(commandChar))
      .isInstanceOf(commandClass);
  }
}
