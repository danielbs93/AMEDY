package algorithms.search;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch {


    public BestFirstSearch() {
        visitedPosition = new HashSet<>();
        statesQueue = new PriorityQueue<>();
    }

    @Override
    public String getName() {
        return "Best First Search";
    }


}
