package fr.mga.lawnmower.parser;

import fr.mga.lawnmower.domain.Action;
import fr.mga.lawnmower.domain.Coordinate;
import fr.mga.lawnmower.domain.Lawn;
import fr.mga.lawnmower.domain.Mower;
import fr.mga.lawnmower.repository.ActionAndMowerRepository;
import fr.mga.lawnmower.repository.LawnRepository;

import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Parse the content of the file line per line
 * It gives the Lawn ready to mow
 */
public class LawnParser {

  private final LawnRepository lawnRepository;

  private final ActionAndMowerRepository actionRepository;

  public LawnParser(LawnRepository lawnRepository, ActionAndMowerRepository actionRepository) {
    this.lawnRepository = lawnRepository;
    this.actionRepository = actionRepository;
  }

  public void parseHeader(String header) {
    this.lawnRepository.parse(header);
  }

  /**
   * @param toParse each lines of the file to parse
   */
  public void parse(List<String> toParse) {
    for (int i = 0; i < toParse.size(); i++) {
      if(i % 2 == 0) {
        String mower = toParse.get(i);
        String commands = toParse.get(i+1);
        this.actionRepository.addLines(mower, commands);
      }
    }
  }

  /**
   * @return the Lawn ready to mow
   */
  public Lawn lawn() {
    return new Lawn(new Coordinate(), lawnRepository.getUpperRightCoordinate(),
      mowers(),
      actions());
  }

  private Map<String, Mower> mowers() {
    return actionRepository.findMowers();
  }

  private PriorityQueue<Action> actions() {
    return actionRepository.findActions();
  }
}
