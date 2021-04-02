package algorithms.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends ASearchingAlgorithm{
    private int NumberOfVisitedNodes = 0;
    @Override
    public int getNumberOfVisitedNodes() {
        return NumberOfVisitedNodes;
    }

    @Override
    public AState search(ISearchable s) {
        Queue<AState> queue = new LinkedList<>();
        AState start = s.getStartState();
        AState goal = s.getGoalState();
        start.setCameFrom(start); //cameFrom indicates that the node was discovered, so in the Root we manipulate labeling root as discovered
        NumberOfVisitedNodes++;
        queue.add(start);
        AState currNode = queue.poll();
        while (!queue.isEmpty()){
            if (currNode.equals(goal)){
                return currNode;
            }
            else{
                ArrayList<AState> successorsList = s.getAllSuccessors(currNode);
                for (int i = 0; i < successorsList.size(); i++) {
                    AState nextNode = successorsList.get(i);
                    if (nextNode.getCameFrom() == null) { //nextNode was not discovered
                        nextNode.setCameFrom(currNode);
                        NumberOfVisitedNodes++;
                        queue.add(nextNode);
                    }
                }
            }
            currNode = queue.poll();
        }
        return currNode;
    }
}
