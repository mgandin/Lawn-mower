package fr.mga.lawnmower.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static fr.mga.lawnmower.domain.Position.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class PositionTest {

  @ParameterizedTest
  @MethodSource("expectedPositionAndTurns")
  public void should_turn_from_position(Position position, Position positionWhenTurnLeft,
    Position positionWhenTurnRight, Integer forwardX, Integer forwardY) {
    // Given
    // When
    // Then
    assertThat(position.turnLeft()).isEqualTo(positionWhenTurnLeft);
    assertThat(position.turnRight()).isEqualTo(positionWhenTurnRight);
    assertThat(position.forwardX()).isEqualTo(forwardX);
    assertThat(position.forwardY()).isEqualTo(forwardY);
  }

  static Stream<Arguments> expectedPositionAndTurns() {
    return Stream.of(
      arguments(NORTH, WEST, EAST, 0, 1),
      arguments(SOUTH, EAST, WEST, 0, -1),
      arguments(EAST, NORTH, SOUTH, 1, 0),
      arguments(WEST, SOUTH, NORTH, -1, 0)
    );
  }
}
