package algorithms.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends ABFSBasedSearchingAlgorithm{

    public BreadthFirstSearch() {
        super();
        Queue<AState> queue = new LinkedList<>();
        this.dataStructure = queue;
    }

}
