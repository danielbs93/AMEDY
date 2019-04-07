package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;


public class DepthFirstSearch extends ASearchingAlgorithm {

    private HashSet<AState> visitedPosition;
    private Stack<AState> statesStack;



    public DepthFirstSearch() {
        visitedPosition = new HashSet<>();
        statesStack = new Stack<AState>();
    }

    public Solution solve(ISearchable maze){

        statesStack.add(maze.getStartState());
        visitedPosition.add( maze.getStartState());

        while (!statesStack.isEmpty()){
            AState currentState = statesStack.pop(); ///pop a state from the queue

            ///////check if current state is the goal state/////////
            if (currentState.equals(maze.getEndState())){
                Solution solution = new Solution(currentState);
              //  setNumberOfNodesEvaluated(solution.getSolutionPath().size());
                return solution;
            }
            ArrayList<AState> possibleState = maze.getAllPossibleState(currentState);
         //   if (possibleState.size() > 0){
                setNumberOfNodesEvaluated(getNumberOfNodesEvaluated()+1);
           // }
            for (int i=0 ; i < possibleState.size() ; i++){
                if (!visitedPosition.contains(possibleState.get(i))){
                    possibleState.get(i).setCameFrom(currentState);
                    statesStack.add(possibleState.get(i));
                    visitedPosition.add( possibleState.get(i)); ///the state is visited now
                }
            }


        }


        return null;
    }


    public String getName() {

        return "DFS";
    }

}
