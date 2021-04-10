package algorithms.maze3D;

public abstract  class AMaze3DGenerator implements IMazeGenerator3D {
    @Override
    public long measureAlgorithmTimeMillis(int depth, int row, int column) {
        long start = System.currentTimeMillis();
        this.generate(depth, row, column);
        long end = System.currentTimeMillis();
        return end - start;
    }

    /**
     * created this method in order to verify the arguments- rows & columns are greater than 1.
     * @param rows
     * @param columns
     * @throws IllegalArgumentException
     */
    protected void validateInput(int depth , int rows, int columns) throws IllegalArgumentException{
        if (rows < 2 || columns < 2 || depth < 2){
            throw new IllegalArgumentException("invalid arguments to generate maze");
        }
    }
}
