package algorithms.search;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {

    private int numberOfNodesEvaluated; //number of visited nodes


    public ASearchingAlgorithm() {

        this.numberOfNodesEvaluated = 0;
    }
    public abstract Solution solve(ISearchable maze);
    public abstract String getName();

    public int getNumberOfNodesEvaluated(){
        return numberOfNodesEvaluated;
    }

    public void setNumberOfNodesEvaluated(int numberOfNodesEvaluated) {
        this.numberOfNodesEvaluated = numberOfNodesEvaluated;
    }
}
