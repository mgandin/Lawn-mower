package fr.mga.lawnmower.domain;

/**
 * All the command a mower can do when he is mowing the lawn
 */
public enum Command {
  LEFT("L"),
  RIGHT("R"),
  FORWARD("F");

  private String label;

  /**
   * @param label for parsing
   */
  Command(String label) {
    this.label = label;
  }

  public static Command fromLabel(String label) {
    for (Command command : Command.values()) {
      if (command.label.equalsIgnoreCase(label)) {
        return command;
      }
    }
    return null;
  }
}
