package algorithms.mazeGenerators;
/**
 * EmptyMazeGenerator inherits the abstract class AMazeGenerator
 * This class generate an empty maze.
 *This class implements the generate method. Therefore it is possible to create an instance of the class.
 */

public class EmptyMazeGenerator extends AMazeGenerator {

    /**
     * This is a method that generate a maze.
     * @param row this is the number of rows in the maze.
     * @param column this is the number of columns in the maze.
     * @return Maze. This returns the maze that was generated in the method (empty maze - all the cells with the value 0).
     */
    @Override
    public Maze generate(int row, int column) {

        //Create a new maze. Only the frame. An empty array.
        Maze newMaze =  new Maze(row , column);

        int [][]arr = newMaze.getMazeArray();

        //Lnitialize all the cells in the array to zero.
        for(int i=0; i<arr.length ; i++){
            for(int j=0 ; j<arr[0].length ; j++){
                arr[i][j]=0;
            }
        }
        //Set the initialized array
        newMaze.setMazeArray(arr);

        //Set the starting point for this maze at this random point
        int rowStart = (int) (Math.random() * row);
        int columnStart = (int) (Math.random() * column);

        newMaze.setStartPosition(new Position(rowStart, columnStart));

        //Set the starting point for this maze at this random point
        int rowEnd = (int) (Math.random() * row);
        int columnEnd = (int) (Math.random() * column);

        //Randomly take a new endpoint as long as the end point is equal to a starting point
        while (rowStart == rowEnd && columnStart ==columnEnd) {
            rowEnd = (int) (Math.random() * row);
            columnEnd = (int) (Math.random() * column);
        }
        newMaze.setEndPosition(new Position(rowEnd,columnEnd));

        return newMaze;
    }
}
