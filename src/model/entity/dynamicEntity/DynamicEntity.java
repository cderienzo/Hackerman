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
    protected int state = IDLE;
    protected static final int MOVING = 1;

    // Movement fields
    protected int velocity;
    protected Grid grid;

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

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    protected void rotate(Direction direction) {
        super.setDirection(direction);
    }

    public abstract void tick();

    public abstract void tryToMove(Direction direction);

    protected abstract void move();

}
