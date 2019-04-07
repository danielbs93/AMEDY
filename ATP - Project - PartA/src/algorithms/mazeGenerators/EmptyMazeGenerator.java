package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int row, int column) {

        Maze newMaze =  new Maze(row , column);

        int [][]arr = newMaze.getMazeArray();

        for(int i=0; i<arr.length ; i++){
            for(int j=0 ; j<arr[0].length ; j++){
                arr[i][j]=0;
            }
        }
        newMaze.setMazeArray(arr);

        newMaze.setStartPosition(new Position(0,0));

        return newMaze;
    }
}
