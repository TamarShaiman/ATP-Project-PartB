package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState{
    private int row;
    private int col;
    private Position position;

    /**
     * @param position in order to create Astate.
     */

    public MazeState(Position position) {
        super(position.toString()); //AState requires String As argument. Position's toString - {row, col}
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
