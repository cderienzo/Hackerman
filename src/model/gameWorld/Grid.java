package model.gameWorld;

import model.entity.Entity;
import model.entity.Position;

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
        inicializeMatrix();
        }
    }

    public void add(Entity entity) throws OccupiedCellException {
        int i = entity.getPosition().getY() / Map.CELL_SIZE;
        int j = entity.getPosition().getX() / Map.CELL_SIZE;

        matrix[i][j].add(entity);               //puede tirar exception
    }

    public Cell getCell(Position position) {
        int i = position.getY() / Map.CELL_SIZE;
        int j = position.getX() / Map.CELL_SIZE;
        return matrix[i][j];
    }

    public Cell getCell(int x, int y) {
        return matrix[x][y];
    }

    public void freePosition(Position position) {
        int i = position.getY() / Map.CELL_SIZE;
        int j = position.getX() / Map.CELL_SIZE;
        matrix[i][j].free();
    }

    public Cell[][] getMatrix() {
        return matrix;
    }

    public boolean isPossibleAdd(Position position) {
        int i = position.getY() / Map.CELL_SIZE;
        int j = position.getX() / Map.CELL_SIZE;
        return matrix[i][j].isEmpty();
    }

    public boolean isPossibleAdd(int x, int y) {
        return matrix[x][y].isEmpty();
    }

    private void inicializeMatrix() {
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = new Cell();
            }
        }
    }
}
