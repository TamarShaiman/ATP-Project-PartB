package algorithms.maze3D;

import algorithms.mazeGenerators.Position;

public class Maze3D {
    private int depthNum;
    private int rowNum;
    private int colNum;
    private Position3D start;
    private Position3D goal;
    private int[][][] map;

    public Maze3D(int depthNum, int rowNum, int colNum, Position3D start, Position3D goal, int[][][] map) {
        this.depthNum = depthNum;
        this.rowNum = rowNum;
        this.colNum = colNum;
        this.start = start;
        this.goal = goal;
        this.map = map;
    }

    public Maze3D() {
    }

    public Position3D getStartPosition() {
        return start;
    }

    public Position3D getGoalPosition() {
        return goal;
    }

    public int[][][] getMapPosition() {
        return map;
    }

    public int getDepth() {
        return depthNum;
    }

    public int getRow() {
        return rowNum;
    }

    public int getCol() {
        return colNum;
    }

    public void setDepth(int depthNum) {
        this.depthNum = depthNum;
    }

    public void setRow(int rowNum) {
        this.rowNum = rowNum;
    }

    public void setCol(int colNum) {
        this.colNum = colNum;
    }

    public void setStart(Position3D start) {
        this.start = start;
    }

    public void setGoal(Position3D goal) {
        this.goal = goal;
    }

    public void setMap(int[][][] map) {
        this.map = map;
    }

    public void print() {
        //System.out.println(Arrays.deepToString(mazeTable));
        for (int k = 0; k < depthNum; k++) {
            for (int i = 0; i < rowNum; i++) {
                //System.out.print("{ ");
                for (int j = 0; j < colNum; j++) {
//                System.out.print(mazeTable[i][j]);
                    if (map[k][i][j] == 0) {
                        if (this.getStartPosition().getRowIndex() == i && this.getStartPosition().getColIndex() == j && this.getStartPosition().getDepthIndex() == k) {
                            System.out.print("S ");
                        } else if (this.getGoalPosition().getRowIndex() == i && this.getGoalPosition().getColIndex() == j && this.getGoalPosition().getDepthIndex() == k) {
                            System.out.print("E ");
                        } else {
                            System.out.print(". "); //TODO: change back to 0
                        }
                    } else {
                        System.out.print("█ "); //TODO: change back to 1
                    }
                   // System.out.print(map[k][i][j] + " ");
                }
                //System.out.println("}");
                System.out.println(); //ends the line after each row
            }
            System.out.println(); //ends the line after each row
        }
    }

    public void setCell0(int depth, int row , int col) {
        this.map[depth][row][col] = 0 ;
    }
    public void setCell1(int depth, int row , int col) {
        this.map[depth][row][col] = 1 ;
    }

    public int getCellValue(int depth, int row, int col) {
        if ( depth < this.depthNum && row < this.rowNum && col < this.colNum && depth>= 0 && row >= 0 && col >=0) {
            return this.map[depth][row][col];
        }
        else {return -1;}
    }
}
