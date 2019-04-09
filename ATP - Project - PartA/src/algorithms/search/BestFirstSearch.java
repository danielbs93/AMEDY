package algorithms.search;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * This class is for best First Search algorithm
 * Extends from BreadthFirstSearch class
 */
public class BestFirstSearch extends BreadthFirstSearch {


    /**
     * Default constructor
     *
     */
    public BestFirstSearch() {
        visitedPosition = new HashSet<>();
        statesQueue = new PriorityQueue<>(AState::compareTo);
    }

    /**
     * Getter for algorithm name
     * @return String - "Best First Search"
     *
     */

    public String getName() {
        return "Best First Search";
    }


}
