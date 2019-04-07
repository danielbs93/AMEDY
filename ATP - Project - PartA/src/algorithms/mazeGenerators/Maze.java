package algorithms.mazeGenerators;


public class Maze {
    private int row;
    private int column;
    private int [][] mazeArray;
    private Position startPosition;
    private Position endPosition;



    public Maze(int row, int column)
    {
        this.row = row;
        this.column = column;

        mazeArray = new int[row][column];

    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int [][] getMazeArray() {
        return mazeArray;
    }

    public void setMazeArray(int [][] mazeArray) {
        this.mazeArray = mazeArray;
    }

    public void setStartPosition(Position startPosition) {
        this.startPosition = startPosition;
    }

    public void setEndPosition(Position endPosition) {
        this.endPosition = endPosition;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public Position getGoalPosition() {
        return endPosition;
    }

    /*
    public void print(){
        String printMaze="";
        for(int i=0 ; i<mazeArray.length ; i++){
            printMaze=printMaze+"\n";
            for(int j=0; j<mazeArray[0].length ; j++){
                if(i==startPosition.getRowIndex() && j==startPosition.getColumnIndex()){
                    printMaze=printMaze + "S";
                }
                else if (i==endPosition.getRowIndex() && j==endPosition.getColumnIndex()){
                    printMaze=printMaze + "E";
                }
                else{
                    printMaze=printMaze+ mazeArray[i][j];
                }

            }
        }
        System.out.println(printMaze);
    }
    */
    public void print () {
        for (int i = 0; i < mazeArray.length; i++) {
            for (int j = 0; j < mazeArray[i].length; j++) {
                if (i == startPosition.getRowIndex() && j == startPosition.getColumnIndex()) {//startPosition
                    System.out.print(" " + "\u001B[44m" + " ");
                } else if (i == endPosition.getRowIndex() && j == endPosition.getColumnIndex()) {//goalPosition
                    System.out.print(" " + "\u001B[44m" + " ");
                } else if (mazeArray[i][j] == 1) System.out.print(" " + "\u001B[45m" + " ");
                else System.out.print(" " + "\u001B[107m" + " ");
            }
            System.out.println(" " + "\u001B[107m");
        }

    }
    public void draw(){
        String printMaze="";
        for(int i=0 ; i<mazeArray.length ; i++){
            printMaze=printMaze+"\n";
            for(int j=0; j<mazeArray[0].length ; j++){
                if(i==startPosition.getRowIndex() && j==startPosition.getColumnIndex()){
                    printMaze=printMaze + "S";
                }
                else if (i==endPosition.getRowIndex() && j==endPosition.getColumnIndex()){
                    printMaze=printMaze + "E";
                }
                else if(mazeArray[i][j]==9){
                    printMaze=printMaze+ "|";
                }else if(mazeArray[i][j]==8){
                    printMaze=printMaze+ "-";
                }else {
                    printMaze=printMaze+ mazeArray[i][j];
                }

            }
        }
        System.out.println(printMaze);
    }



}
