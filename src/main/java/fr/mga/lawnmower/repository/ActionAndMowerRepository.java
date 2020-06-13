package fr.mga.lawnmower.repository;

import com.google.common.hash.Hashing;
import fr.mga.lawnmower.domain.*;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Parse line for Mowers and his actions
 */
public class ActionAndMowerRepository {

  private static final String SEPARATOR_MOWER = " ";
  private static final String SEPARATOR_ACTION = "";
  private int idActions = 0;

  private Map<String, Mower> mowers = new HashMap<>();
  PriorityQueue<Action> actions = new PriorityQueue<>();

  /**
   * @param mowerAsString line representing a Mower to parse
   * @param commandsAsString line representing a list of command to parse
   */
  public void addLines(String mowerAsString, String commandsAsString) {
    checkNotNull(mowerAsString);
    checkNotNull(commandsAsString);
    String id = hash(mowerAsString);
    String[] mowerParams = mowerAsString.split(SEPARATOR_MOWER);
    if(mowerParams.length == 3) {
      Coordinate coordinate = new Coordinate(Integer.valueOf(mowerParams[0]), Integer.valueOf(mowerParams[1]));
      mowers.put(id,new Mower(Position.fromLabel(mowerParams[2]), coordinate));
    } else {
      throw new IllegalArgumentException("error parsing mower " + mowerAsString);
    }

    if(!commandsAsString.isBlank() || !commandsAsString.isEmpty()) {
      String[] actionParam = commandsAsString.split(SEPARATOR_ACTION);
      for(String param : actionParam) {
        actions.add(new Action(++idActions, id, Command.fromLabel(param)));
      }
    } else {
      throw new IllegalArgumentException("error parsing empty command");
    }
  }

  public Map<String, Mower> findMowers() {
    return mowers;
  }

  public PriorityQueue<Action> findActions() {
    return actions;
  }

  private static String hash(String toHash) {
    return Hashing.sha256().hashString(toHash, StandardCharsets.UTF_8).toString();
  }
}
