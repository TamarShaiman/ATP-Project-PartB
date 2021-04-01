package algorithms.mazeGenerators;
import java.util.*;

public class MyMazeGenerator  extends AMazeGenerator {

    @Override
    public Maze generate(int rows, int columns) {

        Maze myMaze = new Maze();
        myMaze.setColNum(columns);
        myMaze.setRowNum(rows);
        int[][] gridTable = generateGrid(rows, columns); //init all Maze Cell to 2 to indicate that they are not part of the Maze (from Prim's algorithm)
        myMaze.setMazeTable(gridTable);
        primGenerate(myMaze , rows, columns);
        return myMaze;
    }

    private void primGenerate(Maze myMaze, int rows, int columns) {
        ArrayList<Position> wallList = initWallList();
        //boolean[][] cellsInMazeMatrix = initMazeNeighMatrix(myMaze, rows, columns); //initiate all the cells in the matrix to false
        myMaze.setStart(genStart(myMaze));
        Position startPos = myMaze.getStartPosition();
        addWallsNeighToWL(myMaze, rows, columns,wallList, startPos);
        while(!wallList.isEmpty()){
            Position currWall = extractWallRandom(wallList);
            Position neigh = findSeparatedCell(myMaze, currWall); //neigh is the cell that is not part of the maze - if exists
            if (!(neigh == null)){
                //Make the wall a passage
                myMaze.setCellTo0(currWall.getColIndex(), currWall.getRowIndex()); //TODO:make sure method integrates with Maze
                //mark the unvisited cell as part of the maze
                myMaze.setCellTo0(neigh.getColIndex(), neigh.getRowIndex());
                //Add the neighboring walls of the cell to the wall list
                addWallsNeighToWL(myMaze, rows, columns,wallList, neigh);
            }
            wallList.remove(currWall);
        }
        myMaze.setGoal(genGoal(myMaze));
    }

    private Position findSeparatedCell(Maze myMaze, Position currWall) { // returns the neighbor cell that is not part of the maz, if exist
        int currRow = currWall.getRowIndex();
        int currCol = currWall.getColIndex();
        int neighA, neighB;
        if (currRow % 2 == 0) { // A is Right to currWall, B is Left to currWall
            neighA = myMaze.getCellValue(currRow,currCol - 1);
            neighB =  myMaze.getCellValue(currRow,currCol + 1);
            if (neighA == 0 && neighB == 2){
                return new Position(currRow,currCol + 1); //return neigh B
            }
            else if (neighA == 2 && neighB == 0){
                return new Position(currRow,currCol - 1); //return neigh A
            }
        }
        else { // A is Below currWall, B is Above currWall
            neighA = myMaze.getCellValue(currRow - 1, currCol);
            neighB = myMaze.getCellValue(currRow + 1, currCol);
            if (neighA == 0 && neighB == 2){
                return new Position(currRow - 1, currCol); //return neigh B
            }
            else if (neighA == 2 && neighB == 0){
                return new Position(currRow + 1, currCol); //return neigh A
            }
        }
        return null;
    }

    private void addWallsNeighToWL(Maze myMaze,int rows, int columns, ArrayList<Position> wallList, Position cellPos) {
        int currRow = cellPos.getRowIndex();
        int currCol = cellPos.getColIndex();
        if (currRow != 0){
            Position neighUp = new Position(currRow-1,currCol);
            wallList.add(neighUp);
        }
        else if (currRow != rows-1){
            Position neighBottom = new Position(currRow+1,currCol);
            wallList.add(neighBottom);
        }
        else if (currCol != 0){
            Position neighLeft = new Position(currRow,currCol-1);
            wallList.add(neighLeft);
        }
        else if (currCol != columns-1){
            Position neighRight = new Position(currRow,currCol+1);
            wallList.add(neighRight);
        }
    }

    /*private boolean[][] initMazeNeighMatrix(Maze myMaze, int rows, int columns) {
        boolean[][] mazaNeighMatix = new boolean[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                mazaNeighMatix[i][j] = false;
            }
        }
        return mazaNeighMatix;
    }*/

    private ArrayList<Position> initWallList() {
        ArrayList<Position> wallsList = new ArrayList<>();
        return wallsList;
    }

    private int[][] generateGrid(int rows, int columns) {
        int[][] gridTable = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i % 2 == 0 && j % 2 == 0 ) {
                    gridTable[i][j] = 2;
                } else {
                    gridTable[i][j] = 1;
                }
            }
        }
        return gridTable;
    }

    private Position extractWallRandom(ArrayList<Position> wallsList) {
        Random rand = new Random();
        Position pos = wallsList.get(rand.nextInt(wallsList.size()));
        wallsList.remove(pos);
        return pos;
    }

    @Override
    public Position genStart(Maze Maze) { //TODO:
        return null;
    }

    @Override
    public Position genGoal(Maze Maze) {
        return null;
    }

    @Override
    public int[][] genTable(Maze Maze) {
        return new int[0][];
    }
}

