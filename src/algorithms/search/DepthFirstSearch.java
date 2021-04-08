package algorithms.search;

import java.util.*;

public class DepthFirstSearch extends ASearchingAlgorithm{
    private Stack<AState> dataStructure;
    HashSet<AState> visitedNodes;


/*    @Override
    public String getName() {
        return this.getClass().getName();
    }*/

    public DepthFirstSearch() {
        super();
        Stack<AState> stack = new Stack<>();
        this.dataStructure = stack;
    }

    @Override
    public AState search(ISearchable s) {
        visitedNodes = new HashSet<>();
        Stack<AState> junctionStack = new Stack<>();
        AState start = s.getStartState();
        AState goal = s.getGoalState();
        AState currNode;
        dataStructure.push(start);
        while (!this.dataStructure.isEmpty()) {
            currNode = dataStructure.pop();
            if (currNode.equals(goal)) {
                //currNode.setCameFrom(fatherNode);
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
