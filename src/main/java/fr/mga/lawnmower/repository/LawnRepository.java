package fr.mga.lawnmower.repository;

import fr.mga.lawnmower.domain.Coordinate;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Parse content of the file (the first line) to retrieve the upper right corner's coordinate
 */
public class LawnRepository {
  public static final String SEPARATOR = " ";
  private final String line;

  /**
   * @param line to parse
   */
  public LawnRepository(String line) {
    this.line = line;
    checkNotNull(line);
    checkArgument(!line.isEmpty(), "line should not be empty");
    checkArgument(!line.isBlank(), "line should not be blank");
  }

  /**
   * @return Upper right corner's coordinate
   */
  public Coordinate getUpperRightCoordinate() {
    String[] splittedLine = line.split(SEPARATOR);
    return Coordinate.from(Integer.valueOf(splittedLine[0]), Integer.valueOf(splittedLine[1]));
  }
}
