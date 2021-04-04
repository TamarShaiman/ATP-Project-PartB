package algorithms.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm{
    private Stack<AState> dataStructure;

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
//        Stack<AState> stack = new Stack<>();
        AState start = s.getStartState();
        AState goal = s.getGoalState();
        dataStructure.push(start);
        AState currNode = start;
        currNode.setCameFrom(start);
        this.increaseVisitedNodes();
        while (!dataStructure.isEmpty()){
            AState nextNode = dataStructure.pop();
            if (currNode.equals(goal)){
                return currNode;
            }
            if (nextNode.getCameFrom() == null){
                nextNode.setCameFrom(currNode);
                this.increaseVisitedNodes();
                ArrayList<AState> successorsList = s.getAllSuccessors(nextNode);
                for (AState successor : successorsList) {
                    dataStructure.push(successor);
                }
                currNode = nextNode;
            }
        }
        return null;
    }
}
