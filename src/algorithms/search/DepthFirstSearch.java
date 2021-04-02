package algorithms.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm{
    private int NumberOfVisitedNodes = 0;
    @Override
    public int getNumberOfVisitedNodes() {
        return NumberOfVisitedNodes;
    }

    @Override
    public AState search(ISearchable s) {
        Stack<AState> stack = new Stack<>();
        AState start = s.getStartState();
        AState goal = s.getGoalState();
        stack.push(start);
        AState currNode = start;
        currNode.setCameFrom(start);
        NumberOfVisitedNodes++;
        while (!stack.isEmpty()){
            AState nextNode = stack.pop();
            if (currNode.equals(goal)){
                return currNode;
            }
            if (nextNode.getCameFrom() == null){
                nextNode.setCameFrom(currNode);
                NumberOfVisitedNodes++;
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

/*    procedure DFS_iterative(G, v) is
    let S be a
        S.push(iterator of G.adjacentEdges(v))
        while S is not empty do
            if S.peek().hasNext() then

                  w = S.peek().next()
                  if w is not labeled as discovered then
                          label w as discovered
                          S.push(iterator of G.adjacentEdges(w))
            else
                  S.pop() */
/*
Stack<AState> stack = new Stack<>();
    AState start = s.getStartState();
    AState goal = s.getGoalState();
    ArrayList<AState> successorsList = s.getAllSuccessors(start);
        for (AState successor : successorsList){
                stack.push(successor);
                }

                while (!stack.isEmpty()){
                successorsList = s.getAllSuccessors(stack.peek());
                if (successorsList.size() != 0 ){ //we will search on Node only if it has successors
                for (AState successor : successorsList){
                if (successor.getCameFrom() == null){
                successor.setCameFrom(stack.peek());
                stack.push(successor);

                }
                }
                }
                else{

                }
                }*/
