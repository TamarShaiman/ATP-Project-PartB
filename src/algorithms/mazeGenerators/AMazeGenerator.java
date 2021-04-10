package algorithms.mazeGenerators;

import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.abs;

/**
 * abstract class implements IMazeGenerator interface.
 */
public abstract class AMazeGenerator implements IMazeGenerator {

    public long measureAlgorithmTimeMillis(int rows, int columns){
        long start = System.currentTimeMillis();
        this.generate(rows, columns);
        long end = System.currentTimeMillis();
        return end - start;
    }

    /**
     * genSmallerThan3X3Table created in order to handle base case when row || columns is smaller than 3/
     * @param maze
     * @param rows
     * @param columns
     */
    public void genSmallerThan3X3Table(Maze maze, int rows, int columns) {
        if (rows == 2 && columns == 2){
            genS2X2Table(maze, rows, columns);
        }
        else if (rows == 2 && columns == 3){
            genS2X3Table(maze, rows, columns);
        }
        else if (rows == 3 && columns == 2){
            genS3X2Table(maze, rows, columns);
        }
        else if (rows == 3 && columns == 3){
            genS3X3Table(maze, rows, columns);
        }
    }

    private void genS3X3Table(Maze maze, int rows, int columns){
        initSmallTable(maze,3,3);
        int option = ThreadLocalRandom.current().nextInt(0, 2);
        if (option == 0){
            maze.setCell0(0,1);
            maze.setCell0(1,1);
            maze.setCell0(2,1);
        }
        else if (option == 1){
            maze.setCell0(1,0);
            maze.setCell0(1,1);
            maze.setCell0(1,2);
        }
    }

    private void genS3X2Table(Maze maze, int rows, int columns){
        initSmallTable(maze,3,2);
        int option = ThreadLocalRandom.current().nextInt(0, 3);
        if (option == 0){
            maze.setCell0(1,0);
            maze.setCell0(1,1);
        }
        else if (option == 1){
            maze.setCell0(0,0);
            maze.setCell0(1,0);
            maze.setCell0(2,0);
        }
        else if (option == 2){
            maze.setCell0(0,1);
            maze.setCell0(1,1);
            maze.setCell0(2,1);
        }
    }

    private void genS2X3Table(Maze maze, int rows, int columns){
        initSmallTable(maze,2,3);
        int option = ThreadLocalRandom.current().nextInt(0, 3);
        if (option == 0){
            maze.setCell0(0,1);
            maze.setCell0(1,1);
        }
        else if (option == 1){
            maze.setCell0(0,0);
            maze.setCell0(0,1);
            maze.setCell0(0,2);
        }
        else if (option == 2){
            maze.setCell0(1,0);
            maze.setCell0(1,1);
            maze.setCell0(1,2);
        }

    }

    private void genS2X2Table(Maze maze, int rows, int columns){
        initSmallTable(maze, 2,2);
        int wallToBreak = ThreadLocalRandom.current().nextInt(0, 2);
        if (wallToBreak == maze.getStartPosition().getRowIndex()){
            maze.setCell0(wallToBreak, 1);
        }
        else{
            maze.setCell0(wallToBreak, 0);
        }
    }

    private void initSmallTable(Maze maze, int rows, int columns){
        int [][] table = new int[rows][columns];
        for (int i = 0 ; i < rows ; i ++){
            for (int j = 0 ; j < columns ; j++){
                table[i][j] = 1;
            }
        }
        maze.setMazeTable(table);
        int startInd = ThreadLocalRandom.current().nextInt(0, rows);
        maze.setStart(new Position(startInd, 0));
        //System.out.println(startInd);
        int goalInd = ThreadLocalRandom.current().nextInt(0, rows);
        while (goalInd == startInd){
            goalInd = ThreadLocalRandom.current().nextInt(0, rows);
        }
        maze.setGoal(new Position(goalInd, columns -1 ));
        maze.setCell0(startInd, 0);
        maze.setCell0(goalInd, columns -1);
    }

}
