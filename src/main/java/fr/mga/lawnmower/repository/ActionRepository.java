package fr.mga.lawnmower.repository;

import fr.mga.lawnmower.domain.Action;
import fr.mga.lawnmower.domain.Command;

import java.util.List;
import java.util.PriorityQueue;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Parse a list of String and retrieve Actions for Mowers
 */
public class ActionRepository {
  public static final String SEPARATOR = "";
  private final List<String> lines;

  /**
   * @param lines to parse
   */
  public ActionRepository(List<String> lines) {
    this.lines = lines;
    checkNotNull(lines);
    checkArgument(lines.size() > 0, "lines should not be empty");
  }

  /**
   * @return actions for mowers
   */
  public PriorityQueue<Action> findAll() {
    PriorityQueue<Action> actions = new PriorityQueue<>();
    int actionId = 0;
    for (int i = 0; i < lines.size(); i++) {
      String line = this.lines.get(i);
      if(!line.isBlank() || !line.isEmpty()) {
        String[] actionsAsString = line.split(SEPARATOR);
        for (int j = 0; j < actionsAsString.length; j++) {
          Action action = new Action(++actionId, i + 1, Command.fromLabel(actionsAsString[j]));
          actions.add(action);
        }
      } else {
        throw new IllegalArgumentException("error parsing empty command");
      }
    }
    return actions;
  }
}
