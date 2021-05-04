package algorithms.mazeGenerators;

import algorithms.search.AState;
import algorithms.search.ISearchable;
import algorithms.search.MazeState;
import algorithms.search.Solution;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Maze implements Serializable {
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
    public Maze(byte[] b) {
        this.rowNum = convertByteArrToInt(b, 0);
        this.colNum = convertByteArrToInt(b, 10);
        this.start = convertByteArrToPos(b, 20);
        this.goal =  convertByteArrToPos(b, 32);
        this.mazeTable = convertByteArrToMazeTable( b, 44);
    }

    private int[][] convertByteArrToMazeTable(byte[] b, int i) {
        int[][] table = new int[this.getRowNum()][this.getColNum()];
        for (int j = 0; j < this.getRowNum(); j++) {
            for (int k = 0; k < this.getColNum(); k++) {
                table[j][k] = b[i];
                i++;
            }
        }
        return table;
    }

    private Position convertByteArrToPos(byte[] b, int i) {
        int num = convertByteArrToInt(b, i); //ind number - calculated from 10 first bits
        int border = 0;
        border += b[i + 10] * Math.pow(2, 1);
        border += b[i + 11] * Math.pow(2, 0);
        return createPosBorder(border, num);
    }

    private Position createPosBorder(int border, int num) {
        if (border == 0){return new Position(0, num );}
        else if (border == 1){return new Position(num,this.getColNum()-1);}
        else if (border == 2){return new Position(this.getRowNum()-1,num );}
        else if (border == 3){return new Position(num,0 );}
        else return null;

    }

    private int convertByteArrToInt(byte[] b, int index) {
        int num=0;
        for (int j = 0; j < 10; j++) {
            num += b[index+j]*Math.pow(2,10-j-1);
        }
        return num;
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
                        System.out.print("0 "); //TODO: change back to 0
                    }
                }
                else{
                    System.out.print("1 "); //TODO: change back to 1
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
        byte[] arr = new byte[(this.getColNum())*(this.getRowNum()) + 44];
        Arrays.fill( arr, (byte) 0 );
        initMeteDataArr(arr); // insert row num, col num, start position and goal position;
        mazeTableToByteArr(arr,44); // insert the maze table to the byte arr
        //System.out.println("Byte arr Maze "+ arr.length);
        return arr;

    }

    private void mazeTableToByteArr(byte[] arr, int index) {
        int ind = index;
        int num;
        for (int i = 0 ; i < rowNum ; i++ ) {
            for (int j = 0; j < colNum; j++) {
                num = getCellValue(i, j);
                arr[ind] = (byte)num;
                ind++;
            }
        }
    }

    private void initMeteDataArr(byte[] arr) {
        IntToByteInsertArr(arr, this.getRowNum(),9);    // insert RowNum
        IntToByteInsertArr(arr, this.getColNum(),19);    // insert ColNum
        PosToByteInsertArr(arr, this.getStartPosition(),31);    // insert StartPos
        PosToByteInsertArr(arr, this.getGoalPosition(),43);     // insert GoalPos
    }

    /**
     * @param arr
     * @param  position start & goal positions are always on the borders/edges of the maze
     * border space will be only 2 bytes.
     * similarity to clock: Up-00,Right-01,Bottom-10,Left-11
     */
    private void PosToByteInsertArr(byte[] arr, Position position, int index) {
        int border = getBorder(position);
        IntToByteInsertArr(arr, border,index);
        if (border%2 == 0)
        {
            IntToByteInsertArr(arr, position.getColIndex(),index-2);
        }
        else{
            IntToByteInsertArr(arr, position.getRowIndex(),index-2);
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
        byte[] arrTemp = new byte[10];
        // Number should be positive
        while (num > 0 && ind > -1) {
            int binNum = (num % 2);
            arr[ind--] = (byte)binNum;
            num = num / 2;
        }
        /*for (int i = ind-1; i > -1; i--) {
            arr[index] = arrTemp[i];
            index--;
        }*/
        /*// Padding with 0
        while(ind<10){
            arr[index] = (byte)0;
            index++;
            ind++;
        }*/
    }
}
