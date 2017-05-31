package model.entity.staticEntity;

import model.entity.Direction;
import model.entity.Entity;
import model.entity.Position;

/**
 * An entity that can´t move.
 */
public abstract class StaticEntity extends Entity {

    protected StaticEntity(Position position, Direction direction) {
        super(position, direction);
    }

}
