package org.mars.rover.kata;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;


public class CommandParserTest {

  @Test
  public void throwsErrorForUnknownInputCharacter() {
    // given
    var unknownCommand = 'U';

    // expect
    assertThatThrownBy(() -> new CommandParser().parse(unknownCommand))
        .isInstanceOf(UnknownCommand.class)
        .hasMessage("Failed parsing command: %s", unknownCommand);
  }
}
