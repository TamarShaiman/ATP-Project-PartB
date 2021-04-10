package algorithms.search;

import java.util.*;

/**
 * abstract class to manage a common search method for searching algorithms that similar to BFS
 * the class has 2 fields: dataStructure - which implemented by queue and HashSet of visited nodes.
 */
public abstract class ABFSBasedSearchingAlgorithm extends ASearchingAlgorithm {
    protected Queue<AState> dataStructure;
    HashSet<AState> visitedNodes;

    protected int calcCost(AState state){return 0;}

    /**
     * @param1 - searchable problem that contains Start & Goal positions with list of states.
     * search method on nodes using Breath search using reference to the cost of the step.
     * returns the goalPosition or null
     */
    @Override
    public AState search(ISearchable s) {
        visitedNodes = new HashSet<>();
        AState start = s.getStartState();
        AState goal = s.getGoalState();
        start.setCameFrom(start); //cameFrom indicates that the node was discovered, so in the Root we manipulate labeling root as discovered
        visitedNodes.add(start);
        dataStructure.add(start);
        AState currNode = null;
        while (!dataStructure.isEmpty()) {
            currNode = dataStructure.poll();
            if (currNode.equals(goal)) {
                return currNode;
            } else {
                ArrayList<AState> successorsList = s.getAllSuccessors(currNode);
                if (successorsList != null) {
                    for (int i = 0; i < successorsList.size(); i++) {
                        AState nextNode = successorsList.get(i);
                        if (!visitedNodes.contains(nextNode) && nextNode.getCameFrom() == null) { //nextNode was not discovered
                            nextNode.setCameFrom(currNode);
                            this.increaseVisitedNodes();
                            dataStructure.add(nextNode);
                            visitedNodes.add(nextNode);
                        }
                    }
                }
            }
        }
        return currNode;
    }
}
