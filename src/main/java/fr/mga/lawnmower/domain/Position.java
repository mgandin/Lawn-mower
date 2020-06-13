package fr.mga.lawnmower.domain;

/**
 * Enumeration for position and configuration to give what will be the position if you turn right
 * of left and how the coordinate will change if you do a forward
 */
public enum Position {
  NORTH("WEST", "EAST", 0, 1, "N"),
  SOUTH("EAST","WEST", 0, -1, "S"),
  EAST("NORTH", "SOUTH", 1, 0, "E"),
  WEST("SOUTH", "NORTH", -1, 0, "W"),
  ;

  private String turnLeft;
  private String turnRight;
  private Integer forwardX;
  private Integer forwardY;
  private String label;

  /**
   * @param turnLeft next position if you turn left
   * @param turnRight next position if you turn right
   * @param forwardX x coordinate to change if you do a forward
   * @param forwardY y coordinaite to change if you do a forward
   * @param label for parsing
   */
  Position(String turnLeft, String turnRight, Integer forwardX, Integer forwardY, String label) {
    this.turnLeft = turnLeft;
    this.turnRight = turnRight;
    this.forwardX = forwardX;
    this.forwardY = forwardY;
    this.label = label;
  }

  /**
   * @return next position if you turn left
   */
  public Position turnLeft() {
    return Position.valueOf(turnLeft);
  }

  /**
   * @return next position if you turn left
   */
  public Position turnRight() {
    return Position.valueOf(turnRight);
  }

  /**
   * @return x coordinate to change if you do a forward
   */
  public Integer forwardX() {
    return forwardX;
  }

  /**
   * @return y coordinate to change if you do a forward
   */
  public Integer forwardY() {
    return forwardY;
  }

  /**
   * @return label
   */
  public String getLabel() {
    return label;
  }

  public static Position fromLabel(String label) {
    for (Position position : Position.values()) {
      if (position.label.equalsIgnoreCase(label)) {
        return position;
      }
    }
    return null;
  }
}
