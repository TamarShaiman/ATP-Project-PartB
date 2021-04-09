package algorithms.search;

import java.util.ArrayList;

/**
 * ISearchable interface in order to identify a problem.
 * Each problem has 3 main variables in order to be able to solve it: start position, goal position and list of Astate which represent the successors of each Astate
 */
public interface ISearchable {
    AState getStartState();
    AState getGoalState();
    ArrayList<AState> getAllSuccessors(AState state);
}

