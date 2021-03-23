package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator{

    public Maze generate(int rows, int columns){
        Maze emptyMaze = new Maze();
        emptyMaze.setColNum(columns);
        emptyMaze.setRowNum(rows);

        emptyMaze.setMazeTable(genTable(emptyMaze));
        emptyMaze.setStart(genStart(emptyMaze));
        emptyMaze.setGoal(genGoal(emptyMaze));

        return emptyMaze;
    }

    private Position genStart(Maze Maze) {
        Position start = new Position(0,0);
        return start;
    }

    private Position genGoal(Maze Maze) {
        Position goal = new Position(Maze.getRowNum()-1,Maze.getColNum()-1);
        return goal;
    }

    private int[][] genTable(Maze Maze) {
        int[][] table = new int[Maze.getRowNum()][Maze.getColNum()]; // n by m 2D array
        for (int i=0 ; i < Maze.getRowNum() ; i++){
            java.util.Arrays.fill(table[i], 0);
        }
        return table;
    }
}
