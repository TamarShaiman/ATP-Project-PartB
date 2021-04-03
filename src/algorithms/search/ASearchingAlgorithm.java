package algorithms.search;

import java.util.ArrayList;
import java.util.PriorityQueue;

public  abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    //protected PriorityQueue<AState> openList;
    private int visitedNodes;

    public String getName() {
        return this.getClass().getName();
    }

    public Solution solve(ISearchable domain){
        ArrayList<AState> solutionPath = new ArrayList<AState>();
        AState currState = search(domain);
        AState startState = domain.getStartState();
        while (!currState.equals(startState)){
            solutionPath.add(currState);
            currState = currState.getCameFrom();
        }
        Solution solution = new Solution();
        solution.setSolutionPath(solutionPath);
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
    public int getNumberOfVisitedNodes() {
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
