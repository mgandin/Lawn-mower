package fr.mga.lawnmower.repository;

import fr.mga.lawnmower.domain.Action;
import fr.mga.lawnmower.domain.Command;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import static org.assertj.core.api.Assertions.assertThat;

public class ActionRepositoryTest {

  @Test
  public void should_parse_actions_for_one_mower() {
    // Given
    List<String> lines = Arrays.asList("LFLFLFLFF");
    ActionRepository actionRepository = new ActionRepository(lines);

    // When
    PriorityQueue<Action> actions = actionRepository.findAll();

    // Then
    assertThat(actions).containsExactly(new Action(1,1, Command.LEFT),
      new Action(2,1,Command.FORWARD),
      new Action(3, 1,Command.LEFT),
      new Action(4, 1,Command.FORWARD),
      new Action(5, 1,Command.LEFT),
      new Action(6, 1,Command.FORWARD),
      new Action(7, 1,Command.LEFT),
      new Action(8,1,Command.FORWARD),
      new Action(9,1,Command.FORWARD));
  }

  @Test
  public void should_parse_actions_for_two_mower() {
    // Given
    List<String> lines = Arrays.asList("LFLFLFLFF", "FFRFFRFRRF");
    ActionRepository actionRepository = new ActionRepository(lines);

    // When
    PriorityQueue<Action> actions = actionRepository.findAll();
    assertThat(actions).containsExactly(new Action(1,1, Command.LEFT),
      new Action(2,1,Command.FORWARD),
      new Action(3, 1,Command.LEFT),
      new Action(4, 1,Command.FORWARD),
      new Action(5, 1,Command.LEFT),
      new Action(6, 1,Command.FORWARD),
      new Action(7, 1,Command.LEFT),
      new Action(8,1,Command.FORWARD),
      new Action(9,1,Command.FORWARD),
      new Action(10,2, Command.FORWARD),
      new Action(11,2, Command.FORWARD),
      new Action(12,2, Command.RIGHT),
      new Action(13, 2, Command.FORWARD),
      new Action(14,2, Command.FORWARD),
      new Action(15, 2, Command.RIGHT),
      new Action(16, 2, Command.FORWARD),
      new Action(17, 2, Command.RIGHT),
      new Action(18, 2, Command.RIGHT),
      new Action(19, 2, Command.FORWARD)
    );
  }
}
