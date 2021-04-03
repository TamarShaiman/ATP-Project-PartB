package algorithms.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public abstract class ABFSBasedSearchingAlgorithm extends ASearchingAlgorithm {
    private int NumberOfVisitedNodes = 0;
    protected Queue<AState> dataStructure;

    protected void setDataStructure() {}

    protected int calcCost(AState state){return 0;}

    @Override
    public int getNumberOfVisitedNodes() {
        return NumberOfVisitedNodes;
    }

    @Override
    public AState search(ISearchable s) {
        AState start = s.getStartState();
        AState goal = s.getGoalState();
        start.setCameFrom(start); //cameFrom indicates that the node was discovered, so in the Root we manipulate labeling root as discovered
        this.increaseVisitedNodes();
        dataStructure.add(start);
        AState currNode = dataStructure.poll();
        while (!dataStructure.isEmpty()) {
            if (currNode.equals(goal)) {
                return currNode;
            } else {
                ArrayList<AState> successorsList = s.getAllSuccessors(currNode);
                for (int i = 0; i < successorsList.size(); i++) {
                    AState nextNode = successorsList.get(i);
                    if (nextNode.getCameFrom() == null) { //nextNode was not discovered
                        nextNode.setCameFrom(currNode);
                        this.increaseVisitedNodes();
                        dataStructure.add(nextNode);
                    }
                }
            }
            currNode = dataStructure.poll();
        }
        return currNode;
    }
}
