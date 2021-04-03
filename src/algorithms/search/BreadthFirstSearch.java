package algorithms.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends ABFSBasedSearchingAlgorithm{

    @Override
    protected void setDataStructure() {
        Queue<AState> queue = new LinkedList<>();
        this.dataStructure = queue;
    }

}
