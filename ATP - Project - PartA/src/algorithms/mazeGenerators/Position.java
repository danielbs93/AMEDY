package algorithms.mazeGenerators;

import java.util.Objects;

public class Position {

    private int rowIndex;
    private int columnIndex;

    public Position(int row, int column) {
        this.rowIndex = row;
        this.columnIndex = column;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public boolean equals(Object o) {
        if(((Position)o).rowIndex==this.rowIndex && ((Position)o).columnIndex==this.columnIndex)
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowIndex, columnIndex);
    }

    @Override
    public String toString() {
        return "{" + rowIndex +
                ", " + columnIndex +
                '}';
    }
}
