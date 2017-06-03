package model.gameWorld;

import model.entity.Entity;
import model.entity.Position;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by franciscosanguineti on 31/5/17.
 */
public class Grid {
    private Cell[][] matrix;
    private int rows, cols;

    public Grid() {
        this.rows = GameMap.getHeight() / GameMap.getCellSize();
        this.cols = GameMap.getWidth() / GameMap.getCellSize();
        matrix = new Cell[rows][cols];
        initializeMatrix();
    }

    public void add(Entity entity) throws OccupiedCellException {
        int i = entity.getPosition().getY() / GameMap.getCellSize();
        int j = entity.getPosition().getX() / GameMap.getCellSize();

        matrix[i][j].add(entity);               //puede tirar exception
    }

    public void add(Collection<? extends Entity> entities) throws OccupiedCellException {       //chequear el comodin
        for(Entity entity: entities) {
            add(entity);
        }
    }


    public Cell getCell(Position position) {
        int i = position.getY() / GameMap.getCellSize();
        int j = position.getX() / GameMap.getCellSize();
        return matrix[i][j];
    }

    public Cell getCell(int x, int y) {
        return matrix[x][y];
    }

    public void freePosition(Position position) {
        int i = position.getY() / GameMap.getCellSize();
        int j = position.getX() / GameMap.getCellSize();
        matrix[i][j].free();
    }

    public Cell[][] getMatrix() {
        return matrix;
    }

    public boolean isPossibleAdd(Position position) {
        int i = position.getY() / GameMap.getCellSize();
        int j = position.getX() / GameMap.getCellSize();
        return matrix[i][j].isEmpty();
    }

    public boolean isPossibleAdd(int x, int y) {
        return matrix[x][y].isEmpty();
    }

    private void initializeMatrix() {
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = new Cell();
            }
        }
    }
}
