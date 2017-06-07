package model.entity.staticEntity.interactiveStaticEntity;

import model.entity.Direction;
import model.entity.Position;
import model.entity.staticEntity.StaticEntity;

/**
 * A static entity that can interactive with the player.
 */
public abstract class InteractiveStaticEntity extends StaticEntity {

    protected InteractiveStaticEntity(Position position, Direction direction) {
        super(position, direction);
    }

    public abstract void interact();


}
