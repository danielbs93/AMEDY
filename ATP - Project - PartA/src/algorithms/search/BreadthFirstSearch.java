package algorithms.search;

import algorithms.mazeGenerators.Maze;

import java.util.*;

/**
 * This class is for breadth First Search algorithm
 * Extends from ASearchingAlgorithm class
 */
public class BreadthFirstSearch extends ASearchingAlgorithm {

    protected HashSet<AState> visitedPosition;
    protected Queue<AState> statesQueue;

    /**
     * Default constructor
     */
    public BreadthFirstSearch() {
        visitedPosition = new HashSet<>();
        statesQueue = new LinkedList<>();
    }

    /**
     * this function is solving the maze by this algorithm
     * @param maze to be solved
     * @return Solution - the solution
     */
    public Solution solve(ISearchable maze){

        statesQueue.add(maze.getStartState());
        visitedPosition.add( maze.getStartState());

        while (!statesQueue.isEmpty()){
            AState currentState = statesQueue.poll(); ///pop a state from the queue

            ///////check if current state is the goal state/////////
            if (currentState.equals(maze.getEndState())){
                Solution solution = new Solution(currentState);
                //setNumberOfNodesEvaluated(solution.getSolutionPath().size());
                return solution;
            }
            ArrayList<AState> possibleState = maze.getAllPossibleState(currentState);
          // if (possibleState.size() > 0){
                setNumberOfNodesEvaluated(getNumberOfNodesEvaluated()+1);
          //  }
            for (int i=0 ; i < possibleState.size() ; i++){
                if (!visitedPosition.contains(possibleState.get(i))){
                    possibleState.get(i).setCameFrom(currentState);
                    statesQueue.add(possibleState.get(i));
                    visitedPosition.add( possibleState.get(i)); ///the state is visited now
                }
            }


        }


        return null;
    }

    @Override
    public String getName() {
        return "BFS";
    }
}
