package fr.mga.lawnmower.domain;

import java.util.Objects;

/**
 * Action to execute according to a mower's id and a command
 */
public class Action implements Comparable<Action> {
  private final Integer id;
  private final String mowerId;
  private final Command command;

  /**
   * @param id action's id, to make the Action Comparable for the Queue
   * @param mowerId mower's ID
   * @param command command to execute
   */
  public Action(Integer id, String mowerId, Command command) {
    this.id = id;
    this.mowerId = mowerId;
    this.command = command;
  }

  public String getMowerId() {
    return mowerId;
  }

  public Command getCommand() {
    return command;
  }

  @Override public int compareTo(Action o) {
    return id.compareTo(o.id);
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Action action = (Action) o;
    return Objects.equals(id, action.id);
  }

  @Override public int hashCode() {
    return Objects.hash(id);
  }

  @Override public String toString() {
    return "Action{" +
      "id=" + id +
      ", mowerId=" + mowerId +
      ", command=" + command +
      '}';
  }
}
