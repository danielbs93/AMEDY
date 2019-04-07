package algorithms.mazeGenerators;

public class SimpleMazeGenerator extends AMazeGenerator {
    @Override
    public Maze generate(int row, int column) {

        int rowStrat;
        int columnStart;
        int rowEnd;
        int columnEnd;

        if (row < 2  || column <2 ){
            row = 3;
            column = 3;
        }

        rowStrat = (int)(Math.random()*row);
        rowEnd = (int)(Math.random()*row);
        columnStart = (int)(Math.random()*column);
        columnEnd = (int)(Math.random()*column);

        Maze simpleMaze = new Maze(row, column);

        simpleMaze.setStartPosition(new Position(rowStrat, columnStart));
        simpleMaze.setEndPosition(new Position(rowEnd, columnEnd));

        int [][] arr = simpleMaze.getMazeArray();

        for(int i=0; i<arr.length ; i++){
            for(int j=0 ; j<arr[0].length ; j++){
                arr[i][j]=-1;
            }
        }

        arr [rowStrat][columnStart]=0;
        arr[rowEnd][columnEnd]=0;

        int rowPointer = rowStrat;

        while (rowPointer!=rowEnd){
            if (rowPointer < rowEnd) {
                rowPointer++;
                arr[rowPointer][columnStart] = 0 ;
            }
            else {
                rowPointer--;
                arr[rowPointer][columnStart] = 0;
            }
        }
        int columnPointer = columnStart;
        while (columnPointer!=columnEnd){
            if (columnPointer < columnEnd) {
                columnPointer++;
                arr[rowPointer][columnPointer] = 0 ;
            }
            else {
                columnPointer--;
                arr[rowPointer][columnPointer] = 0;
            }
        }

        for(int i=0; i<arr.length ; i++){
            for(int j=0 ; j<arr[0].length ; j++){
               if (arr[i][j]==-1){
                   arr[i][j] = (int)(Math.round(Math.random()));
               }

            }
        }

        simpleMaze.setMazeArray(arr);

        return simpleMaze;
    }
}
