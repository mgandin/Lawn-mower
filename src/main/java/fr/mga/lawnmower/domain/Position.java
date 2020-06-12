package fr.mga.lawnmower.domain;

/**
 * Enumeration for position and configuration to give what will be the position if you turn right
 * of left and how the coordinate will change if you do a forward
 */
public enum Position {
  NORTH("WEST", "EAST", 0, 1),
  SOUTH("EAST","WEST", 0, -1),
  EAST("NORTH", "SOUTH", 1, 0),
  WEST("SOUTH", "NORTH", -1, 0),
  ;

  private String turnLeft;
  private String turnRight;
  private Integer forwardX;
  private Integer forwardY;

  /**
   * @param turnLeft next position if you turn left
   * @param turnRight next position if you turn right
   * @param forwardX x coordinate to change if you do a forward
   * @param forwardY y coordinaite to change if you do a forward
   */
  Position(String turnLeft, String turnRight, Integer forwardX, Integer forwardY) {
    this.turnLeft = turnLeft;
    this.turnRight = turnRight;
    this.forwardX = forwardX;
    this.forwardY = forwardY;
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
}
