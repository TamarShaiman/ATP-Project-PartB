package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {
    public long measureAlgorithmTimeMillis(int rows, int columns){
        long start = System.currentTimeMillis();
        this.generate(rows, columns);
        long end = System.currentTimeMillis();
        return end - start;
    }
}
