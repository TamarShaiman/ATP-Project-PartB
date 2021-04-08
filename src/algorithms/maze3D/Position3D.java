package algorithms.maze3D;

public class Position3D {
    private int depthIndex;
    private int rowIndex;
    private int colIndex;

    public Position3D(int depthIndex, int rowIndex, int colIndex) {
        this.depthIndex = depthIndex;
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    public int getDepthIndex() {
        return depthIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }

    @Override
    public String toString() {
        return "{" + depthIndex + "," + rowIndex + "," + colIndex + '}';
    }
}
