package algorithms.search;

/**
 * ISearchingAlgorithm interface created in order to define methods for searching algorithms
 */
public interface ISearchingAlgorithm {

    AState search(ISearchable s);
    int getNumberOfNodesEvaluated();
    String getName();
    Solution solve(ISearchable domain);
}
