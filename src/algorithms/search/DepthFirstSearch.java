package algorithms.search;

import java.util.*;

public class DepthFirstSearch extends ASearchingAlgorithm{
    /**
     * DepthFirstSearch uses Stack and HashSet data structures.
     * HashSet indicate if an Astate was visit.
     */
    private Stack<AState> dataStructure;
    HashSet<AState> visitedNodes;

    public DepthFirstSearch() {
        super();
        Stack<AState> stack = new Stack<>();
        this.dataStructure = stack;
    }

    /**
     * @param s  searchable problem
     * @return goal Astate or null
     */
    @Override
    public AState search(ISearchable s) {
        visitedNodes = new HashSet<>();
        AState start = s.getStartState();
        AState goal = s.getGoalState();
        AState currNode;
        dataStructure.push(start);
        while (!this.dataStructure.isEmpty()) {
            currNode = dataStructure.pop();
            if (currNode.equals(goal)) {
                visitedNodes.add(currNode);
                return currNode;
            }
            if (!visitedNodes.contains(currNode)) {
                visitedNodes.add(currNode);
                this.increaseVisitedNodes();
                ArrayList<AState> successorsList = s.getAllSuccessors(currNode);
                for (AState successor : successorsList) {
                    if(successor.getCameFrom()== null ){
                        successor.setCameFrom(currNode);
                        dataStructure.push(successor);}

                }
            }
        }
        return null;
    }

}
