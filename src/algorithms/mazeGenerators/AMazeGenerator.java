package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {

    public abstract Position genStart(Maze Maze);
    public abstract Position genGoal(Maze Maze);
    public abstract int[][] genTable(Maze Maze);


    public long measureAlgorithmTimeMillis(int rows, int columns){
        long start = System.currentTimeMillis();
        this.generate(rows, columns);
        long end = System.currentTimeMillis();
        return end - start;
    }

}
