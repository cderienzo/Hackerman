package model.entity.dynamicEntity.character;

import model.entity.Direction;
import model.entity.Position;
import model.entity.dynamicEntity.DynamicEntity;
import model.gameWorld.GameMap;
import model.gameWorld.Grid;

/**
 * Created by franciscosanguineti on 5/6/17.
 */
public abstract class GameCharacter extends DynamicEntity {

    protected int movesRemaining;
    private Timer timer;

    protected GameCharacter(Position position, Direction direction, int velocity) {
        super(position, direction, velocity);
        timer = new Timer(velocity);
    }

    public boolean isPassable() {
        return !PASSABLE;
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
        movesRemaining = GameMap.CELL_SIZE;
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