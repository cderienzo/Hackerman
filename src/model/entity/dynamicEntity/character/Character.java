package model.entity.dynamicEntity.character;

import model.entity.Direction;
import model.entity.Position;
import model.entity.dynamicEntity.DynamicEntity;
import model.entity.dynamicEntity.Timer;

/**
 *  A dynamicEntity that can move
 */
public abstract class Character extends DynamicEntity {

    protected Character(Position position, Direction direction, int velocity) {
        super(position, direction, velocity);
    }

    public boolean isPassable() {
        return !PASSABLE;
    }

    public abstract void tick();
}
