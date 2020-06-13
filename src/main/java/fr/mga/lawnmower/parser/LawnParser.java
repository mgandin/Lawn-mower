package fr.mga.lawnmower.parser;

import fr.mga.lawnmower.domain.Action;
import fr.mga.lawnmower.domain.Coordinate;
import fr.mga.lawnmower.domain.Lawn;
import fr.mga.lawnmower.domain.Mower;
import fr.mga.lawnmower.repository.ActionRepository;
import fr.mga.lawnmower.repository.LawnRepository;
import fr.mga.lawnmower.repository.MowerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Parse the content of the file line per line
 * It gives the Lawn ready to mow
 */
public class LawnParser {

  private final LawnRepository lawnRepository;
  private final ActionRepository actionRepository;
  private final MowerRepository mowerRepository;

  /**
   * @param toParse each lines of the file to parse
   */
  public LawnParser(List<String> toParse) {
    this.lawnRepository = new LawnRepository(toParse.get(0));
    List<String> actionsAsString = new ArrayList<>();
    List<String> mowersAsString = new ArrayList<>();
    int i = 0;
    for (String line : toParse) {
      if(i % 2 == 0 && i != 0) {
        actionsAsString.add(line);
      } else if(i != 0) {
        mowersAsString.add(line);
      }
      i++;
    }

    actionRepository = new ActionRepository(actionsAsString);
    mowerRepository = new MowerRepository(mowersAsString);
  }

  /**
   * @return the Lawn ready to mow
   */
  public Lawn lawn() {
    return new Lawn(new Coordinate(), lawnRepository.getUpperRightCoordinate(),
      mowers(),
      actions());
  }

  private Map<Integer, Mower> mowers() {
    return mowerRepository.findAll();
  }

  private PriorityQueue<Action> actions() {
    return actionRepository.findAll();
  }
}
