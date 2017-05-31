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

    private void rotate(Direction direction) {
        super.setDirection(direction);
    }

    public void tryToMove(Direction direction, Grid grid) {
        if (state != IDLE || direction == null)
            return;

        if(!this.direction.equals(direction)) {
            rotate(direction);
            return;
        }

        int[] dir = direction.getDir();

        Position destination = new Position(getPosition().getX() + dir[0], getPosition().getY() + dir[1]);

        // Check destination is within the borders of the map, and its a valid
        // destination.

        if (!destination.withinBoundaries() || !grid.isPossibleAdd(destination))
            return;
        state = MOVING;
        movesRemaining = Map.CELL_SIZE;
        timer.updateLastMoveTime(System.currentTimeMillis(), direction);
        grid.add(this);
        grid.freePosition(this.getPosition());
    }

    protected void move() {
        if (movesRemaining == 0) {
            updateStatus();
            return;
        }

        long nowTime = System.currentTimeMillis();

        if (timer.moveTimePassed(nowTime)) {
            timer.updateLastMoveTime(nowTime);

            movesRemaining--;

            int[] dir = direction.getDir();
            getPosition().incrementPosition(dir[0], dir[1]);
        }
    }

    protected void updateStatus() {
        if (state == MOVING && movesRemaining <= 0)
            state = IDLE;
    }

}
