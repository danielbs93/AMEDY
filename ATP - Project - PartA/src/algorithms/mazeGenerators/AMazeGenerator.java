package algorithms.mazeGenerators;
/**
 *AMazeGenerator class is an abstract class.
 * This class creates the template to generate a maze.
 *It has an abstract method that generate a maze (The classes that inherit this class implement the function).
 * This class implements the Interface IMazeGenerator.
 */

public abstract class AMazeGenerator implements IMazeGenerator {

    /**
     * This is an abstract method that generate a maze.
     * (The classes that inherit AMazeGenerator must execute this method to create an instance)
     * @param row this is the number of rows in the maze.
     * @param column this is the number of columns in the maze.
     * @return Maze. This returns the maze that was generated in the method.
     */
    public abstract Maze generate (int row, int column);



    /**
     * This method measures the system clock before generate the maze and after generate the maze.
     * And calculates how long it took to produce the maze.
     * @param row this is the number of rows in the maze.
     * @param column this is the number of columns in the maze.
     * @return long. This returns the time that took to generate the maze.
     */
    public long measureAlgorithmTimeMillis (int row, int column){

        //measures the system clock before generate the maze
        long startTime = System.currentTimeMillis();

        //generate the maze
        generate(row,column);
        //measures the system clock after generate the maze
        long endTime = System.currentTimeMillis();

        return endTime-startTime;
    }
}
