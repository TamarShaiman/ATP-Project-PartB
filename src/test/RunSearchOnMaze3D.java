package test;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.maze3D.*;
import algorithms.search.*;

import java.util.ArrayList;

public class RunSearchOnMaze3D
{
    public static void main(String[] args) {
        IMazeGenerator3D mg = new MyMaze3DGenerator();
        Maze3D maze = mg.generate(2,15,30);
        maze.print();
        SearchableMaze3D searchableMaze = new SearchableMaze3D(maze);
        //solveProblem(searchableMaze, new BreadthFirstSearch());
        //maze.printWithSolution();
        solveProblem(searchableMaze, new DepthFirstSearch());
        //maze.printWithSolution();
        //solveProblem(searchableMaze, new BestFirstSearch());
        //maze.printWithSolution();
    }
    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s.%s",i,solutionPath.get(i)));
        }
    }

}
