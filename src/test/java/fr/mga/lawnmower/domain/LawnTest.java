package fr.mga.lawnmower.domain;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class LawnTest {

  @Test
  public void should_one_mower_do_commands() {
    // Given
    Map<Integer, Mower> mowers = new HashMap<>();
    PriorityQueue<Action> actions = new PriorityQueue<>();

    mowers.put(1,new Mower(Position.NORTH, new Coordinate(1, 2)));
    actions.add(new Action(1, 1,Command.LEFT));
    actions.add(new Action(2, 1,Command.FORWARD));
    actions.add(new Action(3, 1,Command.LEFT));
    Lawn lawn = new Lawn(new Coordinate(), Coordinate.from(5, 5), mowers, actions);

    // When
    List<Mower> mowersAfterActions = lawn.mow();

    // Then
    assertThat(mowersAfterActions).containsExactly(new Mower(Position.SOUTH, new Coordinate(0, 2)));
  }

  @Test
  public void should_first_mower_do_full_commands() {
    // Given
    Map<Integer, Mower> mowers = new HashMap<>();
    mowers.put(1, new Mower(Position.NORTH, new Coordinate(1, 2)));
    PriorityQueue<Action> actions = new PriorityQueue<>();
    actions.add(new Action(1,1,Command.LEFT));
    actions.add(new Action(2,1,Command.FORWARD));
    actions.add(new Action(3, 1,Command.LEFT));
    actions.add(new Action(4, 1,Command.FORWARD));
    actions.add(new Action(5, 1,Command.LEFT));
    actions.add(new Action(6, 1,Command.FORWARD));
    actions.add(new Action(7, 1,Command.LEFT));
    actions.add(new Action(8,1,Command.FORWARD));
    actions.add(new Action(9,1,Command.FORWARD));

    Lawn lawn = new Lawn(new Coordinate(), Coordinate.from(5, 5), mowers, actions);

    // When
    List<Mower> mowersAfterActions = lawn.mow();

    // Then
    assertThat(mowersAfterActions).containsExactlyInAnyOrder(
      new Mower(Position.NORTH, new Coordinate(1, 3)));
  }

  @Test
  public void should_second_mower_do_full_commands() {
    Map<Integer, Mower> mowers = new HashMap<>();
    mowers.put(2, new Mower(Position.EAST, new Coordinate(3, 3)));
    PriorityQueue<Action> actions = new PriorityQueue<>();

    actions.add(new Action(1,2, Command.FORWARD));
    actions.add(new Action(2,2, Command.FORWARD));
    actions.add(new Action(3,2, Command.RIGHT));
    actions.add(new Action(4, 2, Command.FORWARD));
    actions.add(new Action(5,2, Command.FORWARD));
    actions.add(new Action(6, 2, Command.RIGHT));
    actions.add(new Action(7, 2, Command.FORWARD));
    actions.add(new Action(8, 2, Command.RIGHT));
    actions.add(new Action(9, 2, Command.RIGHT));
    actions.add(new Action(10, 2, Command.FORWARD));

    Lawn lawn = new Lawn(new Coordinate(), Coordinate.from(5, 5), mowers, actions);

    // When
    List<Mower> mowersAfterActions = lawn.mow();

    // Then
    assertThat(mowersAfterActions).containsExactlyInAnyOrder(
      new Mower(Position.EAST, new Coordinate(5, 1)));
  }

  @Test
  public void should_two_mowers_do_commands() {
    // Given
    Map<Integer, Mower> mowers = new HashMap<>();

    mowers.put(1, new Mower(Position.NORTH, new Coordinate(1, 2)));
    mowers.put(2, new Mower(Position.EAST, new Coordinate(3, 3)));
    PriorityQueue<Action> actions = new PriorityQueue<>();
    actions.add(new Action(1,1,Command.LEFT));
    actions.add(new Action(2,2, Command.FORWARD));
    actions.add(new Action(3,1,Command.FORWARD));
    actions.add(new Action(4,2, Command.FORWARD));
    actions.add(new Action(5, 1,Command.LEFT));
    actions.add(new Action(6,2, Command.RIGHT));
    actions.add(new Action(7, 1,Command.FORWARD));
    actions.add(new Action(8, 2, Command.FORWARD));
    actions.add(new Action(9, 1,Command.LEFT));
    actions.add(new Action(10,2, Command.FORWARD));
    actions.add(new Action(11, 1,Command.FORWARD));
    actions.add(new Action(12, 2, Command.RIGHT));
    actions.add(new Action(13, 1,Command.LEFT));
    actions.add(new Action(14, 2, Command.FORWARD));
    actions.add(new Action(15,1,Command.FORWARD));
    actions.add(new Action(16, 2, Command.RIGHT));
    actions.add(new Action(17,1,Command.FORWARD));
    actions.add(new Action(18, 2, Command.RIGHT));
    actions.add(new Action(19, 2, Command.FORWARD));

    Lawn lawn = new Lawn(new Coordinate(), Coordinate.from(5, 5), mowers, actions);

    // When
    List<Mower> mowersAfterActions = lawn.mow();

    // Then
    assertThat(mowersAfterActions).containsExactlyInAnyOrder(
      new Mower(Position.NORTH, new Coordinate(1, 3)),
      new Mower(Position.EAST, new Coordinate(5, 1)));
  }
}
