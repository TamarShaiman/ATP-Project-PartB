package algorithms.mazeGenerators;

import test.RunMazeGenerator;

public class Main {
    public static void main(String[] args) {
/*        EmptyMazeGenerator EMG = new EmptyMazeGenerator();
        Maze EMaze = EMG.generate(10,10);
        EMaze.print();*/

        /*SimpleMazeGenerator SMG = new SimpleMazeGenerator();
        Maze SMaze = SMG.generate(5,5);
        System.out.println(SMG.measureAlgorithmTimeMillis(1000,1000));
        SMaze.print();*/

/*        MyMazeGenerator MMG = new MyMazeGenerator();
//        System.out.print("number of milliseconds: ");
//        System.out.println(MMG.measureAlgorithmTimeMillis(1000,1000));
        Maze MMaze = MMG.generate(10,15);
        MMaze.print();*/

        RunMazeGenerator RGM = new RunMazeGenerator();
    }
}
