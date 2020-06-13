package fr.mga.lawnmower.domain;

import java.util.Objects;

/**
 * Domain object for Mower, it has a Position (N, E, W, S) and a Coordinate (x, y)
 * move() method give a Mower with a new Position and Coordinate according to a Command
 * canMove() method verify if the mower can make a Command without bumping into another Mower,
 * or getting outside the Lawn
 */
public class Mower {

  private final Position position;
  private final Coordinate coordinate;

  /**
   * @param position The Position (N, E, W, S) of the Mower
   * @param coordinate The Coordinate (x, y) of the Mower
   */
  public Mower(Position position, Coordinate coordinate) {
    this.position = position;
    this.coordinate = coordinate;
  }

  /**
   * Verify if the mower's next move is still inside the Lawn
   * @param tryCommand mower's next move
   * @param bottomLeft Bottom Left Corner of the Lawn
   * @param upperRight Upper Right Corner of the Lawn
   * @return if mower can make his next move
   */
  public boolean canMove(Command tryCommand, Coordinate bottomLeft, Coordinate upperRight) {
    Mower tryMove = move(tryCommand);
    return tryMove.coordinate.getX() >= bottomLeft.getX()
      && tryMove.coordinate.getX() <= upperRight.getX()
      && tryMove.coordinate.getY() >= bottomLeft.getY()
      && tryMove.coordinate.getY() <= upperRight.getY();
  }

  /**
   * Verify if the mower's next move don't bump into another mower
   * @param tryCommand mower's next move
   * @param otherMower another mower
   * @return if mower can make his next move
   */
  public boolean canMove(Command tryCommand, Mower otherMower) {
    Mower tryMove = move(tryCommand);
    return !tryMove.coordinate.getX().equals(otherMower.coordinate.getX())
      || !tryMove.coordinate.getY().equals(otherMower.coordinate.getY());
  }

  /**
   * Give you mower's next move according to a Command
   * @param command
   * @return
   */
  public Mower move(Command command) {
    switch (command) {
    case FORWARD:
      return new Mower(position, Coordinate.from(coordinate.getX() + position.forwardX(), coordinate.getY() + position.forwardY()));
    case LEFT:
      return new Mower(position.turnLeft(), coordinate);
    case RIGHT:
      return new Mower(position.turnRight(), coordinate);
    };
    return null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Mower mower = (Mower) o;
    return position == mower.position &&
      Objects.equals(coordinate, mower.coordinate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(position, coordinate);
  }

  @Override public String toString() {
    return coordinate.getX() + " " + coordinate.getY() + " " + position.getLabel();
  }
}
