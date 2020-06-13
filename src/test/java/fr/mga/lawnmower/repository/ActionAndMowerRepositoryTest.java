package fr.mga.lawnmower.repository;

import fr.mga.lawnmower.domain.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ActionAndMowerRepositoryTest {

  private ActionAndMowerRepository actionAndMowerRepository = new ActionAndMowerRepository();

  @Test
  public void should_parse_actions_for_one_mower() {
    // Given
    Map<String, Mower> expectedMowers = new HashMap<>();
    expectedMowers.put("c32de1e53f0848e7e1b100a2bd9af83039e0a7135dd9ebb8198a4e178d969d35"
      , new Mower(Position.NORTH, Coordinate.from(1, 2)));

    // When
    actionAndMowerRepository.addLines("1 2 N", "LFLFLFLFF");
    PriorityQueue<Action> actions = actionAndMowerRepository.findActions();
    Map<String, Mower> mowers = actionAndMowerRepository.findMowers();

    // Then
    assertThat(mowers).isEqualTo(expectedMowers);
    assertThat(actions).containsExactly(new Action(1,"1", Command.LEFT),
      new Action(2,"1",Command.FORWARD),
      new Action(3, "1",Command.LEFT),
      new Action(4, "1",Command.FORWARD),
      new Action(5, "1",Command.LEFT),
      new Action(6, "1",Command.FORWARD),
      new Action(7, "1",Command.LEFT),
      new Action(8,"1",Command.FORWARD),
      new Action(9,"1",Command.FORWARD));
  }

  @Test
  public void should_parse_actions_for_two_mower() {
    // Given
    Map<String, Mower> expectedMowers = new HashMap<>();
    expectedMowers.put("c32de1e53f0848e7e1b100a2bd9af83039e0a7135dd9ebb8198a4e178d969d35", new Mower(Position.NORTH, Coordinate.from(1, 2)));
    expectedMowers.put("c1686614fe31f2ccff27faa83d72cc6df134095284b7f5828378963d2025705d", new Mower(Position.EAST, Coordinate.from(3, 3)));

    // When
    actionAndMowerRepository.addLines("1 2 N", "LFLFLFLFF");
    actionAndMowerRepository.addLines("3 3 E", "FFRFFRFRRF");
    PriorityQueue<Action> actions = actionAndMowerRepository.findActions();
    Map<String, Mower> allMowers = actionAndMowerRepository.findMowers();

    // Then
    assertThat(allMowers).isEqualTo(expectedMowers);
    assertThat(actions).containsExactlyInAnyOrder(new Action(1,"1", Command.LEFT),
      new Action(2,"1",Command.FORWARD),
      new Action(3, "1",Command.LEFT),
      new Action(4, "1",Command.FORWARD),
      new Action(5, "1",Command.LEFT),
      new Action(6, "1",Command.FORWARD),
      new Action(7, "1",Command.LEFT),
      new Action(8,"1",Command.FORWARD),
      new Action(9,"1",Command.FORWARD),
      new Action(10,"2", Command.FORWARD),
      new Action(11,"2", Command.FORWARD),
      new Action(12,"2", Command.RIGHT),
      new Action(13, "2", Command.FORWARD),
      new Action(14,"2", Command.FORWARD),
      new Action(15, "2", Command.RIGHT),
      new Action(16, "2", Command.FORWARD),
      new Action(17, "2", Command.RIGHT),
      new Action(18, "2", Command.RIGHT),
      new Action(19, "2", Command.FORWARD)
    );
  }

  @Test
  public void should_not_parse_command() {
    // When
    // Then
    assertThatThrownBy(()-> actionAndMowerRepository.addLines("1 2 N", "")).hasMessage("error parsing empty command");
  }

  @Test
  public void should_not_parse_mower() {
    // When
    // Then
    assertThatThrownBy(()-> actionAndMowerRepository.addLines("12N", "")).hasMessage("error parsing mower 12N");
  }
}
