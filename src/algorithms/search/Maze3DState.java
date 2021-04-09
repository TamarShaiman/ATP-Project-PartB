package algorithms.search;

import algorithms.mazeGenerators.Position;
import algorithms.maze3D.Position3D;

public class Maze3DState extends AState{
    private int depth;
    private int row;
    private int col;
    private Position3D position;

    /**
     * @param position in order to create Astate.
     */
    public Maze3DState(Position3D position) {
        super(position.toString()); //AState requires String As argument. Position's toString - {row, col}
        this.position = position;
    }



    public Position3D getPosition() {
        return position;
    }
}
