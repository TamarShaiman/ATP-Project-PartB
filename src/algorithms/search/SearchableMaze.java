package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * SearchableMaze is type of ISearchable problem.
 */
public class SearchableMaze implements ISearchable{
    Maze maze;
    int rows;
    int columns;

    public SearchableMaze(Maze maze) {
        validateMazeInput(maze);
        this.maze = maze;
        this.columns = maze.getColNum();
        this.rows = maze.getRowNum();
    }

    private void validateMazeInput(Maze maze) throws IllegalArgumentException{
       if (maze.getRowNum() < 0|| maze.getColNum() < 0 || maze.getStartPosition() == null || maze.getGoalPosition() ==  null)
       {
           throw new IllegalArgumentException("invalid argument of maze");
       }
    }

    @Override
    public AState getStartState() {
        AState startState = new MazeState(maze.getStartPosition());
        return startState;
    }

    @Override
    public AState getGoalState() {
        AState goalState = new MazeState(maze.getGoalPosition());
        return goalState;
    }

    /**
     * @param cameFrom AState - in order to find all the successors of each Astate , finds all the options for the next step from the current AState.
     * @return list of successors AState, included possibles steps orthogonally & diagonally in the maze.
     */
    @Override
    public ArrayList<AState> getAllSuccessors(AState cameFrom) {
        if (!(cameFrom instanceof MazeState)){
            return null;
        }
        ArrayList<AState> successorsList = new ArrayList<>();
        int cameFromRow = ((MazeState)cameFrom).getPosition().getRowIndex();
        int cameFromCol = ((MazeState)cameFrom).getPosition().getColIndex();
        double cameFromCost = cameFrom.getCost();

        addSuccessorsOrthogonally(successorsList, cameFromCost ,cameFromRow - 1, cameFromCol); //up/*/
        addSuccessorsDiagonally(successorsList, cameFromCost,  cameFromRow,  cameFromCol, cameFromRow - 1, cameFromCol + 1); //  up right
        addSuccessorsOrthogonally(successorsList, cameFromCost, cameFromRow, cameFromCol + 1); //right
        addSuccessorsDiagonally(successorsList, cameFromCost,  cameFromRow,  cameFromCol, cameFromRow + 1, cameFromCol + 1); //  bottom right
        addSuccessorsOrthogonally(successorsList, cameFromCost, cameFromRow + 1, cameFromCol); //bottom
        addSuccessorsDiagonally(successorsList, cameFromCost,  cameFromRow,  cameFromCol, cameFromRow + 1, cameFromCol - 1); //  bottom left
        addSuccessorsOrthogonally(successorsList, cameFromCost, cameFromRow, cameFromCol - 1); //left
        addSuccessorsDiagonally(successorsList, cameFromCost,  cameFromRow,  cameFromCol, cameFromRow - 1, cameFromCol - 1); //  up left

        return successorsList;
    }

    private void addSuccessorsOrthogonally(ArrayList<AState> successorsList, double cameFromCost,  int neighRow, int neighCol){
        if (maze.getCellValue(neighRow, neighCol) == 0 ) {
            MazeState successor = new MazeState(new Position(neighRow, neighCol));
            successor.setCost(cameFromCost + 10);
            successorsList.add(successor);

        }
    }

    private void addSuccessorsDiagonally(ArrayList<AState> successorsList, double cameFromCost, int cameFromRow, int cameFromCol, int neighRow, int neighCol){
        if (maze.getCellValue(neighRow, neighCol) == 0 ){
            int neighA = maze.getCellValue(cameFromRow, neighCol);
            int neighB = maze.getCellValue(neighRow, cameFromCol);
            if ((neighA == 0 || neighB == 0) && neighB != -1 && neighA != -1){
                MazeState successor = new MazeState(new Position(neighRow, neighCol));
                successor.setCost(cameFromCost + 15);
                successorsList.add(successor);
            }
        }
    }

}
