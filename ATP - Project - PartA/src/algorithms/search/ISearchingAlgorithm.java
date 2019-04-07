package algorithms.search;

public interface ISearchingAlgorithm {

     int getNumberOfNodesEvaluated();
     Solution solve(ISearchable maze);
     String getName();
}
