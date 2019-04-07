package algorithms.mazeGenerators;


public interface IMazeGenerator {

    public Maze generate (int row, int column);

    public long measureAlgorithmTimeMillis (int row, int column);
}
