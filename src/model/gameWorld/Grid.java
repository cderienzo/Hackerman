package model.gameWorld;

/**
 * Created by franciscosanguineti on 31/5/17.
 */
public class Grid {
    private Cell[][] matrix;
    private int rows, cols;

    public Grid() {
        this.rows = Map.HEIGHT / Map.CELL_SIZE;
        this.cols = Map.WIDTH / Map.CELL_SIZE;
        matrix = new Cell[rows][cols];
    }
}
