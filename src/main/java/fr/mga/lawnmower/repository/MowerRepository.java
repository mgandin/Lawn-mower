package fr.mga.lawnmower.repository;

import fr.mga.lawnmower.domain.Coordinate;
import fr.mga.lawnmower.domain.Mower;
import fr.mga.lawnmower.domain.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Parse a list of String and retrieve Mowers at their initials positions
 */
public class MowerRepository {

  public static final String SEPARATOR = " ";
  private final List<String> lines;

  /**
   * @param lines to parse
   */
  public MowerRepository(List<String> lines) {
    this.lines = lines;
    checkNotNull(lines);
    checkArgument(lines.size() > 0, "lines should not be empty");
  }

  /**
   * Mowers at their initials positions
   * @return
   */
  public Map<Integer, Mower> findAll() {
    Map<Integer, Mower> mowers = new HashMap<>();
    Integer mowerIds = 0;
    for(String line : lines) {
      String[] mowerAsString = line.split(SEPARATOR);
      Coordinate coordinate = new Coordinate(Integer.valueOf(mowerAsString[0]), Integer.valueOf(mowerAsString[1]));
      mowers.put(++mowerIds,new Mower(Position.fromLabel(mowerAsString[2]), coordinate));
    }
    return mowers;
  }
}
