package model.gameWorld;

import model.entity.Entity;

/**
 * Created by franciscosanguineti on 31/5/17.
 */
public class Cell {

    private static final boolean EMPTY = true;

    private Entity entity;

    private boolean state;

    public Cell() {
        entity = null;
        state = EMPTY;
    }

    public Cell(Entity entity) {
        this.entity = entity;
        state = EMPTY;
    }

    public Entity getEntity() {
        return entity;
    }

    public void free() {
        entity = null;
        state = EMPTY;
    }

    public boolean isEmpty() {
        return state;
    }

    public void add(Entity entity) throws OccupiedCellException {
       if(!this.isEmpty()) {
           throw new OccupiedCellException();
       }
       if(entity == null) {
           return;
       }
       this.entity = entity;
       state = !EMPTY;
    }



}
