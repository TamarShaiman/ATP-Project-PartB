package algorithms.search;

public interface ISearchingAlgorithm {
    AState search(ISearchable s);
    int getNumberOfNodesEvaluated();
    String getName();
    Solution solve(ISearchable domain);
    //int getNumberOfNodesEvaluated();
}
