package model.gameWorld;

import model.entity.Entity;

/**
 * Created by franciscosanguineti on 31/5/17.
 */
public class Cell {

    private static final boolean OCCUPIED = true;

    private Entity entity;

    private boolean state;

    public Cell() {
        entity = null;
        state = !OCCUPIED;
    }

    public Cell(Entity entity) {
        this.entity = entity;
        state = OCCUPIED;
    }

    public Entity getEntity() {
        return entity;
    }

    public void removeEntity() {
        entity = null;
        state = !OCCUPIED;
    }

    public static boolean isOccupied() {
        return OCCUPIED;
    }

    public void add(Entity entity) throws OccupiedCellException {
       if(this.isOccupied()) {
           throw new OccupiedCellException();
       }
       this.entity = entity;
       state = OCCUPIED;
    }



}
