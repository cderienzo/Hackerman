package model.entity.dynamicEntity;

import model.entity.Direction;
import model.entity.Entity;
import model.entity.Position;
import model.entity.dynamicEntity.character.Timer;
import model.gameWorld.Grid;

/**
 * It´s an entity that can rotate
 */
public abstract class DynamicEntity extends Entity {

    private static final long serialVersionUID = 1L;

    // State fields
    protected static final int MOVING = 1;
    protected int state = IDLE;

    // Movement fields
    protected int velocity;

    protected DynamicEntity(Position position, Direction direction, int velocity) {
        super(position, direction);
        this.velocity = velocity;
    }
    
    protected int getState() {
        return state;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public void rotate(Direction direction) {
        super.setDirection(direction);
    }

    public abstract void tick();

    public abstract void tryToMove(Direction direction, Grid grid);

    protected abstract void move();

}
