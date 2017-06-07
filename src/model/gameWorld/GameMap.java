package model.gameWorld;

import model.Managers.EntityManager;
import model.entity.Entity;

import java.util.List;

public class GameMap {

    public static final int HEIGHT = 640;
    public static final int WIDTH = (HEIGHT * 3) / 4;
    public static int CELL_SIZE = 32;

    private Grid grid;

    private EntityManager entityManager;

    public GameMap(EntityManager entityManager) throws OccupiedCellException{
        grid = new Grid();
        List<Entity> entities = entityManager.getEntities();
        grid.add(entities);                                     //puede tirar OccupedCellException
        entityManager.setGrid(grid);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Grid getGrid() {
        return grid;
    }

}
