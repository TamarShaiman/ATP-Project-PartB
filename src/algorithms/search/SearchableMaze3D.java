package algorithms.search;

import algorithms.maze3D.Maze3D;
import algorithms.maze3D.Position3D;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

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
    public ArrayList<AState> getAllSuccessors(AState cameFrom) {
        if (!(cameFrom instanceof Maze3DState)) {
            return null;
        }
        ArrayList<AState> successorsList = new ArrayList<>();
        int cameFromDepth = ((Maze3DState) cameFrom).getPosition().getDepthIndex();
        int cameFromRow = ((Maze3DState) cameFrom).getPosition().getRowIndex();
        int cameFromCol = ((Maze3DState) cameFrom).getPosition().getColIndex();
        double cameFromCost = cameFrom.getCost();

        addSuccessorsOrthogonally(successorsList, cameFromCost ,cameFromDepth,cameFromRow - 1, cameFromCol); //up
        addSuccessorsOrthogonally(successorsList, cameFromCost,cameFromDepth, cameFromRow, cameFromCol + 1); //right
        addSuccessorsOrthogonally(successorsList, cameFromCost,cameFromDepth, cameFromRow + 1, cameFromCol); //bottom
        addSuccessorsOrthogonally(successorsList, cameFromCost,cameFromDepth, cameFromRow, cameFromCol - 1); //left
        addSuccessorsOrthogonally(successorsList, cameFromCost ,cameFromDepth - 1,cameFromRow , cameFromCol); //front
        addSuccessorsOrthogonally(successorsList, cameFromCost,cameFromDepth + 1, cameFromRow, cameFromCol ); //back


        return successorsList;
    }

    private void addSuccessorsOrthogonally(ArrayList<AState> successorsList, double cameFromCost,int neighDepth,  int neighRow, int neighCol){
        if (maze.getCellValue(neighDepth,neighRow, neighCol) == 0 ) {
            Maze3DState successor = new Maze3DState(new Position3D(neighDepth, neighRow, neighCol));
            successor.setCost(cameFromCost + 10);
            successorsList.add(successor);
        }
    }

}
