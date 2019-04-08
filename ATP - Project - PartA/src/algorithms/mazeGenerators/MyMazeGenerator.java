package algorithms.mazeGenerators;

//import javafx.scene.control.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;


public class MyMazeGenerator extends AMazeGenerator {
    @Override
    public Maze generate(int row, int column) {


        if (row < 2  || column <2 ){
            row = 3;
            column = 3;
        }

        Maze myMaze = new Maze(row, column);

        int rowStart;
        int columnStart;
        int rowEnd;
        int columnEnd;



        int[][] mazeArray = myMaze.getMazeArray();

        for (int i = 0; i < mazeArray.length; i = i + 2) {  //even cells
            for (int j = 0; j < mazeArray[0].length; j = j + 2) {
                mazeArray[i][j] = -1;
            }
        }
        for (int i = 1; i < mazeArray.length; i = i + 2) {  //odd cells
            for (int j = 1; j < mazeArray[0].length; j = j + 2) {
                mazeArray[i][j] = 1;
            }
        }

        for (int i = 0; i < mazeArray.length; i = i + 2) {  //even walls
            for (int j = 1; j < mazeArray[0].length; j = j + 2) {
                mazeArray[i][j] = 1;
            }
        }
        for (int i = 1; i < mazeArray.length; i = i + 2) {  //odd walls
            for (int j = 0; j < mazeArray[0].length; j = j + 2) {
                mazeArray[i][j] = 1;
            }
        }

        myMaze.setMazeArray(mazeArray);

        //init

        rowStart = (int) (Math.random() * row);
        columnStart = (int) (Math.random() * column);
        /////random cell
        while (mazeArray[rowStart][columnStart] != -1) {
            rowStart = (int) (Math.random() * row);
            columnStart = (int) (Math.random() * column);
        }
        myMaze.setStartPosition(new Position(rowStart, columnStart));
        mazeArray[rowStart][columnStart] = 0;


        if(myMaze.getRow()==2 && myMaze.getColumn()==2){

            for (int i = 0; i < mazeArray.length; i  ++) {  //odd walls
                for (int j = 0; j < mazeArray[0].length; j  ++) {
                    mazeArray[i][j] = 1;
                }
            }

            rowStart = (int) (Math.random() * row);
            columnStart = (int) (Math.random() * column);
            rowEnd = (int) (Math.random() * row);
            columnEnd = (int) (Math.random() * column);
            mazeArray[rowStart][columnStart] = 0;
            mazeArray[rowEnd][columnEnd] = 0;


        }
        //create the stack
        Stack<Position> visitedCells = new Stack<>();

        Position startPosition = myMaze.getStartPosition();
        //insert the startPosition into the stack
        visitedCells.push(startPosition);


        //if all the neighborsCells are visited
        ArrayList<Position> neighborsCells=findNighborsCells(mazeArray , startPosition);
        Position nextPosition=null;



        while (!visitedCells.isEmpty()){///visited stack is not empty
            if(neighborsCells.isEmpty()){///all of the the neighbors are visited

                Position lastPosition = visitedCells.pop(); ///pop the next visited cell
                //check next position neighbors
                neighborsCells=findNighborsCells(mazeArray , lastPosition);


                if(!neighborsCells.isEmpty()) {
                    nextPosition = randomizeNighborsCells(neighborsCells);
                    brakingTheWall(mazeArray, lastPosition, nextPosition);

                    visitedCells.push(nextPosition);
                    mazeArray[nextPosition.getRowIndex()][nextPosition.getColumnIndex()] = 0;
                    //neighborsCells.clear();
                    neighborsCells = findNighborsCells(mazeArray, nextPosition);//find the NighborsCells of the new cell
                }


            }else{
                nextPosition=randomizeNighborsCells(neighborsCells);
                brakingTheWall(mazeArray,visitedCells.peek(),nextPosition);
                visitedCells.push(nextPosition);
                mazeArray[nextPosition.getRowIndex()][nextPosition.getColumnIndex()]=0;
                //neighborsCells.clear();
                neighborsCells=findNighborsCells(mazeArray , nextPosition);//find the NighborsCells of the new cell
            }
        }

        myMaze.setMazeArray(mazeArray);

        ////***set end Position*****
        rowEnd = (int) (Math.random() * row);
        columnEnd = (int) (Math.random() * column);
        /////random cell
        //start position and end position are diffrent
        while ((mazeArray[rowEnd][columnEnd] !=0) || (rowEnd==rowStart && columnEnd==columnStart)) {
            rowEnd = (int) (Math.random() * row);
            columnEnd = (int) (Math.random() * column);
        }
        myMaze.setEndPosition(new Position(rowEnd, columnEnd));



        //brake the frame
        for(int i=0 ; i<mazeArray.length ; i++){
            int j=mazeArray[0].length-1 ;
            if(mazeArray[i][j]==1) {
                mazeArray[i][j] = (int) (Math.round(Math.random()));
            }
        }
        for(int j=0 ; j<mazeArray[0].length ; j++){
            int i=mazeArray.length-1 ;
            if(mazeArray[i][j]==1) {
                mazeArray[i][j] = (int) (Math.round(Math.random()));
            }
        }


        return myMaze;
    }

    private void brakingTheWall( int[][]arr, Position p1 , Position p2){
        if(p1.getRowIndex()-2 == p2.getRowIndex() && p1.getColumnIndex()==p2.getColumnIndex()){
            arr[p1.getRowIndex()-1][p1.getColumnIndex()]=0;
        }
        if(p1.getRowIndex()+2 == p2.getRowIndex() && p1.getColumnIndex()==p2.getColumnIndex() ){
            arr[p1.getRowIndex()+1][p1.getColumnIndex()]=0;
        }
        if(p1.getRowIndex() == p2.getRowIndex() && p1.getColumnIndex()-2 == p2.getColumnIndex()){
            arr[p1.getRowIndex()][p1.getColumnIndex()-1]=0;
        }
        if(p1.getRowIndex() == p2.getRowIndex() && p1.getColumnIndex()+2 == p2.getColumnIndex()){
            arr[p1.getRowIndex()][p1.getColumnIndex()+1]=0;
        }


    }
    private Position randomizeNighborsCells(ArrayList<Position> neighborsCells ){
       if(neighborsCells.isEmpty()){
           return null;
       }
        int index = (int)(Math.random()*neighborsCells.size());
        return neighborsCells.get(index);
    }

    //create and return a stack of all the neighbors of a cell
    private ArrayList<Position> findNighborsCells(int[][] arr,Position position){
        ArrayList<Position> neighborsCells = new ArrayList<>();
        if(position.getRowIndex()-2>=0 ){
            if(arr[position.getRowIndex()-2][position.getColumnIndex()]==-1){
                neighborsCells.add(new Position(position.getRowIndex()-2,position.getColumnIndex()));
            }
        }
        if(position.getRowIndex()+2< arr.length ){
            if(arr[position.getRowIndex()+2][position.getColumnIndex()]==-1){
                neighborsCells.add(new Position(position.getRowIndex()+2,position.getColumnIndex()));
            }
        }
        if(position.getColumnIndex()-2>=0 ){
            if(arr[position.getRowIndex()][position.getColumnIndex()-2]==-1){
                neighborsCells.add(new Position(position.getRowIndex(),position.getColumnIndex()-2));
            }
        }
        if(position.getColumnIndex()+2 <arr[0].length ){
            if(arr[position.getRowIndex()][position.getColumnIndex()+2]==-1){
                neighborsCells.add(new Position(position.getRowIndex(),position.getColumnIndex()+2));
            }
        }

        return neighborsCells;
    }
}



