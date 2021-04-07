package algorithms.search;

import algorithms.maze3D.Maze3D;
import algorithms.mazeGenerators.Maze;

import java.util.ArrayList;

public class SearchableMaze3D implements ISearchable{
    Maze3D maze;
    int depth;
    int rows;
    int columns;

    public SearchableMaze3D(Maze3D maze) {
        this.maze = maze;
        this.depth = maze.getDepth();
        this.columns = maze.getCol();
        this.rows = maze.getRow();
    }

    @Override
    public AState getStartState() {
        AState startState = new Maze3DState(maze.getStartPosition());
        return startState;
    }

    @Override
    public AState getGoalState() {
        AState goalState = new Maze3DState(maze.getGoalPosition());
        return goalState;
    }

    @Override
    public ArrayList<AState> getAllSuccessors(AState state) {
        return null;
    }
}
