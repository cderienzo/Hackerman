package model.entity.staticEntity.interactiveStaticEntity;

import model.entity.Direction;
import model.entity.Position;

/**
 * Created by franciscosanguineti on 25/5/17.
 */
public class Door extends InteractiveStaticEntity{

    private boolean isOpen = false;
    private boolean passed = false;

    public Door(Position position, Direction direction) {
        super(position,direction);
    }

    public void setOpen() {
        isOpen = true;
    }

    public boolean isPassable() {
        return !PASSABLE;
    }

    public boolean hasBeenPassed() {
        return passed;
    }

    public void interact() {
        passed = true;
    }





}
