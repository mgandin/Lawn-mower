package fr.mga.lawnmower.repository;

import fr.mga.lawnmower.domain.Coordinate;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Parse content of the file (the first line) to retrieve the upper right corner's coordinate
 */
public class LawnRepository {
  public static final String SEPARATOR = " ";

  private Coordinate coordinate;

  /**
   * @param line to parse
   */
  public void parse(String line) {
    checkNotNull(line);
    checkArgument(!line.isEmpty(), "line should not be empty");
    checkArgument(!line.isBlank(), "line should not be blank");
    String[] splittedLine = line.split(SEPARATOR);
    if(splittedLine.length == 2) {
      coordinate = Coordinate.from(Integer.valueOf(splittedLine[0]), Integer.valueOf(splittedLine[1]));
    } else {
      throw new IllegalArgumentException("error parsing coordinate for lawn " + line);
    }
  }

  /**
   * @return Upper right corner's coordinate
   */
  public Coordinate getUpperRightCoordinate() {
    return coordinate;
  }
}
