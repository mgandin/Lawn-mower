package fr.mga.lawnmower.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static fr.mga.lawnmower.domain.Position.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class MowerTest {

  @ParameterizedTest
  @MethodSource("expectedForwardPositionAndCoordinates")
  public void should_move_forward(Position position, Integer x, Integer y) {
    // Given
    Mower mower = new Mower(position, Coordinate.from(2, 2));

    // When
    Mower newMower = mower.move(Command.FORWARD);

    // Then
    assertThat(newMower).isEqualTo(new Mower(position, Coordinate.from(x, y)));
  }

  @ParameterizedTest
  @MethodSource("expectedLeftPositionAndCoordinates")
  public void should_move_left(Position initialPosition, Position newPosition, Integer x, Integer y) {
    // Given
    Mower mower = new Mower(initialPosition, Coordinate.from(2, 2));

    // When
    Mower newMower = mower.move(Command.LEFT);

    // Then
    assertThat(newMower).isEqualTo(new Mower(newPosition, Coordinate.from(x, y)));
  }

  @ParameterizedTest
  @MethodSource("expectedRightPositionAndCoordinates")
  public void should_move_right(Position initialPosition, Position newPosition, Integer x, Integer y) {
    // Given
    Mower mower = new Mower(initialPosition, Coordinate.from(2, 2));

    // When
    Mower newMower = mower.move(Command.RIGHT);

    // Then
    assertThat(newMower).isEqualTo(new Mower(newPosition, Coordinate.from(x, y)));
  }

  static Stream<Arguments> expectedForwardPositionAndCoordinates() {
    return Stream.of(
      arguments(NORTH, 2, 3),
      arguments(SOUTH, 2, 1),
      arguments(EAST, 3, 2),
      arguments(WEST, 1, 2)
    );
  }

  static Stream<Arguments> expectedLeftPositionAndCoordinates() {
    return Stream.of(
      arguments(NORTH, WEST, 2, 2),
      arguments(SOUTH, EAST, 2, 2),
      arguments(EAST, NORTH, 2, 2),
      arguments(WEST, SOUTH, 2, 2)
    );
  }

  static Stream<Arguments> expectedRightPositionAndCoordinates() {
    return Stream.of(
      arguments(NORTH, EAST, 2, 2),
      arguments(SOUTH, WEST, 2, 2),
      arguments(EAST, SOUTH, 2, 2),
      arguments(WEST, NORTH, 2, 2)
    );
  }
}
