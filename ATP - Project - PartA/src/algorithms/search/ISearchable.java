package algorithms.search;


import java.util.ArrayList;

public interface ISearchable {

    AState getStartState();
    AState getEndState();

    //int [][] getMazeArray (AState state);

    public ArrayList<AState> getAllPossibleState (AState state);
}
