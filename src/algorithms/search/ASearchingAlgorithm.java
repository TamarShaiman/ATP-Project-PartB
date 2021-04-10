package algorithms.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Abstract class that implements ISearching Algorithms
 * created shared solve method because all the algorithms shared the same pattern to identify the solution path
 */

public  abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    private int visitedNodes;
    /**
     * default constructor set visitedNodes to 0.
     */
    public ASearchingAlgorithm() {
        this.visitedNodes = 0;
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }

    public Solution solve(ISearchable domain) {
        /**
         * identify the solution path using cameFrom field  - from goal position on each Astate until reaching start Position
         */
        ArrayList<AState> solutionPath = new ArrayList<AState>();
        AState currState = search(domain);
        AState startState = domain.getStartState();
        while (!currState.equals(startState)) {
            solutionPath.add(currState);
            currState = currState.getCameFrom();
        }
        solutionPath.add(startState);
        Solution solution = new Solution();
        Collections.reverse(solutionPath);
        solution.setSolutionPath(solutionPath);
        return solution;
        }

    @Override
    public int getNumberOfNodesEvaluated() {
        return visitedNodes;
    }

    protected void increaseVisitedNodes(){
        this.visitedNodes++;
    }

}
