package algorithms.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public  abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    //protected PriorityQueue<AState> openList;
    private int visitedNodes;
    //protected Object dataStructure;

    public String getName() {
        return this.getClass().getSimpleName();
    }

    public Solution solve(ISearchable domain) {
        //domain.resetProblem();
        ArrayList<AState> solutionPath = new ArrayList<AState>();
        AState currState = search(domain);
        AState startState = domain.getStartState();
        while (!currState.equals(startState)) {
            solutionPath.add(currState);
            currState = currState.getCameFrom();
        }
        Solution solution = new Solution();
        Collections.reverse(solutionPath);
        solution.setSolutionPath(solutionPath);
        //domain.setSolution(solution); //TODO delete after tests
        return solution;
        }


    public ASearchingAlgorithm() {
        //this.openList = new PriorityQueue<AState>();
        this.visitedNodes = 0;
    }

/*    protected AState popOpenList(){
        this.visitedNodes++;
        return this.openList.poll();
    }*/

    @Override
    public int getNumberOfNodesEvaluated() {
        return visitedNodes;
    }

    @Override
    public AState search(ISearchable s) {
        return null;
    }

    protected void increaseVisitedNodes(){
        this.visitedNodes++;
    }

}
