package algorithms.mazeGenerators;

import algorithms.search.AState;
import algorithms.search.ISearchable;
import algorithms.search.MazeState;
import algorithms.search.Solution;

import java.util.ArrayList;
import java.util.Arrays;

public class Maze {
    private int rowNum;
    private int colNum;
    private Position start;
    private Position goal;
    private int[][] mazeTable;
    //private Solution solution; //TODO delete after tests

    public Maze(int rowNum, int colNum, Position start, Position goal, int[][] mazeTable) {
        this.rowNum = rowNum;
        this.colNum = colNum;
        this.start = start;
        this.goal = goal;
        this.mazeTable = mazeTable;
    }

    public Maze() {
    }

    public void print(){
        //System.out.println(Arrays.deepToString(mazeTable));
        for (int i = 0 ; i < rowNum ; i++ ){
            System.out.print("{ ");
            for (int j = 0 ; j < colNum ; j++){
//                System.out.print(mazeTable[i][j]);
                if (mazeTable[i][j] == 0){
                    if (this.getStartPosition().getRowIndex() == i && this.getStartPosition().getColIndex() == j){
                        System.out.print("S ");
                    }
                    else if (this.getGoalPosition().getRowIndex() == i && this.getGoalPosition().getColIndex() == j){
                        System.out.print("E ");
                    }
                    else{
                        System.out.print(". "); //TODO: change back to 0
                    }
                }
                else{
                    System.out.print("█ "); //TODO: change back to 1
                }
            }
            System.out.println("}");
            //System.out.println(); //ends the line after each row
        }
    }

    public void setCell1(int row, int col){
        this.mazeTable[row][col] = 1;
    }

    public void setCell0(int row, int col){
        this.mazeTable[row][col] = 0;
    }

    public void setColToOnes(int col, int startInd, int endInd) {
        for (int i = startInd; i <= endInd; i++) {
            this.setCell1(i, col);
        }
    }

    public void setRowToOnes(int row, int startInd, int endInd) {
        for (int i = startInd; i <= endInd; i++) {
            this.setCell1(row, i);
        }
    }

    public Position getStartPosition() {
        return this.start;
    }

    public Position getGoalPosition() { return this.goal; }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public void setColNum(int colNum) {
        this.colNum = colNum;
    }

    public void setStart(Position start) {
        this.start = start;
    }

    public void setGoal(Position goal) {
        this.goal = goal;
    }

    public void setMazeTable(int[][] mazeTable) {
        this.mazeTable = mazeTable;
    }

    public int getRowNum() {
        return rowNum;
    }

    public int getColNum() {
        return colNum;
    }

    public int getCellValue(int row, int col) {
        if (row < this.getRowNum() && col < this.getColNum() && row >= 0 && col >=0) {
            return this.mazeTable[row][col];
        }
        else {
            return -1;
        }
    }
    public byte[] toByteArray(){
        byte[] arr = new byte[(this.getColNum()-1)*(this.getRowNum()-1) +44];
        initMeteDataArr(arr); // insert row num, col num, start position and goal position;
        mazeTableToByteArr(arr,44); // insert the maze table to the byte arr
        return arr;

    }

    private void mazeTableToByteArr(byte[] arr, int index) {
        int ind = index;
        int num;
        for (int i = 0 ; i < rowNum ; i++ ) {
            for (int j = 0; j < colNum; j++) {
                num = getCellValue(i, j);
                arr[ind++] = (byte)num;
            }
        }
    }

    private void initMeteDataArr(byte[] arr) {
        IntToByteInsertArr(arr, this.getRowNum(),0);    // insert RowNum
        IntToByteInsertArr(arr, this.getColNum(),10);    // insert ColNum
        PosToByteInsertArr(arr, this.getStartPosition(),20);    // insert StartPos
        PosToByteInsertArr(arr, this.getGoalPosition(),32);     // insert GoalPos
    }

    /**
     * @param arr
     * @param  position start & goal positions are always on the borders/edges of the maze
     * border space will be only 2 bytes.
     * similarity to clock: Up-00,Right-01,Bottom-10,Left-11
     */
    private void PosToByteInsertArr(byte[] arr, Position position, int index) {
        int border = getBorder(position);
        while (border > 0) {        // insert the border bytes to arr
            int binNum = (border % 2);
            arr[index++] = (byte)binNum;
            border = border / 2;
        }
        if (border%2 == 0)
        {
            IntToByteInsertArr(arr, position.getColIndex(),index);
        }
        else{
            IntToByteInsertArr(arr, position.getRowIndex(),index);
        }
    }

    private int getBorder(Position position) {
       if (position.getRowIndex() == 0){return 0;}  // UP-00
       else if (position.getRowIndex() == this.getRowNum()-1){return 2;}    // Bottom-10
       else if (position.getColIndex() == this.getColNum()-1){return 1;}    // Right-01
       else if (position.getColIndex() == 0){return 3;}     // Left-11
       return -1;
    }

    private void IntToByteInsertArr(byte[] arr, int num, int index) {
        int ind = index;
        // Number should be positive
        while (num > 0) {
            int binNum = (num % 2);
            arr[ind++] = (byte)binNum;
            num = num / 2;
        }
        // Padding with 0
        while(ind<10){
            arr[ind++] = (byte)0;
        }
    }
}
