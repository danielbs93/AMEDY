package algorithms.search;

import java.util.ArrayList;

public class Solution {

    private  ArrayList<AState> solutionPath;


    public Solution(AState state) {

        solutionPath = makePath(state);
    }

    private ArrayList<AState> makePath (AState goalState){

        ArrayList<AState> path = new ArrayList<>();

        if (goalState == null){
            return path;
        }

        path.add(0,goalState);

        AState father = goalState.getCameFrom();

        while  (father!=null){
            path.add(0,father);
            father = father.getCameFrom();

        }
        return path;
    }

    public ArrayList<AState> getSolutionPath (){
        return solutionPath;
    }
}



