package algorithms.mazeGenerators;

public class Main {
    public static void main(String[] args) {
/*        EmptyMazeGenerator EMG = new EmptyMazeGenerator();
        Maze EMaze = EMG.generate(10,10);
        EMaze.print();*/

        SimpleMazeGenerator SMG = new SimpleMazeGenerator();
        Maze SMaze = SMG.generate(20,20);
        //System.out.println(SMG.measureAlgorithmTimeMillis(1000,1000));
        SMaze.print();
    }
}
