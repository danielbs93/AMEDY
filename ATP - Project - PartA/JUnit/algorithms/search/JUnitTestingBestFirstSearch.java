package algorithms.search;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JUnitTestingBestFirstSearch {



    @Test
    void getName () {

        BestFirstSearch best = new BestFirstSearch();
        assertEquals("Best First Search" , best.getName());
    }


    void solve () {
        BestFirstSearch best = new BestFirstSearch();
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(1, 1);
        SearchableMaze searchableMaze = new SearchableMaze(maze);

        Solution solution = best.solve(searchableMaze);

        assertFalse(solution.getSolutionPath().size() < 0);
    }
}