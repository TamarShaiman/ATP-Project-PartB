package algorithms.search;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;


public class BestFirstSearch extends ABFSBasedSearchingAlgorithm{

    /**
     * BestFirstSearch class uses min heap using PriorityQueue data structure inorder to get the minimum cost path.
     */
    public BestFirstSearch() {
        super();
        Queue<AState> queue = new PriorityQueue<>(new bestComparator());
        this.dataStructure = queue;
    }

}

/**
 * Comparator class in order to compare 2 Astate using their cost.
 * returns 1 in case the cost of state1 greater than state 2, -1 if state2 greater than stat1 and 0 if their cost step is equal/
 */
class bestComparator implements Comparator<AState> {
    public int compare(AState state1, AState state2) {
        if (state1.getCost() < state2.getCost()) { //TODO: double check if needs to be backwards
            return -1;
        } else if (state1.getCost() > state2.getCost()){
            return 1;
        }
        else{
            return 0;
        }
    }
}

