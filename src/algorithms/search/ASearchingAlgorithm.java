package algorithms.search;

import java.util.PriorityQueue;

public  abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    protected PriorityQueue<AState> openList;
    private int visitedNodes;

    public ASearchingAlgorithm() {
        this.openList = new PriorityQueue<AState>();
        this.visitedNodes = 0;
    }

    protected AState popOpenList(){
        this.visitedNodes++;
        return this.openList.poll();
    }

    @Override
    public int getNumberOfVisitedNodes() {
        return 0;
    }

    @Override
    public AState search(ISearchable s) {
        return null;
    }
}
