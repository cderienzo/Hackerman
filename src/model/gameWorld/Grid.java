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
        int i = entity.getPosition().getX() / GameMap.CELL_SIZE;
        int j = entity.getPosition().getY() / GameMap.CELL_SIZE;
        matrix[i][j].add(entity);               //puede tirar exception
    }

    public void add(Collection<? extends Entity> entities) throws OccupiedCellException {       //chequear el comodin
        for(Entity entity: entities) {
            add(entity);
        }
    }


    public Cell getCell(Position position) {
        Position positionGrid = position.toGridIndexes();
        return matrix[positionGrid.getX()][positionGrid.getY()];
    }

    public Cell getCell(int x, int y) {
        return matrix[x][y];
    }

    public void freePosition(Position position) {
        Position positionGrid = position.toGridIndexes();
        matrix[positionGrid.getX()][positionGrid.getY()].free();
    }

    public Cell[][] getMatrix() {
        return matrix;
    }

    public boolean isPossibleAdd(Position position) {
        Position positionGrid = position.toGridIndexes();
        return matrix[positionGrid.getX()][positionGrid.getY()].isEmpty();
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
