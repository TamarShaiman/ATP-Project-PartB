package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {
    ABFSBasedSearchingAlgorithm bestFS = new BestFirstSearch();

    @Test
    void testBestCost5X5Empty() {
        Maze maze = new Maze();
        int table[][]  = {{0,0,0,0,0}, {0,0,0,0,0},{0,0,0,0,0}, {0,0,0,0,0}, {0,0,0,0,0}};
        Solution solution = initTest(maze, table, 5,5);
        assertEquals(60, solution.getSolutionPath().get(4).getCost());
    }

    @Test
    void testBestRoute5X5Empty() {
        Maze maze = new Maze();
        int table[][]  = {{0,0,0,0,0}, {0,0,0,0,0},{0,0,0,0,0}, {0,0,0,0,0}, {0,0,0,0,0}};
        Solution solution = initTest(maze, table, 5,5);
        ArrayList<AState>  path = new ArrayList<>();
        path.add(new MazeState(new Position(0,0)));
        path.add(new MazeState(new Position(1,1)));
        path.add(new MazeState(new Position(2,2)));
        path.add(new MazeState(new Position(3,3)));
        path.add(new MazeState(new Position(4,4)));
        assertEquals(path, solution.getSolutionPath());
    }

    @Test
    void testBestCost3X3() {
        Maze maze = new Maze();
        int table[][]  = {{0,0,0}, {1,1,0}, {1,1,0}};
        Solution solution = initTest(maze, table, 3,3);
        assertEquals(35, solution.getSolutionPath().get(3).getCost());
    }

    @Test
    void testBestRoute3X3() {
        Maze maze = new Maze();
        int table[][]  = {{0,0,0}, {1,1,0}, {1,1,0}};
        Solution solution = initTest(maze, table, 3,3);
        ArrayList<AState>  path = new ArrayList<>();
        path.add(new MazeState(new Position(0,0)));
        path.add(new MazeState(new Position(0,1)));
        path.add(new MazeState(new Position(1,2)));
        path.add(new MazeState(new Position(2,2)));
        assertEquals(path, solution.getSolutionPath());
    }

    @Test
    void testBestCost4X4() {
        Maze maze = new Maze();
        int table[][]  = {{0,1,1,1}, {0,0,0,1}, {0,1,0,1}, {0,0,0,0}};
        Solution solution = initTest(maze, table, 4,4);
        assertEquals(45, solution.getSolutionPath().get(3).getCost());
    }

    @Test
    void testBestRoute4X4() {
        Maze maze = new Maze();
        int table[][]  = {{0,1,1,1}, {0,0,0,1}, {0,1,0,1}, {0,0,0,0}};
        Solution solution = initTest(maze, table, 4,4);
        ArrayList<AState>  path = new ArrayList<>();
        path.add(new MazeState(new Position(0,0)));
        path.add(new MazeState(new Position(1,1)));
        path.add(new MazeState(new Position(2,2)));
        path.add(new MazeState(new Position(3,3)));
        assertEquals(path, solution.getSolutionPath());
    }

    @Test
    void testBestCost10X10() {
        Maze maze = new Maze();
        int table[][]  = {{0,1,1,1,1,1,1,1,1,1}, {0,0,0,1,1,1,1,1,1,1}, {1,1,0,1,1,1,1,1,1,1}, {1,0,0,1,1,1,1,1,1,1}, {1,0,1,1,1,1,1,1,1,1}, {1,0,0,1,1,1,1,1,1,1}, {1,1,0,1,1,1,1,1,1,1}, {1,0,0,1,1,1,1,1,1,1}, {1,0,0,0,0,0,0,0,0,1}, {1,1,1,0,1,0,0,1,0,0} };
        Solution solution = initTest(maze, table, 10,10);
        assertEquals(170
                , solution.getSolutionPath().get(14).getCost());
    }

    @Test
    void testBestRoute10X10() {
        Maze maze = new Maze();
        int table[][]  = {{0,1,1,1,1,1,1,1,1,1}, {0,0,0,1,1,1,1,1,1,1}, {1,1,0,1,1,1,1,1,1,1}, {1,0,0,1,1,1,1,1,1,1}, {1,0,1,1,1,1,1,1,1,1}, {1,0,0,1,1,1,1,1,1,1}, {1,1,0,1,1,1,1,1,1,1}, {1,0,0,1,1,1,1,1,1,1}, {1,0,0,0,0,0,0,0,0,1}, {1,1,1,0,1,0,0,1,0,0} };
        Solution solution = initTest(maze, table, 10,10);
        ArrayList<AState>  path = new ArrayList<>();
        path.add(new MazeState(new Position(0,0)));
        path.add(new MazeState(new Position(1,1)));
        path.add(new MazeState(new Position(2,2)));
        path.add(new MazeState(new Position(3,2)));
        path.add(new MazeState(new Position(4,1)));
        path.add(new MazeState(new Position(5,1)));
        path.add(new MazeState(new Position(6,2)));
        path.add(new MazeState(new Position(7,2)));
        path.add(new MazeState(new Position(8,3)));
        path.add(new MazeState(new Position(8,4)));
        path.add(new MazeState(new Position(8,5)));
        path.add(new MazeState(new Position(8,6)));
        path.add(new MazeState(new Position(8,7)));
        path.add(new MazeState(new Position(8,8)));
        path.add(new MazeState(new Position(9,9)));
        assertEquals(path, solution.getSolutionPath());
    }

    private Solution initTest(Maze maze, int[][] table, int rows, int cols){
        maze.setRowNum(rows);
        maze.setColNum(cols);
        maze.setStart(new Position(0,0));
        maze.setGoal(new Position(rows - 1 ,cols -1));
        ISearchable sMaze = new SearchableMaze(maze);
        maze.setMazeTable(table);
        return bestFS.solve(sMaze);
    }


}