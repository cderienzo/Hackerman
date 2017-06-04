package model.entity.dynamicEntity;

import model.entity.Direction;
import model.entity.Entity;
import model.entity.Position;
import model.gameWorld.Grid;
import model.gameWorld.Map;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
    protected int movesRemaining;
    private Timer timer;

    protected DynamicEntity(Position position, Direction direction, int velocity) {
        super(position, direction);
        this.velocity = velocity;
        timer = new Timer(velocity);
    }

    
    public int getState() {
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

    public abstract void trytoMove(Direction direction, Grid grid);

    public abstract void move();

    protected void updateStatus() {
        if (state == MOVING && movesRemaining <= 0)
            state = IDLE;
    }

}
