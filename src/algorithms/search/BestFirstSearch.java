package algorithms.search;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class BestFirstSearch extends ABFSBasedSearchingAlgorithm{

    @Override
    protected void setDataStructure() {
        PriorityQueue<AState> priorityQueue = new PriorityQueue<AState>(new bestComparator());
        this.dataStructure = priorityQueue;
    }



}

class bestComparator implements Comparator<AState> {
    public int compare(AState state1, AState state2) {
        if (state1.getCost() > state2.getCost()) { //TODO: double check if needs to be backwards
            return -1;
        } else if (state1.getCost() < state2.getCost()){
            return 1;
        }
        else{
            return 0;
        }
    }
}

