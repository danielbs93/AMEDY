package algorithms.mazeGenerators;

///test-test

public abstract class AMazeGenerator implements IMazeGenerator {

    public abstract Maze generate (int row, int column);

    public long measureAlgorithmTimeMillis (int row, int column){
        long startTime = System.currentTimeMillis();
        generate(row,column);

        long endTime = System.currentTimeMillis();

        return endTime-startTime;
    }
}
