package algorithms.mazeGenerators;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class SimpleMazeGenerator extends AMazeGenerator {

    public Maze generate(int rows, int columns) {
        validateInput(rows, columns);
        Maze simpleMaze = new Maze();
        simpleMaze.setColNum(columns);
        simpleMaze.setRowNum(rows);

        genAndSetTable(simpleMaze);

        return simpleMaze;
    }

    private void genAndSetTable(Maze simpleMaze) {
        int rows = simpleMaze.getRowNum();
        int columns = simpleMaze.getColNum();
        if (rows < 4 || columns < 4){
            genSmallerThan3X3Table(simpleMaze, rows, columns);
            return;
        }
        initMaze(simpleMaze, rows, columns);
        //if the Maze is smaller than 8X8, we will initiate it differently
        if (rows < 8 || columns < 8){
            genSmallTable(simpleMaze, rows, columns);
        }
        else {
            genBigTable(simpleMaze, rows, columns);
        }
    }

    private void genBigTable(Maze simpleMaze, int rows, int columns) {
        int maxIters = chooseMaxIters(rows, columns);
        for (int k = 2; k < maxIters - 1; k += 2) {
            setFrameOnes(simpleMaze, rows, columns, k, columns - 1 - k, k, rows - 1 - k);
            int numOfHorizontalHoles = genNumOfHoles(rows, k);
            int numOfVerticalHoles = genNumOfHoles(columns, k);

            genAndSetRandomZeroesInFrameCols(simpleMaze, rows, columns, k + 1, rows - 1 - k, numOfHorizontalHoles, k); //left
            genAndSetRandomZeroesInFrameCols(simpleMaze, rows, columns, k + 1, rows - 1 - k, numOfHorizontalHoles, columns - 1 - k); //right
            genAndSetRandomZeroesInFrameRows(simpleMaze, rows, columns, k + 1, columns - 1 - k, numOfVerticalHoles, k); //top
            genAndSetRandomZeroesInFrameRows(simpleMaze, rows, columns, k + 1, columns - 1 - k, numOfVerticalHoles, rows - 1 - k); //Bottom
        }
    }

    private void genAndSetRandomZeroesInFrameCols(Maze simpleMaze, int rows, int columns, int indStart, int indEnd, int numOfHoles, int indColToChange) {
        int[] Zeros = new Random().ints(indStart, indEnd).distinct().limit(numOfHoles).toArray();
        for (int ind : Zeros) {
            simpleMaze.setCell0(ind, indColToChange);
        }
    }

    private void genAndSetRandomZeroesInFrameRows(Maze simpleMaze, int rows, int columns, int indStart, int indEnd, int numOfHoles, int indRowToChange) {
        int[] Zeros = new Random().ints(indStart, indEnd).distinct().limit(numOfHoles).toArray();
        for (int ind : Zeros) {
            simpleMaze.setCell0(indRowToChange, ind);
        }
    }

    private int genNumOfHoles(int number, int currK) {
        int numOfHoles = (int) (Math.sqrt(number - 2 - 2 * currK) * 0.75);
        while (numOfHoles >= number - 2 * currK - 2) {
            numOfHoles--;
        }
        return numOfHoles;
    }

    private int chooseMaxIters(int rows, int columns) {
        if (rows < columns) {
            return (int) Math.ceil(rows / 2);
        } else {
            return (int) Math.ceil(columns / 2);
        }
    }

    private void initMaze(Maze simpleMaze, int rows, int columns) {
        initTableOfZeroes(simpleMaze, rows, columns);
        setFrameOnes(simpleMaze,rows, columns, 0, columns-1, 0, rows-1);
        initStartAndGoal(simpleMaze, rows, columns);
    }

    private void initStartAndGoal(Maze simpleMaze, int rows, int columns) {
        int startInd = genRandomInd(1, rows - 2);
        simpleMaze.setCell0(startInd, 0);
        Position startPos = new Position(startInd, 0);
        simpleMaze.setStart(startPos);
        int goalInd = genRandomInd(1, rows - 2);
        while (rows != 3 && startInd == goalInd){
            goalInd = genRandomInd(1, rows - 2);
        }
        simpleMaze.setCell0(goalInd, columns - 1);
        Position endPos = new Position(goalInd, columns - 1);
        simpleMaze.setGoal(endPos);
    }

    private void initTableOfZeroes(Maze simpleMaze, int rows, int columns) {
        int[][] table = new int[rows][columns]; // n by m 2D array
        for (int i = 0; i < rows; i++) {
            java.util.Arrays.fill(table[i], 0);
        }
        simpleMaze.setMazeTable(table);
    }

    private void setFrameOnes(Maze simpleMaze, int rows, int columns, int left, int right, int top, int bottom) {
        simpleMaze.setColToOnes(left, top, bottom); //left
        simpleMaze.setColToOnes(right, top, bottom); //right
        simpleMaze.setRowToOnes(top, left, right); //top
        simpleMaze.setRowToOnes(bottom, left, right); //bottom
    }

    private void genSmallTable(Maze simpleMaze, int rows, int columns) {
        int startRowInd = simpleMaze.getStartPosition().getRowIndex();
        int goalRowInd = simpleMaze.getGoalPosition().getRowIndex();

        for (int i = 1; i <= columns / 2 - 1 ; i ++){
            if (startRowInd > 1){
                simpleMaze.setCell1(startRowInd - 1, i);
            }
            else{
                simpleMaze.setCell1(startRowInd + 1, i);
            }
            if ((double) i == (double) columns / 2 -1 ){
                break;
            }
            if (goalRowInd > 1){
                simpleMaze.setCell1(goalRowInd -1 , columns -i -1);
            }
            else{
                simpleMaze.setCell1(goalRowInd +1 , columns -i -1);
            }
        }
    }

    private int genRandomInd(int min, int max){
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        return randomNum;
    }

}