/*

    public Maze generate(int rows, int columns) {

        Maze myMaze = new Maze();
        myMaze.setRowNum(rows);
        myMaze.setColNum(columns);
        int[][] gridTable = buildGridKruskal(rows, columns);
        ArrayList<Position> wallsList = new ArrayList<>();
        createWalls(gridTable, wallsList);
        generateKruskal(gridTable, wallsList);
        myMaze.setMazeTable(gridTable);
        return myMaze;

    }

    private int[][] buildGridKruskal(int rows, int columns) {
        int[][] gridTable = new int[rows][columns];
        int counter = 2;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i % 2 == 0 && j % 2 == 0 ) {
                    gridTable[i][j] = counter;
                    counter ++;
                } else {
                    gridTable[i][j] = 1; //TODO: maybe use method set rows to 1
                }
            }
        }
        if (rows % 2 == 0 ){    // even number of rows - adjust the numbers to be 1010..10  instead of walls 111...11.
            for (int k = 1; k < columns; k++){
                if (k % 2 != 0){
                    gridTable[rows-1][k] = counter;
                    counter ++;
                }
            }
        }
        return gridTable;
    }

    private void createWalls(int[][] gridTable, ArrayList<Position> wallsList) {
        for (int i = 0; i < gridTable.length; i++) {
            for (int j = 0; j < gridTable[i].length; j++) {
                if (gridTable[i][j] == 1) {
                    if (checkWall(gridTable,i,j)) {
                        Position pos = new Position(i, j);
                        wallsList.add(pos);
                    }
                }
            }
        }
        if (gridTable.length % 2 == 0){
            Position pos = new Position(gridTable.length-1, 0);
            wallsList.remove(pos);
        }
    }

    private boolean checkWall(int[][] gridTable, int i, int j ) {
        if(j != 0  && j != gridTable[i].length-1 && gridTable[i][j+1] != 1 && gridTable[i][j-1] != 1 )
        {
            return true;
        }
        else return i != 0 && i != gridTable.length - 1 && gridTable[i + 1][j] != 1 && gridTable[i - 1][j] != 1;
    }

    private void generateKruskal(int[][] gridTable, ArrayList<Position> wallsList) {
        int neighA, neighB;
        ArrayList<Set<Integer>> listOfCellsSet = new ArrayList<>(); // ArrayList of cells

        while (!wallsList.isEmpty()) {
            Position posWall = extractWallRandom(wallsList);
            int currRow = posWall.getRowIndex();
            int currCol = posWall.getColIndex();

            if (currRow % 2 == 0 || (currRow == gridTable.length-1)) {
                neighA = gridTable[currRow][currCol - 1];
                neighB = gridTable[currRow][currCol + 1];
            }
            else {
                neighA = gridTable[currRow-1][currCol];
                neighB = gridTable[currRow+1][currCol];
            }

            if (neighA != neighB) { // not in the same set
                Boolean ans = mergeCells(listOfCellsSet,neighA,neighB);  // merge the sets cell
                if(ans){gridTable[currRow][currCol] = 0;  }  // change the wall to a cell with the 0 value
            }
        }
    }

    private Boolean mergeCells(ArrayList<Set<Integer>> listOfCellsSet,int destValue, int srcValue) {
        int indexA = -1,indexB = -1;
        for (int i = 0; i < listOfCellsSet.size(); i++) {
            if (listOfCellsSet.get(i).contains(destValue)) {
                indexA = i;
            }
            if (listOfCellsSet.get(i).contains(srcValue)) {
                indexB = i;
            }
        }
        if (indexA == -1 && indexB == -1)
        {
            Set set = new HashSet();
            set.add(destValue);
            set.add(srcValue);
            listOfCellsSet.add(set);
        }
        else if (indexA == -1 && indexB != -1)
        {
            listOfCellsSet.get(indexB).add(destValue);
        }
        else if (indexA != -1 && indexB == -1)
        {
            listOfCellsSet.get(indexA).add(srcValue);
        }
        else if ((indexA != -1 && indexB == indexA)){
            return false;
        }
        else {
            listOfCellsSet.get(indexA).addAll(listOfCellsSet.get(indexB));
            listOfCellsSet.remove(indexB);
        }
        return true;
    }

    private Position extractWallRandom(ArrayList<Position> wallsList) {
        Random rand = new Random();
        Position pos = wallsList.get(rand.nextInt(wallsList.size()));
        wallsList.remove(pos);
        return pos;
    }

    @Override
    public Position genStart(Maze Maze) {
        return null;
    }

    @Override
    public Position genGoal(Maze Maze) {
        return null;
    }

    @Override
    public int[][] genTable(Maze Maze) {
        return new int[0][];
    }*/
