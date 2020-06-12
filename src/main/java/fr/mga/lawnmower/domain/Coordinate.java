package fr.mga.lawnmower.domain;

import java.util.Objects;

/**
 * Coordinate of a Mower
 */
public class Coordinate {

  private final Integer x;
  private final Integer y;

  public Coordinate() {
    this(0, 0);
  }

  public Coordinate(Integer x, Integer y) {
    this.x = x;
    this.y = y;
  }

  public Integer getX() {
    return x;
  }

  public Integer getY() {
    return y;
  }

  /**
   * Factory Method
   * @param x x axis coordinate
   * @param y y axis coordinate
   * @return new Coordinate
   */
  public static Coordinate from(Integer x, Integer y) {
    return new Coordinate(x, y);
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Coordinate that = (Coordinate) o;
    return Objects.equals(x, that.x) &&
      Objects.equals(y, that.y);
  }

  @Override public int hashCode() {
    return Objects.hash(x, y);
  }

  @Override public String toString() {
    return "Coordinate{" +
      "x=" + x +
      ", y=" + y +
      '}';
  }
}
