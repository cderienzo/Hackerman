package model.gameWorld;

import model.entity.Entity;
import model.entity.Position;

import java.util.Collection;

/**
 * Created by franciscosanguineti on 31/5/17.
 */
public class Grid {
    private Cell[][] matrix;
    private int rows, cols;

    public Grid() {
        this.rows = GameMap.HEIGHT / GameMap.CELL_SIZE;
        this.cols = GameMap.WIDTH / GameMap.CELL_SIZE;
        matrix = new Cell[rows][cols];
        inicializeMatrix();
    }

    public void add(Entity entity) throws OccupiedCellException {
        int i = entity.getPosition().getY() / GameMap.CELL_SIZE;
        int j = entity.getPosition().getX() / GameMap.CELL_SIZE;

        matrix[i][j].add(entity);               //puede tirar exception
    }

    public void add(Collection<? extends Entity> entities) throws OccupiedCellException {       //chequear el comodin
        for(Entity entity: entities) {
            add(entity);
        }
    }


    public Cell getCell(Position position) {
        int i = position.getY() / GameMap.CELL_SIZE;
        int j = position.getX() / GameMap.CELL_SIZE;
        return matrix[i][j];
    }

    public Cell getCell(int x, int y) {
        return matrix[x][y];
    }

    public void freePosition(Position position) {
        int i = position.getY() / GameMap.CELL_SIZE;
        int j = position.getX() / GameMap.CELL_SIZE;
        matrix[i][j].free();
    }

    public Cell[][] getMatrix() {
        return matrix;
    }

    public boolean isPossibleAdd(Position position) {
        int i = position.getY() / GameMap.CELL_SIZE;
        int j = position.getX() / GameMap.CELL_SIZE;
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
