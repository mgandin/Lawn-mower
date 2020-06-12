package fr.mga.lawnmower.domain;

import java.util.*;

import static java.util.stream.Collectors.toSet;

/**
 * Domain object for Lawn, it has an upper right corner and a bottom left corner coordinates to
 * calculate where Mower will mow.
 *
 * It has a Map of mower to store the ID of a mower and the last Mower's move. This Map is
 * initialized with each starting position of mowers.
 *
 * It has a Queue of actions for each mower.
 *
 * When the Lawn is mowed, it pools an action from the queue, it retrieve the last Mower's move, it
 * verify if the mower can make is next move (ie staying int the map of the lawn and not bumping
 * another mower) and it execute a command which give the next Mower, then we update the map of mowers
 */
public class Lawn {

  private final Coordinate upperRightCorner;
  private final Coordinate bottomLeftCorner;
  private final Map<Integer, Mower> mowers;
  private final PriorityQueue<Action> actions;

  /**
   * @param upperRightCorner upper right corner Coordinate of the Lawn
   * @param bottomLeftCorner bottom left corner Coordinate of the Lawn
   * @param mowers Map of initiale position of mowers
   * @param actions Queue of actions for mowers
   */
  public Lawn(Coordinate upperRightCorner, Coordinate bottomLeftCorner, Map<Integer, Mower> mowers,
    PriorityQueue<Action> actions) {
    this.upperRightCorner = upperRightCorner;
    this.bottomLeftCorner = bottomLeftCorner;
    this.mowers = mowers;
    this.actions = actions;
  }

  /**
   * It mow the Lawn
   * @return Mowers last positions after the lawn has been mowed
   */
  public List<Mower> mow() {
    while (actions.iterator().hasNext()) {
      Action action = actions.poll();
      Mower mower = mowers.get(action.getMowerId());
      Command command = action.getCommand();
      if (canMove(command, mower, action.getMowerId())) {
        Mower newMower = mower.move(command);
        mowers.put(action.getMowerId(), newMower);
      }
    }
    return new ArrayList<>(mowers.values());
  }

  /**
   * Verify if a Mower can make his next move according to other Mowers and
   * the size of the Lawn
   * @param command command to make Mower's next move
   * @param mower Mower trying to make his next move
   * @param mowerId id of mower (for filtering other mowers)
   * @return
   */
  private boolean canMove(Command command, Mower mower, Integer mowerId) {
    Set<Integer> otherMowerIds = mowers.keySet()
      .stream()
      .filter(id -> !id.equals(mowerId))
      .collect(toSet());
    boolean canBumpIntoMower = false;
    for(Integer otherMowerId : otherMowerIds) {
      Mower otherMower = mowers.get(otherMowerId);
      if(!mower.canMove(command, otherMower)) {
        canBumpIntoMower = true;
      }
    }
    return !canBumpIntoMower && mower.canMove(command, bottomLeftCorner, upperRightCorner);
  }
}
