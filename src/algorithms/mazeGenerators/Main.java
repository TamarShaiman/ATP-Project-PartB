package algorithms.mazeGenerators;

public class Main {
    public static void main(String[] args) {
        EmptyMazeGenerator EMG = new EmptyMazeGenerator();
        Maze newMaze = EMG.generate(10,10);
        newMaze.print();
    }
}
