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
        Stack<AState> junctionStack = new Stack<>();
        AState start = s.getStartState();
        AState goal = s.getGoalState();
        dataStructure.push(start);
        AState currNode = start;
        AState fatherNode = start;
        this.increaseVisitedNodes();

        while (!this.dataStructure.isEmpty()) {
            currNode = dataStructure.pop();
            this.increaseVisitedNodes();
            if (currNode.equals(goal)) {
                currNode.setCameFrom(fatherNode);
                return currNode;
            }
            if (currNode.getCameFrom() == null) {
                ArrayList<AState> successorsList = s.getAllSuccessors(currNode);
                for (AState successor : successorsList) {
                    dataStructure.push(successor);
                }
                currNode.setCameFrom(fatherNode);
                if (successorsList.size() > 1) {
                    fatherNode = currNode;
                    for (int i = 0; i < successorsList.size()-1; i++) {
                        junctionStack.push(fatherNode);
                    }
                } else if (successorsList.size() == 1) {
                    fatherNode = currNode;
                } else { //successorsList.size() == 0 , no neighbors
                    fatherNode = junctionStack.pop();
                }
            }
        }
        return null;

        /*Stack<AState> junctionStack = new Stack<>();
        AState start = s.getStartState();
        AState goal = s.getGoalState();
        dataStructure.push(start);
        AState currNode = start;
        this.increaseVisitedNodes();
        while (!dataStructure.isEmpty()){
            AState nextNode = dataStructure.pop();
            if (currNode.equals(goal)){
                return currNode;
            }
            if (nextNode.getCameFrom() == null){
//                nextNode.setCameFrom(currNode);
                this.increaseVisitedNodes();
                ArrayList<AState> successorsList = s.getAllSuccessors(nextNode);
                if (successorsList.size() > 1) { junctionStack.push(nextNode); }
                for (AState successor : successorsList) {
                    successor.setCameFrom(currNode);
                    dataStructure.push(successor);
                }
                if(successorsList.size() == 0){ currNode = junctionStack.pop();}
                else{ currNode = nextNode;}
            }
        }
        return null;*/
    }

}
