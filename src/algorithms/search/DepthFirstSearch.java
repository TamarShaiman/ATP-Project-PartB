package algorithms.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm{

/*    @Override
    public String getName() {
        return this.getClass().getName();
    }*/

    public DepthFirstSearch() {

    }

    @Override
    public AState search(ISearchable s) {
        Stack<AState> stack = new Stack<>();
        AState start = s.getStartState();
        AState goal = s.getGoalState();
        stack.push(start);
        AState currNode = start;
        currNode.setCameFrom(start);
        this.increaseVisitedNodes();
        while (!stack.isEmpty()){
            AState nextNode = stack.pop();
            if (currNode.equals(goal)){
                return currNode;
            }
            if (nextNode.getCameFrom() == null){
                nextNode.setCameFrom(currNode);
                this.increaseVisitedNodes();
                ArrayList<AState> successorsList = s.getAllSuccessors(nextNode);
                for (AState successor : successorsList) {
                    stack.push(successor);
                }
                currNode = nextNode;
            }
        }
        return null;
    }
}
