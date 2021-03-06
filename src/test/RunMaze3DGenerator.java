package test;

import algorithms.maze3D.IMazeGenerator3D;
import algorithms.maze3D.*;
import algorithms.mazeGenerators.*;
public class RunMaze3DGenerator {
    public static void main(String[] args) {
        testMazeGenerator3D(new MyMaze3DGenerator());

    }
    private static void testMazeGenerator3D(IMazeGenerator3D mazeGenerator) {
        // prints the time it takes the algorithm to run
        //System.out.println(String.format("Maze generation time(ms): %s", mazeGenerator.measureAlgorithmTimeMillis(100, 100, 100)));
        // generate another maze
//        Maze3D maze = mazeGenerator.generate(4,4,4);
        Maze3D maze = mazeGenerator.generate(4,4,3);

        // prints the maze
        maze.print();
        //maze.printTest();
        // get the maze entrance
        Position3D startPosition = maze.getStartPosition();
        // print the start position
        System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"
        // prints the maze exit position
        System.out.println(String.format("Goal Position: %s", maze.getGoalPosition()));
    }
}