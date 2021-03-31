package algorithms.mazeGenerators;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class SimpleMazeGenerator extends AMazeGenerator {

    public Maze generate(int rows, int columns) {
        Maze simpleMaze = new Maze();
        simpleMaze.setColNum(columns);
        simpleMaze.setRowNum(rows);


        int[][] table = new int[rows][columns]; // n by m 2D array
        for (int i = 0; i < rows; i++) {
            java.util.Arrays.fill(table[i], 0);
        }
        simpleMaze.setMazeTable(table);

        setColToOnes(simpleMaze, 0, 0, rows - 1); //left
        setColToOnes(simpleMaze, columns - 1, 0, rows - 1); //right
        setRowToOnes(simpleMaze, 0, 0, columns - 1); //top
        setRowToOnes(simpleMaze, rows - 1, 0, columns - 1); //bottom
        //first iteration
        int startInd = genRandomInd(1, rows - 2);
        simpleMaze.setCell0(startInd, 0);
        Position startPos = new Position(startInd, 0);
        simpleMaze.setStart(startPos);
        int goalInd = genRandomInd(1, rows - 2);
        simpleMaze.setCell0(goalInd, columns - 1);
        Position endPos = new Position(goalInd, rows - 1);
        simpleMaze.setGoal(endPos);


        if (rows < 8 || columns < 8){
            setBaseTable(simpleMaze, rows, columns);
            return simpleMaze;
        }

        else {
            //iterations
            int maxIters;
            if (rows < columns) {
                maxIters = (int) Math.ceil(rows / 2);
            } else {
                maxIters = (int) Math.ceil(columns / 2);
            }
            for (int k = 2; k < maxIters - 1; k += 2) {
                setColToOnes(simpleMaze, k, k, rows - 1 - k); //left
                setColToOnes(simpleMaze, columns - 1 - k, k, rows - 1 - k); //right
                setRowToOnes(simpleMaze, k, k, columns - 1 - k); //top
                setRowToOnes(simpleMaze, rows - 1 - k, k, columns - 1 - k); //bottom

                //"break" some of the walls
                //left

                int numOfHorizontalHoles = (int) (Math.sqrt(rows - 2 - 2 * k) * 0.75);
                int numOfVerticalHoles = (int) (Math.sqrt(columns - 2 - 2 * k) * 0.75);

/*            int numOfHorizontalHoles = 3;
            int numOfVerticalHoles = 3;*/
                while (numOfHorizontalHoles >= columns - 2 * k - 2) {
                    numOfHorizontalHoles--;
                }
                while (numOfVerticalHoles >= rows - 2 * k - 2) {
                    numOfVerticalHoles--;
                }


                int[] leftZeros = new Random().ints(k + 1, rows - 1 - k).distinct().limit(numOfHorizontalHoles).toArray();
                for (int ind : leftZeros) {
                    simpleMaze.setCell0(ind, k);
                }

                //right
                int[] rightZeros = new Random().ints(k, rows - 1 - k).distinct().limit(numOfHorizontalHoles).toArray();
                for (int ind : rightZeros) {
                    simpleMaze.setCell0(ind, rows - 1 - k);
                }

                //top
                Math.log(rows - 1 - 2 * k);
                int[] topZeros = new Random().ints(k, columns - 1 - k).distinct().limit(numOfVerticalHoles).toArray();
                for (int ind : topZeros) {
                    simpleMaze.setCell0(k, ind);
                }

                //bottom
                int[] bottomZeros = new Random().ints(k, columns - 1 - k).distinct().limit(numOfVerticalHoles).toArray();
                for (int ind : bottomZeros) {
                    simpleMaze.setCell0(columns - 1 - k, ind);
                }

            }

            return simpleMaze;
        }
    }

    private void setBaseTable(Maze simpleMaze, int rows, int columns) {
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

    private void setColToOnes(Maze maze, int col, int startInd, int endInd) {
        for (int i = startInd; i <= endInd; i++) {
            maze.setCell1(i, col);
        }
    }

    private void setRowToOnes(Maze maze, int row, int startInd, int endInd) {
        for (int i = startInd; i <= endInd; i++) {
            maze.setCell1(row, i);
        }
    }

    private int genRandomInd(int min, int max){
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        return randomNum;
    }

}


