package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.HashSet;

public class SearchableMaze implements ISearchable {
    AState startState;
    AState endState;
    private Maze maze;

   // private HashSet<AState> visitedPosition;

    public SearchableMaze(Maze maze) {
        this.startState = new MazeState(maze.getStartPosition());
        this.endState = new MazeState(maze.getGoalPosition());
        this.maze = maze;
    }

    @Override
    public AState getStartState() {
        return this.startState;
    }

    @Override
    public AState getEndState() {
        return this.endState;
    }
/*
    @Override
    public int[][] getMazeArray(AState state) {
        return this.mazeArray;
    }
*/

    @Override

    //need to implements
    public ArrayList<AState> getAllPossibleState(AState state) {

        ArrayList<AState> possibleState = new ArrayList<>();
        int [][] mazeArray = maze.getMazeArray();
        int rowPosition = ((MazeState)state).getPosition().getRowIndex();
        int columnPosition = ((MazeState)state).getPosition().getColumnIndex();


        //go down
        if (canGoTo(rowPosition+1,columnPosition)){
            MazeState nextStateDown = new MazeState(new Position(rowPosition + 1, columnPosition));
            nextStateDown.setCost(10);
            possibleState.add(nextStateDown);
        }




        //go up
        if (canGoTo(rowPosition-1,columnPosition)){
            MazeState nextStateUp = new MazeState(new Position(rowPosition - 1, columnPosition));
            nextStateUp.setCost(10);
            possibleState.add(nextStateUp);
        }



        //go right
        if (canGoTo(rowPosition,columnPosition+1)){
            MazeState nextStateRight = new MazeState(new Position(rowPosition , columnPosition+1));
            nextStateRight.setCost(10);
            possibleState.add(nextStateRight);
        }


        //go left
        if (canGoTo(rowPosition,columnPosition-1)){
            MazeState nextStateLeft = new MazeState(new Position(rowPosition , columnPosition-1));
            nextStateLeft.setCost(10);
            possibleState.add(nextStateLeft);
        }

        //go diagonal

        //go left-Down
        if (canGoTo(rowPosition+1,columnPosition-1)){
            if (canGoTo(rowPosition+1,columnPosition) || canGoTo(rowPosition,columnPosition-1)){
                MazeState nextStateLeftDown = new MazeState(new Position(rowPosition+1 , columnPosition-1));
                nextStateLeftDown.setCost(15);
                possibleState.add(nextStateLeftDown);
            }
        }

        //go right-Down
        if (canGoTo(rowPosition+1,columnPosition+1)){
            if (canGoTo(rowPosition+1,columnPosition) || canGoTo(rowPosition, columnPosition+1)){
                MazeState nextStateRightDown = new MazeState(new Position(rowPosition+1 , columnPosition+1));
                nextStateRightDown.setCost(15);
                possibleState.add(nextStateRightDown);
            }
        }

        //go left - up
        if (canGoTo(rowPosition-1,columnPosition-1)){
            if (canGoTo(rowPosition-1,columnPosition) || canGoTo(rowPosition,columnPosition-1)){
                MazeState nextStateLeftUp = new MazeState(new Position(rowPosition-1 , columnPosition-1));
                nextStateLeftUp.setCost(15);
                possibleState.add(nextStateLeftUp);
            }
        }

        //go right - up
        if (canGoTo(rowPosition-1,columnPosition+1)){
            if (canGoTo(rowPosition-1,columnPosition) || canGoTo(rowPosition, columnPosition+1)){
                MazeState nextStateRightUp = new MazeState(new Position(rowPosition-1 , columnPosition+1));
                nextStateRightUp.setCost(15);
                possibleState.add(nextStateRightUp);
            }
        }


        return possibleState;
    }



    private boolean canGoTo(int row , int column){
        int [][] mazeArray = maze.getMazeArray();

        if (row < 0 || column < 0 || row >= mazeArray.length || column >= mazeArray[0].length){
            return false;
        }
        if (mazeArray[row][column]==0) {
            /*
            MazeState state = new MazeState(new Position(row, column));
            if (!(visitedPosition.contains(state))) {
                    return true;
            }
            */
            return true;
        }
        return false;





    }
}

