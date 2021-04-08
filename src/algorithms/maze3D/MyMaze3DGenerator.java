package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.Random;

public class MyMaze3DGenerator extends AMaze3DGenerator{

    @Override
    public Maze3D generate(int depth, int row, int column) {
        Maze3D myMaze = new Maze3D();
        myMaze.setDepth(depth);
        myMaze.setCol(column);
        myMaze.setRow(row);
        int[][][] gridTable = generateGrid(depth, row, column); //init all Maze Cell to 2 to indicate that they are not part of the Maze (from Prim's algorithm)
        myMaze.setMap(gridTable);
        primGenerate(myMaze, depth, row, column);
        return myMaze;
    }

    private void primGenerate(Maze3D myMaze, int depth, int rows, int columns) {
        ArrayList<Position3D> wallList = initWallList();
        //boolean[][] cellsInMazeMatrix = initMazeNeighMatrix(myMaze, rows, columns); //initiate all the cells in the matrix to false
        myMaze.setStart(genStart(myMaze));
        Position3D startPos = myMaze.getStartPosition();
        //myMaze.setGoal(genGoal(myMaze));
        addWallsNeighToWL(myMaze,depth, rows, columns,wallList, startPos);
        while(!wallList.isEmpty()){
            Position3D currWall = extractWallRandom(wallList);
            Position3D neigh = findSeparatedCell(myMaze, currWall); //neigh is the cell that is not part of the maze - if exists
            if (!(neigh == null)){
                //Make the wall a passage
                myMaze.setCell0(currWall.getDepthIndex(), currWall.getRowIndex(), currWall.getColIndex());
                //mark the unvisited cell as part of the maze
                myMaze.setCell0(neigh.getDepthIndex(), neigh.getRowIndex(), neigh.getColIndex());
                //Add the neighboring walls of the cell to the wall list
                addWallsNeighToWL(myMaze, depth, rows, columns,wallList, neigh);
            }
            wallList.remove(currWall);
        }
        myMaze.setGoal(genGoal(myMaze));
    }

    private Position3D genGoal(Maze3D myMaze) {
        Position3D goalPos = new Position3D(myMaze.getDepth()-1, myMaze.getRow()-1 , myMaze.getCol()-1);
        breakGoal(myMaze, goalPos);
        return goalPos;
    }

    private void breakGoal(Maze3D myMaze, Position3D goalPos) {
        myMaze.setCell0(goalPos.getDepthIndex(), goalPos.getRowIndex(), goalPos.getColIndex());
        myMaze.setCell0(goalPos.getDepthIndex(),goalPos.getRowIndex(), goalPos.getColIndex()-1);
        myMaze.setCell0(goalPos.getDepthIndex()-1,goalPos.getRowIndex(), goalPos.getColIndex()-1);
        myMaze.setCell0(goalPos.getDepthIndex()-1,goalPos.getRowIndex(), goalPos.getColIndex());


    }

    private Position3D genStart(Maze3D myMaze) {
        Position3D startPos = new Position3D(0,0 , 0);
        myMaze.setCell0(0,0,0);
        return  startPos;
    }

    private Position3D findSeparatedCell(Maze3D myMaze, Position3D currWall) { // returns the neighbor cell that is not part of the maze, if exist
        int currDepth = currWall.getDepthIndex();
        int currRow = currWall.getRowIndex();
        int currCol = currWall.getColIndex();
        int neighA, neighB;
        if (currDepth %2 == 0) {
            if (currRow % 2 == 0) { // A is Right to currWall, B is Left to currWall
                neighA = myMaze.getCellValue(currDepth, currRow, currCol - 1);
                neighB = myMaze.getCellValue(currDepth, currRow, currCol + 1);
                if (neighA == 0 && neighB == 2) {
                    return new Position3D(currDepth, currRow, currCol + 1); //return neigh B
                } else if (neighA == 2 && neighB == 0) {
                    return new Position3D(currDepth, currRow, currCol - 1); //return neigh A
                }
            } else { // A is Below currWall, B is Above currWall
                neighA = myMaze.getCellValue(currDepth, currRow - 1, currCol);
                neighB = myMaze.getCellValue(currDepth,currRow + 1, currCol);
                if (neighA == 0 && neighB == 2) {
                    return new Position3D(currDepth, currRow + 1, currCol); //return neigh B
                } else if (neighA == 2 && neighB == 0) {
                    return new Position3D(currDepth, currRow - 1, currCol); //return neigh A
                }
            }
        }
        else { //currDepth %2 == 1
            neighA = myMaze.getCellValue(currDepth - 1, currRow, currCol );
            neighB = myMaze.getCellValue(currDepth + 1, currRow, currCol);
            if (neighA == 0 && neighB == 2) {
                return new Position3D(currDepth + 1, currRow, currCol); //return neigh B
            } else if (neighA == 2 && neighB == 0) {
                return new Position3D(currDepth - 1, currRow, currCol); //return neigh A
            }
        }
        return null;
    }

    private void addWallsNeighToWL(Maze3D myMaze,int depth, int rows, int columns, ArrayList<Position3D> wallList, Position3D cellPos) {
        int currDepth = cellPos.getDepthIndex();
        int currRow = cellPos.getRowIndex();
        int currCol = cellPos.getColIndex();
        if (currDepth != 0) {
            addWallToMaze(myMaze, wallList, currDepth - 1,currRow, currCol);
        }
        if (currDepth != depth-1){
            addWallToMaze(myMaze, wallList, currDepth + 1,currRow , currCol);
        }
        if (currRow != 0) {
            addWallToMaze(myMaze, wallList, currDepth,currRow - 1, currCol);
        }
        if (currRow != rows-1){
            addWallToMaze(myMaze, wallList,currDepth, currRow + 1, currCol);
        }
        if (currCol != 0){
            addWallToMaze(myMaze, wallList,currDepth, currRow, currCol-1);
        }
        if (currCol != columns-1){
            addWallToMaze(myMaze, wallList,currDepth,currRow, currCol + 1);
        }
    }

    private void addWallToMaze (Maze3D myMaze, ArrayList<Position3D> wallList,int depth, int row, int col){
        Position3D neigh = new Position3D(depth, row, col);
        if (myMaze.getCellValue(depth,row, col) == 3 ) {
            myMaze.setCell1(depth,row, col);
            wallList.add(neigh);
        }
    }

    private ArrayList<Position3D> initWallList() {
        ArrayList<Position3D> wallsList = new ArrayList<>();
        return wallsList;
    }

    private int[][][] generateGrid(int depth, int rows, int columns) {
        int[][][] gridTable = new int[depth][rows][columns];
        for (int k = 0; k < depth; k++) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (i % 2 == 0 && j % 2 == 0 && k % 2 == 0)  {
                        gridTable[k][i][j] = 2;
                    }
                    else if ((i % 2 == 1 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0 && k % 2 ==1) || (i % 2 == 0 && j % 2 == 1 && k % 2 ==1) ) {
                        gridTable[k][i][j] = 1;
                    }
                    else {
                        gridTable[k][i][j] = 3;
                    }

                }
            }
        }
        return gridTable;
    }

    private Position3D extractWallRandom(ArrayList<Position3D> wallsList) {
        Random rand = new Random();
        Position3D pos = wallsList.get(rand.nextInt(wallsList.size()));
        wallsList.remove(pos);
        return pos;
    }
}
